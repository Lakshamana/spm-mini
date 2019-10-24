package br.ufpa.labes.spm.repository.interfaces;

public interface ApseeObjectRepositoryQuery {

  public <T> T retrieve(Class<T> classe, String key);
}
