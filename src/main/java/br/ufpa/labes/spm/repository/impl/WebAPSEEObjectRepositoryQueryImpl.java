package br.ufpa.labes.spm.repository.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import br.ufpa.labes.spm.repository.interfaces.WebAPSEEObjectRepositoryQuery;
import br.ufpa.labes.spm.exceptions.RepositoryQueryException;
import br.ufpa.labes.spm.domain.GraphicCoordinate;
import br.ufpa.labes.spm.domain.WebAPSEEObject;

public class WebAPSEEObjectRepositoryQueryImpl<T> implements WebAPSEEObjectRepositoryQuery {

  @PersistenceContext
  private EntityManager em;

  public Class<WebAPSEEObject> clazz = WebAPSEEObject.class;

  public WebAPSEEObject retrieveWebAPSEEObject(Long theReferredOid, String className)
      throws RepositoryQueryException {

    Query query =
        this.em
            .createQuery(
                "FROM "
                    + clazz.getName()
                    + " as obj WHERE obj.theReferredOid = :oid AND obj.className like :className");

    query.setParameter("oid", theReferredOid);
    query.setParameter("className", className);

    List retorno = query.getResultList();

    if (retorno != null) {
      if (!retorno.isEmpty()) {
        return (WebAPSEEObject) retorno.get(0);
      } else {
        return null;
      }
    } else return null;
  }

  public Collection<GraphicCoordinate> retrieveProcessCoordinates(String processId)
      throws RepositoryQueryException {
    // We need to use order by clause since it is less complex to remove
    // duplicated elements in a sorted array
    // We use desc to be sure that aways the older coordinate has been
    // removed with iter.remove()
    Query query =
        this.em
            .createQuery(
                "SELECT obj.theGraphicCoordinate FROM "
                    + clazz.getName()
                    + " as obj WHERE obj.theGraphicCoordinate.theProcess=:process ORDER BY obj.theReferredOid desc, obj.className, obj.oid");
    query.setParameter("process", processId);

    List objs = query.getResultList();

    Iterator iter = objs.iterator();
    if (iter.hasNext()) {
      GraphicCoordinate predecessor = (GraphicCoordinate) iter.next();
      while (iter.hasNext()) {
        boolean removed = false;
        GraphicCoordinate gcoord = (GraphicCoordinate) iter.next();
        if (predecessor
            .getTheObjectReference()
            .getTheReferredOid()
            .equals(gcoord.getTheObjectReference().getTheReferredOid())) {
          if (predecessor
              .getTheObjectReference()
              .getClassName()
              .equals(gcoord.getTheObjectReference().getClassName())) {
            iter.remove();
            removed = true;
          }
        }
        if (!removed) predecessor = gcoord;
      }
    }
    return objs;
  }
}
