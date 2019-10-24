package br.ufpa.labes.spm.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Artifact.
 */
@Entity
@Table(name = "artifact")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Artifact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "ident", unique = true)
    private String ident;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "path_name")
    private String pathName;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "latest_version")
    private String latestVersion;

    @Column(name = "is_template")
    private Boolean isTemplate;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JsonIgnoreProperties("derivedTos")
    private Artifact derivedFrom;

    @ManyToOne
    @JsonIgnoreProperties("possesses")
    private Artifact belongsTo;

    @ManyToOne
    @JsonIgnoreProperties("finalArtifacts")
    private Project theProject;

    @OneToMany(mappedBy = "derivedFrom")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Artifact> derivedTos = new HashSet<>();

    @OneToMany(mappedBy = "belongsTo")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Artifact> possesses = new HashSet<>();

    @OneToMany(mappedBy = "theArtifact")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ArtifactCon> theArtifactCons = new HashSet<>();

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

    public Artifact ident(String ident) {
        this.ident = ident;
        return this;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getName() {
        return name;
    }

    public Artifact name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Artifact description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathName() {
        return pathName;
    }

    public Artifact pathName(String pathName) {
        this.pathName = pathName;
        return this;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getFileName() {
        return fileName;
    }

    public Artifact fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public Artifact latestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
        return this;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public Boolean isIsTemplate() {
        return isTemplate;
    }

    public Artifact isTemplate(Boolean isTemplate) {
        this.isTemplate = isTemplate;
        return this;
    }

    public void setIsTemplate(Boolean isTemplate) {
        this.isTemplate = isTemplate;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Artifact isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Artifact getDerivedFrom() {
        return derivedFrom;
    }

    public Artifact derivedFrom(Artifact artifact) {
        this.derivedFrom = artifact;
        return this;
    }

    public void setDerivedFrom(Artifact artifact) {
        this.derivedFrom = artifact;
    }

    public Artifact getBelongsTo() {
        return belongsTo;
    }

    public Artifact belongsTo(Artifact artifact) {
        this.belongsTo = artifact;
        return this;
    }

    public void setBelongsTo(Artifact artifact) {
        this.belongsTo = artifact;
    }

    public Project getTheProject() {
        return theProject;
    }

    public Artifact theProject(Project project) {
        this.theProject = project;
        return this;
    }

    public void setTheProject(Project project) {
        this.theProject = project;
    }

    public Set<Artifact> getDerivedTos() {
        return derivedTos;
    }

    public Artifact derivedTos(Set<Artifact> artifacts) {
        this.derivedTos = artifacts;
        return this;
    }

    public Artifact addDerivedTo(Artifact artifact) {
        this.derivedTos.add(artifact);
        artifact.setDerivedFrom(this);
        return this;
    }

    public Artifact removeDerivedTo(Artifact artifact) {
        this.derivedTos.remove(artifact);
        artifact.setDerivedFrom(null);
        return this;
    }

    public void setDerivedTos(Set<Artifact> artifacts) {
        this.derivedTos = artifacts;
    }

    public Set<Artifact> getPossesses() {
        return possesses;
    }

    public Artifact possesses(Set<Artifact> artifacts) {
        this.possesses = artifacts;
        return this;
    }

    public Artifact addPossess(Artifact artifact) {
        this.possesses.add(artifact);
        artifact.setBelongsTo(this);
        return this;
    }

    public Artifact removePossess(Artifact artifact) {
        this.possesses.remove(artifact);
        artifact.setBelongsTo(null);
        return this;
    }

    public void setPossesses(Set<Artifact> artifacts) {
        this.possesses = artifacts;
    }

    public Set<ArtifactCon> getTheArtifactCons() {
        return theArtifactCons;
    }

    public Artifact theArtifactCons(Set<ArtifactCon> artifactCons) {
        this.theArtifactCons = artifactCons;
        return this;
    }

    public Artifact addTheArtifactCon(ArtifactCon artifactCon) {
        this.theArtifactCons.add(artifactCon);
        artifactCon.setTheArtifact(this);
        return this;
    }

    public Artifact removeTheArtifactCon(ArtifactCon artifactCon) {
        this.theArtifactCons.remove(artifactCon);
        artifactCon.setTheArtifact(null);
        return this;
    }

    public void setTheArtifactCons(Set<ArtifactCon> artifactCons) {
        this.theArtifactCons = artifactCons;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Artifact)) {
            return false;
        }
        return id != null && id.equals(((Artifact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Artifact{" +
            "id=" + getId() +
            ", ident='" + getIdent() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", pathName='" + getPathName() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", latestVersion='" + getLatestVersion() + "'" +
            ", isTemplate='" + isIsTemplate() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
