package br.ufpa.labes.spm.repository.interfaces;

import br.ufpa.labes.spm.domain.ReqWorkGroup;

public interface ReqWorkGroupRepositoryQuery {

  public ReqWorkGroup findReqWorkGroupFromProcessModel(
      String groupIdent, String WorkgroupTypeIdent, String normalIdent);
}
