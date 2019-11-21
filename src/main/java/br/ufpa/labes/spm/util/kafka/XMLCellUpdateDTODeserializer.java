package br.ufpa.labes.spm.util.kafka;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufpa.labes.spm.service.dto.XMLCellUpdateDTO;

/** XMLCellUpdateDTODeserializer */
public class XMLCellUpdateDTODeserializer implements Deserializer<XMLCellUpdateDTO> {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public XMLCellUpdateDTO deserialize(String topic, byte[] data) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(data, XMLCellUpdateDTO.class);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }
}
