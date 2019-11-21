package br.ufpa.labes.spm.event;

import org.springframework.context.ApplicationEvent;

import reactor.core.publisher.Flux;

/** MessageReceivedEvent */
public class MessageReceivedEvent extends ApplicationEvent {

  private Flux<String> flux;

  public MessageReceivedEvent(String source) {
    super(source);
  }

  public Flux<String> getFlux() {
    return flux;
  }

  public void setFlux(Flux<String> flux) {
    this.flux = flux;
  }
}
