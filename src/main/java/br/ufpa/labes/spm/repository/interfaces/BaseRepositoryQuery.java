package br.ufpa.labes.spm.repository.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.repository.NoRepositoryBean;

import br.ufpa.labes.spm.util.PagingContext;
import br.ufpa.labes.spm.util.SortCriteria;

public interface BaseRepositoryQuery<T, PK> {

  public T update(T object);

  public T retrieve(String key);

  public List<T> retrieveByCriteria(T searchCriteria);

  public List<T> retrieveByCriteria(T searchCriteria, SortCriteria sortCriteria);

  public List<T> retrieveByCriteria(
      T searchCriteria, SortCriteria sortCriteria, PagingContext paging);

  public T retrieveBySecondaryKey(String ident);

  public String generateIdent(String oldIdent);

  public String generateIdent(String oldIdent, T t);

  public Class<T> getBusinessClass();

  public EntityManager getPersistenceContext();
}
