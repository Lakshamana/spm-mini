package br.ufpa.labes.spm.event;

import org.springframework.context.ApplicationEvent;

/**
 * MessageReceivedEvent
 */
public class MessageReceivedEvent extends ApplicationEvent {

  public MessageReceivedEvent(String source) {
    super(source);
  }

}
