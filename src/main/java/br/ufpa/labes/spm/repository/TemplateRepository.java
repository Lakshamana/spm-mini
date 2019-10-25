package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.Template;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/** Spring Data repository for the Template entity. */
@SuppressWarnings("unused")
@Repository
public interface TemplateRepository extends GenericRepository<Template, Long> {}
