package br.ufpa.labes.spm.beans;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import br.ufpa.labes.spm.repository.impl.GenericRepositoryImpl;

public class GenericRepositoryFactoryBean<
        R extends JpaRepository<T, PK>, T, PK extends Serializable>
    extends JpaRepositoryFactoryBean<R, T, PK> {

  public GenericRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
    super(repositoryInterface);
  }

  @Override
  protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
    return new GenericRepositoryFactory<>(entityManager);
  }

  private static class GenericRepositoryFactory<T, PK extends Serializable>
      extends JpaRepositoryFactory {

    private EntityManager manager;

    public GenericRepositoryFactory(EntityManager entityManager) {
      super(entityManager);
      this.manager = entityManager;
    }

    protected Object getTargetRepository(RepositoryMetadata metadata) {
      return new GenericRepositoryImpl<>(
          getEntityInformation(metadata.getDomainType()), this.manager);
    }

    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

      // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
      // to check for QueryDslJpaRepository's which is out of scope.
      return GenericRepositoryImpl.class;
    }
  }
}
