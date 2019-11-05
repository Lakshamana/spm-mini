package br.ufpa.labes.spm.web.rest.nopersistent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufpa.labes.spm.beans.editor.CoordinateRequestBean;
import br.ufpa.labes.spm.domain.WebAPSEEObject;
import br.ufpa.labes.spm.service.interfaces.EasyModelingServices;

@RestController
@RequestMapping("/api")
public class EasyModelingResource {

  @Autowired private EasyModelingServices easyModelingServices;

  @PostMapping("/easy-modeling")
  public ResponseEntity<?> getCoordinatesResponse(@RequestBody CoordinateRequestBean bean) {
    WebAPSEEObject obj = easyModelingServices.getCoordinatesResponse(bean);
    return obj != null ? ResponseEntity.ok(obj) : ResponseEntity.badRequest().build();
  }
}
