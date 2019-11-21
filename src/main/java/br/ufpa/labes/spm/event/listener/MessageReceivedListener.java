package br.ufpa.labes.spm.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import br.ufpa.labes.spm.event.MessageReceivedEvent;

/** MessageReceiveListener */
@Component
public class MessageReceivedListener implements ApplicationListener<MessageReceivedEvent> {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public void onApplicationEvent(MessageReceivedEvent event) {
    log.debug("Received message from event: {}", event.getSource());
    log.debug("Flux: {}", event.getFlux());
  }
}
