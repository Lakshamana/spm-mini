package br.ufpa.labes.spm.repository.interfaces;

import br.ufpa.labes.spm.service.util.SimpleActivityQueryResult;

public interface ProcessRepositoryQuery {

  public SimpleActivityQueryResult[] getAllNormalActivitiesFromProcess(String processIdent);

  public SimpleActivityQueryResult[] getAllActivitiesFromProcess(String processIdent);
}
