package br.ufpa.labes.spm.web.rest;

import br.ufpa.labes.spm.service.SpmKafkaConsumer;
import br.ufpa.labes.spm.service.SpmKafkaProducer;
import br.ufpa.labes.spm.service.dto.XMLCellUpdateDTO;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/spm-kafka")
public class SpmKafkaResource {

  private final Logger log = LoggerFactory.getLogger(SpmKafkaResource.class);

  private SpmKafkaProducer kafkaProducer;

  private SpmKafkaConsumer kafkaConsumer;

  private final Long TOTAL_MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;

  public SpmKafkaResource(SpmKafkaProducer kafkaProducer, SpmKafkaConsumer kafkaConsumer) {
    this.kafkaProducer = kafkaProducer;
    this.kafkaConsumer = kafkaConsumer;
  }

  @PostMapping("/publish")
  public void sendMessageToKafkaTopic(@RequestBody XMLCellUpdateDTO message) {
    log.debug("REST request to send to Kafka topic the message : {}", message);
    this.kafkaProducer.send(message);
  }

  // pmId stands for processModelId
  @GetMapping(value = "/subscribe/{pmId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  SseEmitter subscribe(@PathVariable Long pmId, HttpServletRequest request) {
    SseEmitter sseEmitter = new SseEmitter(TOTAL_MILLIS_PER_DAY);
    String key = request.getHeader("Authorization");
    key = key.replace("Bearer ", "");
    kafkaConsumer.getEvents().put(key, Pair.of(sseEmitter, pmId));
    return sseEmitter;
  }

  @GetMapping("/unsubscribe")
  @ResponseStatus(HttpStatus.OK)
  void unsubscribe(HttpServletRequest request) {
    String key = request.getHeader("Authorization");
    key = key.replace("Bearer ", "");
    SseEmitter emitter = kafkaConsumer.getEvents().get(key).getFirst();
    emitter.complete();
    kafkaConsumer.getEvents().remove(key);
  }
}
