package br.ufpa.labes.spm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpa.labes.spm.domain.Activity;
import br.ufpa.labes.spm.domain.ArtifactCon;
import br.ufpa.labes.spm.repository.ActivityRepository;
import br.ufpa.labes.spm.repository.ArtifactConRepository;
import br.ufpa.labes.spm.service.dto.ArtifactConDTO;
import br.ufpa.labes.spm.service.interfaces.ArtifactConServices;
import br.ufpa.labes.spm.util.ServicesUtil;

/** ArtifactConServicesImpl */
@Service
@Transactional
public class ArtifactConServicesImpl implements ArtifactConServices {

  @Autowired ActivityRepository activityRepository;

  @Autowired ArtifactConRepository artifactConRepository;

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public ArtifactCon updateArtifactCon(ArtifactConDTO artifactConDTO) {
    ArtifactCon artifactCon = artifactConRepository.findById(artifactConDTO.getId()).get();
    if (artifactCon == null) return null;

    Long pmid = artifactCon.getTheProcessModel().getId();
    List<Activity> activities = activityRepository.findByTheProcessModelId(pmid);
    log.debug("ACTIVITIES BY PMID: {}", activities);
    if (artifactConDTO.getFromActivities() != null) {
      artifactConDTO.getFromActivities().stream()
          .forEach(
              act -> {
                Activity from = ServicesUtil.findActivityById(activities, act.getId());
                artifactCon.addFromActivity(from);
                activityRepository.save(from);
              });
    }

    if (artifactConDTO.getToActivities() != null) {
      artifactConDTO.getToActivities().stream()
          .forEach(
              act -> {
                Activity to = ServicesUtil.findActivityById(activities, act.getId());
                artifactCon.addToActivity(to);
                activityRepository.save(to);
              });
    }
    log.debug("OUT ARTIFACTCON: {}", artifactCon);
    return artifactConRepository.save(artifactCon);
  }
}
