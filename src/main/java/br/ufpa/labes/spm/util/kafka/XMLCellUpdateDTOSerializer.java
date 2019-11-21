package br.ufpa.labes.spm.util.kafka;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpa.labes.spm.service.dto.XMLCellUpdateDTO;

/** XMLCellUpdateDTOSerializer */
public class XMLCellUpdateDTOSerializer implements Serializer<XMLCellUpdateDTO> {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public byte[] serialize(String topic, XMLCellUpdateDTO data) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsBytes((XMLCellUpdateDTO) data);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }
}
