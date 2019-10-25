package br.ufpa.labes.spm.repository.interfaces;

import br.ufpa.labes.spm.domain.ReqAgent;

public interface ReqAgentRepositoryQuery {

  public ReqAgent findReqAgentFromProcessModel(
      String agentIdent, String roleIdent, String normalIdent);
}
