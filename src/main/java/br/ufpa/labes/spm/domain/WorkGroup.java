package br.ufpa.labes.spm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/** A WorkGroup. */
@Entity
@Table(name = "work_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WorkGroup implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "ident", nullable = false, unique = true)
  private String ident;

  @Column(name = "name")
  private String name;

  @Lob
  @Column(name = "description")
  private String description;

  @Column(name = "is_active")
  private Boolean isActive;

  @OneToMany(mappedBy = "theWorkGroup")
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<ReqWorkGroup> theReqGroups = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties("subGroups")
  private WorkGroup superGroup;

  @OneToMany(mappedBy = "superGroup")
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  private Set<WorkGroup> subGroups = new HashSet<>();

  @ManyToMany(mappedBy = "theWorkGroups")
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @JsonIgnore
  private Set<Agent> theAgents = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIdent() {
    return ident;
  }

  public WorkGroup ident(String ident) {
    this.ident = ident;
    return this;
  }

  public void setIdent(String ident) {
    this.ident = ident;
  }

  public String getName() {
    return name;
  }

  public WorkGroup name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public WorkGroup description(String description) {
    this.description = description;
    return this;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean isIsActive() {
    return isActive;
  }

  public WorkGroup isActive(Boolean isActive) {
    this.isActive = isActive;
    return this;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public Set<ReqWorkGroup> getTheReqGroups() {
    return theReqGroups;
  }

  public WorkGroup theReqGroups(Set<ReqWorkGroup> reqWorkGroups) {
    this.theReqGroups = reqWorkGroups;
    return this;
  }

  public WorkGroup addTheReqGroup(ReqWorkGroup reqWorkGroup) {
    this.theReqGroups.add(reqWorkGroup);
    reqWorkGroup.setTheWorkGroup(this);
    return this;
  }

  public WorkGroup removeTheReqGroup(ReqWorkGroup reqWorkGroup) {
    this.theReqGroups.remove(reqWorkGroup);
    reqWorkGroup.setTheWorkGroup(null);
    return this;
  }

  public void setTheReqGroups(Set<ReqWorkGroup> reqWorkGroups) {
    this.theReqGroups = reqWorkGroups;
  }

  public WorkGroup getSuperGroup() {
    return superGroup;
  }

  public WorkGroup superGroup(WorkGroup workGroup) {
    this.superGroup = workGroup;
    return this;
  }

  public void setSuperGroup(WorkGroup workGroup) {
    this.superGroup = workGroup;
  }

  public Set<WorkGroup> getSubGroups() {
    return subGroups;
  }

  public WorkGroup subGroups(Set<WorkGroup> workGroups) {
    this.subGroups = workGroups;
    return this;
  }

  public WorkGroup addSubGroups(WorkGroup workGroup) {
    this.subGroups.add(workGroup);
    workGroup.setSuperGroup(this);
    return this;
  }

  public WorkGroup removeSubGroups(WorkGroup workGroup) {
    this.subGroups.remove(workGroup);
    workGroup.setSuperGroup(null);
    return this;
  }

  public void setSubGroups(Set<WorkGroup> workGroups) {
    this.subGroups = workGroups;
  }

  public Set<Agent> getTheAgents() {
    return theAgents;
  }

  public WorkGroup theAgents(Set<Agent> agents) {
    this.theAgents = agents;
    return this;
  }

  public WorkGroup addTheAgent(Agent agent) {
    this.theAgents.add(agent);
    agent.getTheWorkGroups().add(this);
    return this;
  }

  public WorkGroup removeTheAgent(Agent agent) {
    this.theAgents.remove(agent);
    agent.getTheWorkGroups().remove(this);
    return this;
  }

  public void setTheAgents(Set<Agent> agents) {
    this.theAgents = agents;
  }
  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not
  // remove

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WorkGroup)) {
      return false;
    }
    return id != null && id.equals(((WorkGroup) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return "WorkGroup{"
        + "id="
        + getId()
        + ", ident='"
        + getIdent()
        + "'"
        + ", name='"
        + getName()
        + "'"
        + ", description='"
        + getDescription()
        + "'"
        + ", isActive='"
        + isIsActive()
        + "'"
        + "}";
  }
}
