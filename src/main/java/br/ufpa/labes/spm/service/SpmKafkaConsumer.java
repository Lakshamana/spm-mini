package br.ufpa.labes.spm.service;

import br.ufpa.labes.spm.config.KafkaProperties;
import br.ufpa.labes.spm.service.dto.XMLCellUpdateDTO;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class SpmKafkaConsumer {

  private final Logger log = LoggerFactory.getLogger(SpmKafkaConsumer.class);

  private final AtomicBoolean closed = new AtomicBoolean(false);

  public static final String TOPIC = "topic_spm";

  private final KafkaProperties kafkaProperties;

  private KafkaConsumer<String, XMLCellUpdateDTO> kafkaConsumer;

  private static final Map<String, Pair<SseEmitter, Long>> events = new ConcurrentHashMap<>();

  public SpmKafkaConsumer(final KafkaProperties kafkaProperties) {
    this.kafkaProperties = kafkaProperties;
  }

  public void start() {
    log.info("Kafka consumer starting...");
    this.kafkaConsumer = new KafkaConsumer<>(kafkaProperties.getConsumerProps());
    Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

    final Thread consumerThread =
        new Thread(
            () -> {
              try {
                kafkaConsumer.subscribe(Collections.singletonList(TOPIC));
                log.info("Kafka consumer started");
                while (!closed.get()) {
                  final ConsumerRecords<String, XMLCellUpdateDTO> records =
                      kafkaConsumer.poll(Duration.ofSeconds(1));
                  for (final ConsumerRecord<String, XMLCellUpdateDTO> record : records) {
                    final XMLCellUpdateDTO message = record.value();
                    log.info("Consumed message in {} : {}", TOPIC, message);
                    if (!events.isEmpty()) {
                      events.entrySet().stream()
                          .filter(
                              entry ->
                                  entry.getValue().getSecond().equals(message.getProcessModelId())
                                      && !entry.getKey().equals(message.getUsername()))
                          .forEach(
                              entry -> {
                                try {
                                  log.debug("Sending message: {}", message);
                                  entry.getValue().getFirst().send(message);
                                } catch (IOException e) {
                                }
                              });
                    }
                  }
                }
              } catch (final WakeupException e) {
                // Ignore exception if closing
                if (!closed.get()) throw e;
              } catch (final Exception e) {
                log.error(e.getMessage(), e);
              } finally {
                kafkaConsumer.close();
              }
            });
    consumerThread.start();
  }

  public KafkaConsumer<String, XMLCellUpdateDTO> getKafkaConsumer() {
    return kafkaConsumer;
  }

  /**
   * Returns Map<String, Pair<SseEmitter, Long>>, where key refers to username and long value in
   * Pair refers to processModelId
   *
   * @return {@code} Map<String, Pair<SseEmitter, Long>>
   */
  @Bean
  public Map<String, Pair<SseEmitter, Long>> getEvents() {
    return events;
  }

  public void shutdown() {
    log.info("Shutdown Kafka consumer");
    closed.set(true);
    kafkaConsumer.wakeup();
  }

  public AtomicBoolean getClosed() {
    return this.closed;
  }
}
