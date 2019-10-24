package br.ufpa.labes.spm.web.rest.nopersistent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufpa.labes.spm.beans.editor.CoordinateRequestBean;
import br.ufpa.labes.spm.service.interfaces.EasyModelingServices;

@RestController
@RequestMapping("/easyModeling")
public class EasyModelingResource {

  @Autowired
  private EasyModelingServices easyModelingServices;

  @PostMapping
  public ResponseEntity<?> getCoordinatesResponse(@RequestBody CoordinateRequestBean bean) {
    easyModelingServices.getCoordinatesResponse(bean.getProcessId(), bean.getIdents(), bean.getXs(),
      bean.getYs(), bean.getTypes(), bean.getNodeTypes(), bean.getReferredObjs()
    );
    return ResponseEntity.ok().build();
  }
}
