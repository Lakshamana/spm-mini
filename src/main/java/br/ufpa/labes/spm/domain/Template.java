package br.ufpa.labes.spm.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/** A Template. */
@Entity
@Table(name = "template")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Template extends Process implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "template_state")
  private String templateState;

  @OneToMany(mappedBy = "theOrigin")
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<ProcessModel> theInstances = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTemplateState() {
    return templateState;
  }

  public Template templateState(String templateState) {
    this.templateState = templateState;
    return this;
  }

  public void setTemplateState(String templateState) {
    this.templateState = templateState;
  }

  public Set<ProcessModel> getTheInstances() {
    return theInstances;
  }

  public Template theInstances(Set<ProcessModel> processModels) {
    this.theInstances = processModels;
    return this;
  }

  public Template addTheInstances(ProcessModel processModel) {
    this.theInstances.add(processModel);
    processModel.setTheOrigin(this);
    return this;
  }

  public Template removeTheInstances(ProcessModel processModel) {
    this.theInstances.remove(processModel);
    processModel.setTheOrigin(null);
    return this;
  }

  public void setTheInstances(Set<ProcessModel> processModels) {
    this.theInstances = processModels;
  }
  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not
  // remove

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Template)) {
      return false;
    }
    return id != null && id.equals(((Template) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return "Template{" + "id=" + getId() + ", templateState='" + getTemplateState() + "'" + "}";
  }
}
