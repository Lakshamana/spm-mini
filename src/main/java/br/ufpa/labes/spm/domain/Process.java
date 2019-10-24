package br.ufpa.labes.spm.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Process.
 */
@Entity
@Table(name = "process")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Process implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "ident", unique = true)
    private String ident;

    @Column(name = "p_state")
    private String pState;

    @OneToOne
    @JoinColumn(unique = true)
    private ProcessModel theProcessModel;

    @OneToMany(mappedBy = "processRefered")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Project> theProjects = new HashSet<>();

    @ManyToMany(mappedBy = "theProcesses")
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

    public Process ident(String ident) {
        this.ident = ident;
        return this;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getpState() {
        return pState;
    }

    public Process pState(String pState) {
        this.pState = pState;
        return this;
    }

    public void setpState(String pState) {
        this.pState = pState;
    }

    public ProcessModel getTheProcessModel() {
        return theProcessModel;
    }

    public Process theProcessModel(ProcessModel processModel) {
        this.theProcessModel = processModel;
        return this;
    }

    public void setTheProcessModel(ProcessModel processModel) {
        this.theProcessModel = processModel;
    }

    public Set<Project> getTheProjects() {
        return theProjects;
    }

    public Process theProjects(Set<Project> projects) {
        this.theProjects = projects;
        return this;
    }

    public Process addTheProject(Project project) {
        this.theProjects.add(project);
        project.setProcessRefered(this);
        return this;
    }

    public Process removeTheProject(Project project) {
        this.theProjects.remove(project);
        project.setProcessRefered(null);
        return this;
    }

    public void setTheProjects(Set<Project> projects) {
        this.theProjects = projects;
    }

    public Set<Agent> getTheAgents() {
        return theAgents;
    }

    public Process theAgents(Set<Agent> agents) {
        this.theAgents = agents;
        return this;
    }

    public Process addTheAgent(Agent agent) {
        this.theAgents.add(agent);
        agent.getTheProcesses().add(this);
        return this;
    }

    public Process removeTheAgent(Agent agent) {
        this.theAgents.remove(agent);
        agent.getTheProcesses().remove(this);
        return this;
    }

    public void setTheAgents(Set<Agent> agents) {
        this.theAgents = agents;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Process)) {
            return false;
        }
        return id != null && id.equals(((Process) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Process{" +
            "id=" + getId() +
            ", ident='" + getIdent() + "'" +
            ", pState='" + getpState() + "'" +
            "}";
    }
}
