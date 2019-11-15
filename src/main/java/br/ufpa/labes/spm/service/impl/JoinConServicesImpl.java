package br.ufpa.labes.spm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpa.labes.spm.domain.Activity;
import br.ufpa.labes.spm.domain.JoinCon;
import br.ufpa.labes.spm.domain.MultipleCon;
import br.ufpa.labes.spm.repository.ActivityRepository;
import br.ufpa.labes.spm.repository.JoinConRepository;
import br.ufpa.labes.spm.repository.MultipleConRepository;
import br.ufpa.labes.spm.service.dto.JoinConDTO;
import br.ufpa.labes.spm.service.interfaces.JoinConServices;
import br.ufpa.labes.spm.util.ServicesUtil;

/** JoinConServicesImpl */
@Service
@Transactional
public class JoinConServicesImpl implements JoinConServices {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired JoinConRepository joinConRepository;

  @Autowired ActivityRepository activityRepository;

  @Autowired MultipleConRepository multipleConRepository;

  @Override
  public JoinCon updateJoinCon(JoinConDTO joinConDTO) {
    JoinCon joinCon = joinConRepository.findById(joinConDTO.getId()).get();
    if (joinCon == null) return null;

    Long pmid = joinCon.getTheProcessModel().getId();
    List<Activity> activities = activityRepository.findByTheProcessModelId(pmid);
    List<MultipleCon> multipleCons = multipleConRepository.findByTheProcessModelId(pmid);

    joinCon.setToActivity(joinConDTO.getToActivity());
    joinCon.setToMultipleCon(joinConDTO.getToMultipleCon());

    // from activities
    if (joinConDTO.getFromActivities() != null) {
      joinConDTO.getFromActivities().stream()
          .forEach(
              act -> {
                Activity from = ServicesUtil.findActivityById(activities, act.getId());
                joinCon.addFromActivity(from);
                activityRepository.save(from);
              });
    }

    // from multipleCons
    if (joinConDTO.getFromMultipleCons() != null) {
      joinConDTO.getFromMultipleCons().stream()
          .forEach(
              m -> {
                MultipleCon from = ServicesUtil.findMultipleConById(multipleCons, m.getId());
                joinCon.addFromMultipleCon(from);
                multipleConRepository.save(from);
              });
    }
    log.debug("OUT JOINCON: {}", joinCon);
    return joinConRepository.save(joinCon);
  }
}
