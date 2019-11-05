package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.WebAPSEEObject;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;
import br.ufpa.labes.spm.repository.interfaces.WebAPSEEObjectRepositoryQuery;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Spring Data repository for the WebAPSEEObject entity. */
@SuppressWarnings("unused")
@Repository
public interface WebAPSEEObjectRepository
    extends GenericRepository<WebAPSEEObject, Long>, WebAPSEEObjectRepositoryQuery {
  WebAPSEEObject findOneByTheReferredOid(@Param("theReferredOid") Long theReferredOid);
}
