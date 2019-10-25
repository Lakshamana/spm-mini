package br.ufpa.labes.spm.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufpa.labes.spm.repository.interfaces.ReqAgentRepositoryQuery;
import br.ufpa.labes.spm.domain.ReqAgent;

public class ReqAgentRepositoryQueryImpl implements ReqAgentRepositoryQuery {

  @PersistenceContext
  private EntityManager em;

  public ReqAgent findReqAgentFromProcessModel(
      String agentIdent, String roleIdent, String normalIdent) {
    List<ReqAgent> retorno = null;
    if (agentIdent != null && !agentIdent.equals("")) {

      String hql =
          "select obj from "
              + ReqAgent.class.getName()
              + " as obj where obj.theAgent.ident=:agentIdent AND obj.theRole.ident=:roleIdent AND obj.theNormal.ident=:normalIdent";
      Query query = em.createQuery(hql);
      query.setParameter("agentIdent", agentIdent);
      query.setParameter("roleIdent", roleIdent);
      query.setParameter("normalIdent", normalIdent);

      retorno = (List<ReqAgent>) query.getResultList();
    } else {

      String hql =
          "select obj from "
              + ReqAgent.class.getName()
              + " as obj where obj.theRole.ident=:roleIdent AND obj.theNormal.ident=:normalIdent";
      Query query = em.createQuery(hql);
      query.setParameter("roleIdent", roleIdent);
      query.setParameter("normalIdent", normalIdent);

      retorno = (List<ReqAgent>) query.getResultList();
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
