package br.ufpa.labes.spm.repository.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import br.ufpa.labes.spm.annotations.Criteria;
import br.ufpa.labes.spm.annotations.EnumCriteriaType;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;
import br.ufpa.labes.spm.util.PagingContext;
import br.ufpa.labes.spm.util.SortCriteria;
import br.ufpa.labes.spm.util.ident.ConversorDeIdent;
import br.ufpa.labes.spm.util.ident.SemCaracteresEspeciais;
import br.ufpa.labes.spm.util.ident.TrocaEspacoPorPonto;

public class GenericRepositoryImpl<T, PK extends Serializable> extends SimpleJpaRepository<T, PK>
    implements GenericRepository<T, PK> {

  @PersistenceContext private EntityManager em;

  Logger log = LoggerFactory.getLogger(GenericRepositoryImpl.class);

  private Class<T> clazz;

  public GenericRepositoryImpl(JpaEntityInformation<T, PK> entityInformation, EntityManager em) {
    super(entityInformation, em);
    this.clazz = entityInformation.getJavaType();
    this.em = em;
  }

  @Override
  public T update(T object) {
    this.getPersistenceContext().merge(object);
    return object;
  }

  @Override
  public List<T> retrieveByCriteria(T searchCriteria) {
    return retrieveByCriteria(searchCriteria, null, null);
  }

  @Override
  public List<T> retrieveByCriteria(T searchCriteria, SortCriteria sortCriteria) {
    return retrieveByCriteria(searchCriteria, sortCriteria, null);
  }

  @Override
  public List<T> retrieveByCriteria(
      T searchCriteria, SortCriteria sortCriteria, PagingContext paging) {
    ArrayList<Field> criteria = new ArrayList<Field>();
    Field[] fields = this.getBusinessClass().getDeclaredFields();

    for (Field field : fields) {
      if (field.getAnnotation(Criteria.class) != null) criteria.add(field);
    }

    String queryStr =
        "from " + this.getBusinessClass().getName() + " as obj " + "where true = true ";

    for (Field field : criteria) {
      try {
        String fieldName = field.getName();
        Object fieldValue = field.get(criteria);

        if (fieldValue != null) {
          queryStr +=
              "and obj."
                  + fieldName
                  + " "
                  + field.getAnnotation(Criteria.class).type().getFormattedText()
                  + " "
                  + ":"
                  + fieldName
                  + " ";
        }
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    if (sortCriteria != null) {
      Collection<SortCriteria.SortEntry> entries = sortCriteria.getEntries().values();

      if (entries.size() > 0) {
        boolean isSorting = false;
        String orderByClause = "order by ";

        Iterator<SortCriteria.SortEntry> entryIterator = entries.iterator();

        while (entryIterator.hasNext()) {
          SortCriteria.SortEntry entry = entryIterator.next();
          String fieldName = entry.getFieldName();

          switch (entry.getType()) {
            case SortCriteria.SortEntry.TYPE_ASCENDING:
              orderByClause += "obj." + fieldName + " asc";
              isSorting = true;
              break;

            case SortCriteria.SortEntry.TYPE_DESCENDING:
              orderByClause += "obj." + fieldName + " desc";
              isSorting = true;
              break;
          }

          if (entryIterator.hasNext()) orderByClause += ", ";
        }

        if (isSorting) queryStr += orderByClause;
      }
    }

    Query query = getPersistenceContext().createQuery(queryStr);

    for (Field field : criteria) {
      try {
        String fieldName = field.getName();
        Object fieldValue = field.get(criteria);

        query.setParameter(
            fieldName,
            (field.getAnnotation(Criteria.class).type() == EnumCriteriaType.LIKE
                ? "%" + fieldValue + "%"
                : fieldValue));
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    if (paging != null) {
      int firstResult = (paging.getPageNumber() - 1) * paging.getResultsPerPage();

      query.setFirstResult(firstResult);
      query.setMaxResults(paging.getResultsPerPage());
    }

    return query.getResultList();
  }

  @Override
  public T retrieve(String key) {
    return this.getPersistenceContext().find(this.getBusinessClass(), key);
  }

  @Override
  public Class<T> getBusinessClass() {
    return this.clazz;
  }

  @Override
  public EntityManager getPersistenceContext() {
    return em;
  }

  @Override
  public T retrieveBySecondaryKey(String ident) {
    final String jpql =
        "SELECT c FROM " + clazz.getSimpleName() + " c WHERE LOWER(c.ident) = LOWER(:ident)";
    TypedQuery<T> query = this.getPersistenceContext().createQuery(jpql, clazz);
    query.setParameter("ident", ident);
    List retorno = (List) query.getResultList();
    if (retorno != null) {
      if (!retorno.isEmpty()) {
        // System.out.println("caiu na consulta"+retorno.get(0));
        return (T) retorno.get(0);
      } else {
        return null;
      }
    } else return null;
  }

  @Override
  public String generateIdent(String oldIdent) {
    return ConversorDeIdent.de(oldIdent)
        .para(new SemCaracteresEspeciais())
        .para(new TrocaEspacoPorPonto())
        .ident();
  }

  @Override
  public String generateIdent(String oldIdent, T t) {
    oldIdent = this.concatOidOnString(oldIdent, t);
    return ConversorDeIdent.de(oldIdent)
        .para(new SemCaracteresEspeciais())
        .para(new TrocaEspacoPorPonto())
        .ident();
  }

  private String concatOidOnString(String oldIdent, T t) {
    Method method = null;
    String oid = "";
    try {
      method = t.getClass().getMethod("getId", new Class<?>[0]);
      oid = method.invoke(t, new Object[0]).toString();
      oid = oid.concat(" " + oldIdent);
    } catch (NoSuchMethodException e) {
      System.out.println(
          "O método oid não foi encontrado para a entidade " + t.getClass().getSimpleName());
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return oid;
  }
}
