package br.ufpa.labes.spm.repository.impl.processModels;

import br.ufpa.labes.spm.repository.impl.BaseRepositoryQueryImpl;
import br.ufpa.labes.spm.repository.interfaces.processModels.ProcessModelRepositoryQuery;
import br.ufpa.labes.spm.repository.interfaces.BaseRepositoryQuery;
import br.ufpa.labes.spm.domain.ProcessModel;

public class ProcessModelRepositoryQueryImpl extends BaseRepositoryQueryImpl<ProcessModel, Long> implements ProcessModelRepositoryQuery {

  protected ProcessModelRepositoryQueryImpl(Class<ProcessModel> businessClass) {
    super(businessClass);
  }

  public ProcessModelRepositoryQueryImpl() {
    super(ProcessModel.class);
  }
}
