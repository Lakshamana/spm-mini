package br.ufpa.labes.spm.web.rest;

import br.ufpa.labes.spm.service.SpmKafkaConsumer;
import br.ufpa.labes.spm.service.SpmKafkaProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/spm-kafka")
public class SpmKafkaResource {

  private final Logger log = LoggerFactory.getLogger(SpmKafkaResource.class);

  private SpmKafkaProducer kafkaProducer;

  private SpmKafkaConsumer kafkaConsumer;

  private final Long TOTAL_MILLIS_A_DAY = 24 * 60 * 60 * 1000L;

  public SpmKafkaResource(SpmKafkaProducer kafkaProducer, SpmKafkaConsumer kafkaConsumer) {
    this.kafkaProducer = kafkaProducer;
    this.kafkaConsumer = kafkaConsumer;
  }

  @PostMapping("/publish")
  public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
    log.debug("REST request to send to Kafka topic the message : {}", message);
    this.kafkaProducer.send(message);
  }

  @GetMapping(value = "/subscribe/{topic}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  SseEmitter subscribe(@PathVariable String topic) {
    SseEmitter sseEmitter = new SseEmitter(TOTAL_MILLIS_A_DAY);
    kafkaConsumer.getEvents().put(sseEmitter, topic);
    return sseEmitter;
  }
}
