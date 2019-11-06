package br.ufpa.labes.spm.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/** A GraphicDescription. */
@Entity
@Table(name = "graphic_description")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GraphicDescription implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Lob
  @Column(name = "description")
  private String description;

  @OneToOne
  @JoinColumn(unique = true)
  private ProcessModel theProcessModel;

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public GraphicDescription description(String description) {
    this.description = description;
    return this;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProcessModel getTheProcessModel() {
    return theProcessModel;
  }

  public GraphicDescription theProcessModel(ProcessModel processModel) {
    this.theProcessModel = processModel;
    this.theProcessModel.setGraphicDescription(this);
    return this;
  }

  public void setTheProcessModel(ProcessModel processModel) {
    this.theProcessModel = processModel;
  }
  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not
  // remove

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GraphicDescription)) {
      return false;
    }
    return id != null && id.equals(((GraphicDescription) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return "GraphicDescription{"
        + "id="
        + getId()
        + ", description='"
        + getDescription()
        + "'"
        + "}";
  }
}
