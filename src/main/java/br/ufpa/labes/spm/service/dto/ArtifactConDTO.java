package br.ufpa.labes.spm.service.dto;

import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;

import br.ufpa.labes.spm.domain.Activity;
import br.ufpa.labes.spm.domain.Artifact;
import br.ufpa.labes.spm.domain.ArtifactCon;
import br.ufpa.labes.spm.domain.ProcessModel;

/** ArtifactConDTO */
public class ArtifactConDTO {

  private Long id;

  @NotNull private String ident;

  private Set<Activity> toActivities;
  private Set<Activity> fromActivities;

  @NotNull private ProcessModel theProcessModel;

  private Artifact theArtifact;

  public ArtifactConDTO() {}

  public ArtifactConDTO(ArtifactCon artifactCon) {
    this.id = artifactCon.getId();
    this.ident = artifactCon.getIdent();
    this.fromActivities = artifactCon.getFromActivities();
    this.toActivities = artifactCon.getToActivities();
    this.theProcessModel = artifactCon.getTheProcessModel();
    this.theArtifact = artifactCon.getTheArtifact();
  }

  public ArtifactConDTO(
      Long id,
      String ident,
      Set<Activity> toActivities,
      Set<Activity> fromActivities,
      ProcessModel theProcessModel,
      Artifact theArtifact) {
    this.id = id;
    this.ident = ident;
    this.toActivities = toActivities;
    this.fromActivities = fromActivities;
    this.theProcessModel = theProcessModel;
    this.theArtifact = theArtifact;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIdent() {
    return this.ident;
  }

  public void setIdent(String ident) {
    this.ident = ident;
  }

  public Set<Activity> getToActivities() {
    return this.toActivities;
  }

  public void setToActivities(Set<Activity> toActivities) {
    this.toActivities = toActivities;
  }

  public Set<Activity> getFromActivities() {
    return this.fromActivities;
  }

  public void setFromActivities(Set<Activity> fromActivities) {
    this.fromActivities = fromActivities;
  }

  public ProcessModel getTheProcessModel() {
    return this.theProcessModel;
  }

  public void setTheProcessModel(ProcessModel theProcessModel) {
    this.theProcessModel = theProcessModel;
  }

  public Artifact getTheArtifact() {
    return this.theArtifact;
  }

  public void setTheArtifact(Artifact theArtifact) {
    this.theArtifact = theArtifact;
  }

  public ArtifactConDTO id(Long id) {
    this.id = id;
    return this;
  }

  public ArtifactConDTO ident(String ident) {
    this.ident = ident;
    return this;
  }

  public ArtifactConDTO toActivities(Set<Activity> toActivities) {
    this.toActivities = toActivities;
    return this;
  }

  public ArtifactConDTO fromActivities(Set<Activity> fromActivities) {
    this.fromActivities = fromActivities;
    return this;
  }

  public ArtifactConDTO theProcessModel(ProcessModel theProcessModel) {
    this.theProcessModel = theProcessModel;
    return this;
  }

  public ArtifactConDTO theArtifact(Artifact theArtifact) {
    this.theArtifact = theArtifact;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof ArtifactConDTO)) {
      return false;
    }
    ArtifactConDTO artifactConDTO = (ArtifactConDTO) o;
    return Objects.equals(ident, artifactConDTO.ident)
        && Objects.equals(toActivities, artifactConDTO.toActivities)
        && Objects.equals(fromActivities, artifactConDTO.fromActivities)
        && Objects.equals(theProcessModel, artifactConDTO.theProcessModel)
        && Objects.equals(theArtifact, artifactConDTO.theArtifact);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ident, toActivities, fromActivities, theProcessModel, theArtifact);
  }

  @Override
  public String toString() {
    return "{"
        + " ident='"
        + getIdent()
        + "'"
        + ", toActivities='"
        + getToActivities()
        + "'"
        + ", fromActivities='"
        + getFromActivities()
        + "'"
        + ", theProcessModel='"
        + getTheProcessModel()
        + "'"
        + ", theArtifact='"
        + getTheArtifact()
        + "'"
        + "}";
  }
}
