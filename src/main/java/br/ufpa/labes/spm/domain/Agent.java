package br.ufpa.labes.spm.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Agent.
 */
@Entity
@Table(name = "agent")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Agent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "ident", unique = true)
    private String ident;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "cost_hour")
    private Float costHour;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "tipo_user")
    private Integer tipoUser;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "online")
    private Boolean online;

    @Column(name = "photo_url")
    private String photoURL;

    @Column(name = "upload")
    private String upload;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "theAgent")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ReqAgent> theReqAgents = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "agent_the_process",
               joinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "the_process_id", referencedColumnName = "id"))
    private Set<Process> theProcesses = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "agent_the_work_group",
               joinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "the_work_group_id", referencedColumnName = "id"))
    private Set<WorkGroup> theWorkGroups = new HashSet<>();

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

    public Agent ident(String ident) {
        this.ident = ident;
        return this;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getName() {
        return name;
    }

    public Agent name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Agent email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getCostHour() {
        return costHour;
    }

    public Agent costHour(Float costHour) {
        this.costHour = costHour;
        return this;
    }

    public void setCostHour(Float costHour) {
        this.costHour = costHour;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Agent passwordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Integer getTipoUser() {
        return tipoUser;
    }

    public Agent tipoUser(Integer tipoUser) {
        this.tipoUser = tipoUser;
        return this;
    }

    public void setTipoUser(Integer tipoUser) {
        this.tipoUser = tipoUser;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Agent isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean isOnline() {
        return online;
    }

    public Agent online(Boolean online) {
        this.online = online;
        return this;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public Agent photoURL(String photoURL) {
        this.photoURL = photoURL;
        return this;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getUpload() {
        return upload;
    }

    public Agent upload(String upload) {
        this.upload = upload;
        return this;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getDescription() {
        return description;
    }

    public Agent description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ReqAgent> getTheReqAgents() {
        return theReqAgents;
    }

    public Agent theReqAgents(Set<ReqAgent> reqAgents) {
        this.theReqAgents = reqAgents;
        return this;
    }

    public Agent addTheReqAgent(ReqAgent reqAgent) {
        this.theReqAgents.add(reqAgent);
        reqAgent.setTheAgent(this);
        return this;
    }

    public Agent removeTheReqAgent(ReqAgent reqAgent) {
        this.theReqAgents.remove(reqAgent);
        reqAgent.setTheAgent(null);
        return this;
    }

    public void setTheReqAgents(Set<ReqAgent> reqAgents) {
        this.theReqAgents = reqAgents;
    }

    public Set<Process> getTheProcesses() {
        return theProcesses;
    }

    public Agent theProcesses(Set<Process> processes) {
        this.theProcesses = processes;
        return this;
    }

    public Agent addTheProcess(Process process) {
        this.theProcesses.add(process);
        process.getTheAgents().add(this);
        return this;
    }

    public Agent removeTheProcess(Process process) {
        this.theProcesses.remove(process);
        process.getTheAgents().remove(this);
        return this;
    }

    public void setTheProcesses(Set<Process> processes) {
        this.theProcesses = processes;
    }

    public Set<WorkGroup> getTheWorkGroups() {
        return theWorkGroups;
    }

    public Agent theWorkGroups(Set<WorkGroup> workGroups) {
        this.theWorkGroups = workGroups;
        return this;
    }

    public Agent addTheWorkGroup(WorkGroup workGroup) {
        this.theWorkGroups.add(workGroup);
        workGroup.getTheAgents().add(this);
        return this;
    }

    public Agent removeTheWorkGroup(WorkGroup workGroup) {
        this.theWorkGroups.remove(workGroup);
        workGroup.getTheAgents().remove(this);
        return this;
    }

    public void setTheWorkGroups(Set<WorkGroup> workGroups) {
        this.theWorkGroups = workGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agent)) {
            return false;
        }
        return id != null && id.equals(((Agent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Agent{" +
            "id=" + getId() +
            ", ident='" + getIdent() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", costHour=" + getCostHour() +
            ", passwordHash='" + getPasswordHash() + "'" +
            ", tipoUser=" + getTipoUser() +
            ", isActive='" + isIsActive() + "'" +
            ", online='" + isOnline() + "'" +
            ", photoURL='" + getPhotoURL() + "'" +
            ", upload='" + getUpload() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
