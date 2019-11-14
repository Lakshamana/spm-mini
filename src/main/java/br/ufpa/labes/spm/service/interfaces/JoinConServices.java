package br.ufpa.labes.spm.service.interfaces;

import br.ufpa.labes.spm.domain.JoinCon;
import br.ufpa.labes.spm.service.dto.JoinConDTO;

/** JoinConServices */
public interface JoinConServices {
  JoinCon updateJoinCon(JoinConDTO joinConDTO);
}
