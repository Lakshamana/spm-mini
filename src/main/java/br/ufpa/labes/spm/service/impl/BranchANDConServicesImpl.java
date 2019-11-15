package br.ufpa.labes.spm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufpa.labes.spm.domain.Activity;
import br.ufpa.labes.spm.domain.BranchANDCon;
import br.ufpa.labes.spm.domain.MultipleCon;
import br.ufpa.labes.spm.repository.ActivityRepository;
import br.ufpa.labes.spm.repository.BranchANDConRepository;
import br.ufpa.labes.spm.repository.MultipleConRepository;
import br.ufpa.labes.spm.service.dto.BranchANDConDTO;
import br.ufpa.labes.spm.service.interfaces.BranchANDConServices;
import br.ufpa.labes.spm.util.ServicesUtil;

/** BranchANDConServicesImpl */
@Service
@Transactional
public class BranchANDConServicesImpl implements BranchANDConServices {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired private BranchANDConRepository branchandConRepository;

  @Autowired private ActivityRepository activityRepository;

  @Autowired private MultipleConRepository multipleConRepository;

  @Override
  public BranchANDCon updateBranchANDCon(BranchANDConDTO branchandConDTO) {
    BranchANDCon branchandCon = branchandConRepository.findById(branchandConDTO.getId()).get();
    if (branchandCon == null) return null;

    Long pmid = branchandCon.getTheProcessModel().getId();
    List<Activity> activities = activityRepository.findByTheProcessModelId(pmid);
    List<MultipleCon> multipleCons = multipleConRepository.findByTheProcessModelId(pmid);
    log.debug("PROCESS MULTIPLECONS: {}", multipleCons);

    branchandCon.setFromActivity(branchandConDTO.getFromActivity());
    branchandCon.setFromMultipleConnection(branchandConDTO.getFromMultipleConnection());

    // to activities
    if (branchandConDTO.getToActivities() != null) {
      branchandConDTO.getToActivities().stream()
          .forEach(
              act -> {
                Activity to = ServicesUtil.findActivityById(activities, act.getId());
                branchandCon.addToActivity(to);
                activityRepository.save(to);
              });
    }

    // to multipleCons
    if (branchandConDTO.getToMultipleCons() != null) {
      branchandConDTO.getToMultipleCons().stream()
          .forEach(
              m -> {
                MultipleCon to = ServicesUtil.findMultipleConById(multipleCons, m.getId());
                if (to != null) {
                  branchandCon.addToMultipleCon(to);
                  multipleConRepository.save(to);
                }
              });
    }
    log.debug("OUT BRANCHANDCON: {}", branchandCon);
    return branchandConRepository.save(branchandCon);
  }
}
