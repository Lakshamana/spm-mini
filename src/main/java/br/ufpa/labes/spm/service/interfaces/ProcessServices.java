package br.ufpa.labes.spm.service.interfaces;

import br.ufpa.labes.spm.domain.Process;
import br.ufpa.labes.spm.exceptions.RepositoryQueryException;
import br.ufpa.labes.spm.service.dto.ActivitysDTO;
import br.ufpa.labes.spm.service.dto.ProcessesDTO;

public interface ProcessServices {

  // public ProjectsDTO getProjectsForAgent(String agentIdent);

  // public List<ProcessDTO> getProcess(String agentIdent);

  public Process saveProcess(Process process) throws RepositoryQueryException;

  public ActivitysDTO getActivitiesFromProcess(String processIdent);

  public ProcessesDTO getProjectsManagedBy(String agentIdent);
}
