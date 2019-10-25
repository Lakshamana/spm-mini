package br.ufpa.labes.spm.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

  private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

  public CacheConfiguration(JHipsterProperties jHipsterProperties) {
    JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

    jcacheConfiguration =
        Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(
                    Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(
                    ExpiryPolicyBuilder.timeToLiveExpiration(
                        Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
  }

  @Bean
  public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(
      javax.cache.CacheManager cacheManager) {
    return hibernateProperties ->
        hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
  }

  @Bean
  public JCacheManagerCustomizer cacheManagerCustomizer() {
    return cm -> {
      createCache(cm, br.ufpa.labes.spm.repository.UserRepository.USERS_BY_LOGIN_CACHE);
      createCache(cm, br.ufpa.labes.spm.repository.UserRepository.USERS_BY_EMAIL_CACHE);
      createCache(cm, br.ufpa.labes.spm.domain.User.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Authority.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.User.class.getName() + ".authorities");
      createCache(cm, br.ufpa.labes.spm.domain.Activity.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Activity.class.getName() + ".hasVersions");
      createCache(cm, br.ufpa.labes.spm.domain.Activity.class.getName() + ".fromSimpleCons");
      createCache(cm, br.ufpa.labes.spm.domain.Activity.class.getName() + ".toSimpleCons");
      createCache(cm, br.ufpa.labes.spm.domain.Activity.class.getName() + ".fromJoinCons");
      createCache(cm, br.ufpa.labes.spm.domain.Activity.class.getName() + ".toBranchCons");
      createCache(cm, br.ufpa.labes.spm.domain.Activity.class.getName() + ".toJoinCons");
      createCache(cm, br.ufpa.labes.spm.domain.Activity.class.getName() + ".fromBranchANDCons");
      createCache(cm, br.ufpa.labes.spm.domain.Activity.class.getName() + ".fromArtifactCons");
      createCache(cm, br.ufpa.labes.spm.domain.Activity.class.getName() + ".toArtifactCons");
      createCache(
          cm, br.ufpa.labes.spm.domain.Activity.class.getName() + ".theBranchConCondToActivities");
      createCache(cm, br.ufpa.labes.spm.domain.Decomposed.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Plain.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Agent.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Agent.class.getName() + ".theReqAgents");
      createCache(cm, br.ufpa.labes.spm.domain.Agent.class.getName() + ".theProcesses");
      createCache(cm, br.ufpa.labes.spm.domain.Agent.class.getName() + ".theWorkGroups");
      createCache(cm, br.ufpa.labes.spm.domain.WorkGroup.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.WorkGroup.class.getName() + ".theReqGroups");
      createCache(cm, br.ufpa.labes.spm.domain.WorkGroup.class.getName() + ".subGroups");
      createCache(cm, br.ufpa.labes.spm.domain.WorkGroup.class.getName() + ".theAgents");
      createCache(cm, br.ufpa.labes.spm.domain.Artifact.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Artifact.class.getName() + ".derivedTos");
      createCache(cm, br.ufpa.labes.spm.domain.Artifact.class.getName() + ".possesses");
      createCache(cm, br.ufpa.labes.spm.domain.Artifact.class.getName() + ".theArtifactCons");
      createCache(cm, br.ufpa.labes.spm.domain.Connection.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.ArtifactCon.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.ArtifactCon.class.getName() + ".toMultipleCons");
      createCache(cm, br.ufpa.labes.spm.domain.ArtifactCon.class.getName() + ".toActivities");
      createCache(cm, br.ufpa.labes.spm.domain.ArtifactCon.class.getName() + ".fromActivities");
      createCache(cm, br.ufpa.labes.spm.domain.SimpleCon.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.JoinCon.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.JoinCon.class.getName() + ".fromMultipleCons");
      createCache(cm, br.ufpa.labes.spm.domain.JoinCon.class.getName() + ".fromActivities");
      createCache(cm, br.ufpa.labes.spm.domain.MultipleCon.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.MultipleCon.class.getName() + ".toBranchCons");
      createCache(
          cm,
          br.ufpa.labes.spm.domain.MultipleCon.class.getName() + ".theBranchConCondToMultipleCons");
      createCache(cm, br.ufpa.labes.spm.domain.MultipleCon.class.getName() + ".theJoinCons");
      createCache(cm, br.ufpa.labes.spm.domain.MultipleCon.class.getName() + ".fromArtifactCons");
      createCache(cm, br.ufpa.labes.spm.domain.MultipleCon.class.getName() + ".theBranchANDCons");
      createCache(
          cm, br.ufpa.labes.spm.domain.MultipleCon.class.getName() + ".theJoinConToMultipleCons");
      createCache(cm, br.ufpa.labes.spm.domain.BranchCon.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.BranchANDCon.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.BranchANDCon.class.getName() + ".toMultipleCons");
      createCache(cm, br.ufpa.labes.spm.domain.BranchANDCon.class.getName() + ".toActivities");
      createCache(cm, br.ufpa.labes.spm.domain.BranchConCond.class.getName());
      createCache(
          cm,
          br.ufpa.labes.spm.domain.BranchConCond.class.getName() + ".theBranchConCondToActivities");
      createCache(
          cm,
          br.ufpa.labes.spm.domain.BranchConCond.class.getName()
              + ".theBranchConCondToMultipleCons");
      createCache(cm, br.ufpa.labes.spm.domain.BranchConCondToActivity.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.BranchConCondToMultipleCon.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Normal.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.RequiredPeople.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.ReqAgent.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.ReqWorkGroup.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.GraphicCoordinate.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.WebAPSEEObject.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Process.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Process.class.getName() + ".theAgents");
      createCache(cm, br.ufpa.labes.spm.domain.ProcessModel.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.ProcessModel.class.getName() + ".theActivities");
      createCache(cm, br.ufpa.labes.spm.domain.ProcessModel.class.getName() + ".theConnections");
      createCache(cm, br.ufpa.labes.spm.domain.Template.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Template.class.getName() + ".theInstances");
      createCache(cm, br.ufpa.labes.spm.domain.Project.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Project.class.getName() + ".finalArtifacts");
      createCache(cm, br.ufpa.labes.spm.domain.Process.class.getName() + ".theProjects");
      createCache(cm, br.ufpa.labes.spm.domain.Artifact.class.getName() + ".theInvolvedArtifacts");
      createCache(cm, br.ufpa.labes.spm.domain.InvolvedArtifact.class.getName());
      createCache(
          cm, br.ufpa.labes.spm.domain.Normal.class.getName() + ".involvedArtifactToNormals");
      createCache(
          cm, br.ufpa.labes.spm.domain.Normal.class.getName() + ".involvedArtifactFromNormals");
      createCache(cm, br.ufpa.labes.spm.domain.Normal.class.getName() + ".theRequiredPeople");
      createCache(cm, br.ufpa.labes.spm.domain.Feedback.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Sequence.class.getName());
      createCache(cm, br.ufpa.labes.spm.domain.Dependency.class.getName());
      createCache(
          cm,
          br.ufpa.labes.spm.domain.Dependency.class.getName() + ".theMultipleConsToDependencies");
      createCache(
          cm, br.ufpa.labes.spm.domain.Dependency.class.getName() + ".theMultipleSequences");
      // jhipster-needle-ehcache-add-entry
    };
  }

  private void createCache(javax.cache.CacheManager cm, String cacheName) {
    javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
    if (cache != null) {
      cm.destroyCache(cacheName);
    }
    cm.createCache(cacheName, jcacheConfiguration);
  }
}
