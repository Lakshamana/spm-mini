package br.ufpa.labes.spm.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufpa.labes.spm.repository.interfaces.ApseeObjectRepositoryQuery;

public class ApseeObjectRepositoryQueryImpl implements ApseeObjectRepositoryQuery {

  @PersistenceContext
  private EntityManager em;

  public <T> T retrieve(Class<T> classe, String key) {
    return this.em.find(classe, key);
  }
}
