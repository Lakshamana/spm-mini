package br.ufpa.labes.spm.service;

import br.ufpa.labes.spm.config.KafkaProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SpmKafkaProducer {

  private final Logger log = LoggerFactory.getLogger(SpmKafkaProducer.class);

  private static final String TOPIC = "topic_spm";

  private KafkaProperties kafkaProperties;

  private KafkaProducer<String, String> kafkaProducer;

  public SpmKafkaProducer(KafkaProperties kafkaProperties) {
    this.kafkaProperties = kafkaProperties;
  }

  public void init() {
    log.info("Kafka producer initializing...");
    kafkaProducer = new KafkaProducer<>(kafkaProperties.getProducerProps());
    Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    log.info("Kafka producer initialized");
  }

  public void send(String message) {
    ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, message);
    try {
      kafkaProducer.send(record);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  public void shutdown() {
    log.info("Shutdown Kafka producer");
    kafkaProducer.close();
  }
}
