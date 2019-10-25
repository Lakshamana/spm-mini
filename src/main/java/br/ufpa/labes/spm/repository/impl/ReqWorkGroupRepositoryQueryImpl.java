package br.ufpa.labes.spm.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufpa.labes.spm.repository.impl.BaseRepositoryQueryImpl;
import br.ufpa.labes.spm.repository.interfaces.ReqWorkGroupRepositoryQuery;
import br.ufpa.labes.spm.repository.interfaces.BaseRepositoryQuery;
import br.ufpa.labes.spm.domain.ReqWorkGroup;

public class ReqWorkGroupRepositoryQueryImpl implements ReqWorkGroupRepositoryQuery {

  @PersistenceContext
  private EntityManager em;

  public ReqWorkGroup findReqWorkGroupFromProcessModel(
      String groupIdent, String WorkgroupTypeIdent, String normalIdent) {
    List<ReqWorkGroup> retorno = null;

    if (groupIdent != null && !groupIdent.equals("")) {

      String hql =
          "SELECT ReqWorkGroup FROM "
              + ReqWorkGroup.class.getName()
              + " AS ReqWorkGroup WHERE ReqWorkGroup.theGroup.ident=:groupIdent AND ReqWorkGroup.theGroupType.ident=:WorkgroupTypeIdent AND ReqWorkGroup.theNormal.ident=:normalIdent";
      Query query = em.createQuery(hql);
      query.setParameter("groupIdent", groupIdent);
      query.setParameter("groupTypeIdent", WorkgroupTypeIdent);
      query.setParameter("normalIdent", normalIdent);

      retorno = (List<ReqWorkGroup>) query.getResultList();
    } else {

      String hql =
          "SELECT ReqWorkGroup FROM "
              + ReqWorkGroup.class.getName()
              + " AS ReqWorkGroup WHERE ReqWorkGroup.theGroupType.ident=:WorkgroupTypeIdent AND ReqWorkGroup.theNormal.ident=:normalIdent";
      Query query = em.createQuery(hql);
      query.setParameter("groupTypeIdent", WorkgroupTypeIdent);
      query.setParameter("normalIdent", normalIdent);

      retorno = (List<ReqWorkGroup>) query.getResultList();
    }

    if (retorno != null) {
      if (!retorno.isEmpty()) {
        return retorno.get(0);
      } else {
        return null;
      }
    } else return null;
  }
}
