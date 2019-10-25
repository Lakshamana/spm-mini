package br.ufpa.labes.spm.repository.interfaces;

import java.util.Collection;

import br.ufpa.labes.spm.exceptions.RepositoryQueryException;
import br.ufpa.labes.spm.domain.GraphicCoordinate;
import br.ufpa.labes.spm.domain.WebAPSEEObject;

public interface WebAPSEEObjectRepositoryQuery {

  public WebAPSEEObject retrieveWebAPSEEObject(Long theReferredOid, String className)
      throws RepositoryQueryException;

  public Collection<GraphicCoordinate> retrieveProcessCoordinates(String processId)
      throws RepositoryQueryException;
}
