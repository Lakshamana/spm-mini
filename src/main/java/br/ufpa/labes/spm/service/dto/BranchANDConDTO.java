package br.ufpa.labes.spm.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.ufpa.labes.spm.domain.Activity;
import br.ufpa.labes.spm.domain.MultipleCon;

import java.util.Objects;

/** A DTO for the {@link br.ufpa.labes.spm.domain.BranchANDCon} entity. */
public class BranchANDConDTO implements Serializable {

  private Long id;

  private MultipleCon fromMultipleConnection;
  private Activity fromActivity;

  private Set<MultipleCon> toMultipleCons = new HashSet<>();
  private Set<Activity> toActivities = new HashSet<>();

  public BranchANDConDTO() {}

  public BranchANDConDTO(
      Long id,
      MultipleCon fromMultipleConnection,
      Activity fromActivity,
      Set<MultipleCon> toMultipleCons,
      Set<Activity> toActivities) {
    this.id = id;
    this.fromMultipleConnection = fromMultipleConnection;
    this.fromActivity = fromActivity;
    this.toMultipleCons = toMultipleCons;
    this.toActivities = toActivities;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MultipleCon getFromMultipleConnection() {
    return this.fromMultipleConnection;
  }

  public void setFromMultipleConnection(MultipleCon fromMultipleConnection) {
    this.fromMultipleConnection = fromMultipleConnection;
  }

  public Activity getFromActivity() {
    return this.fromActivity;
  }

  public void setFromActivity(Activity fromActivity) {
    this.fromActivity = fromActivity;
  }

  public Set<MultipleCon> getToMultipleCons() {
    return this.toMultipleCons;
  }

  public void setToMultipleCons(Set<MultipleCon> toMultipleCons) {
    this.toMultipleCons = toMultipleCons;
  }

  public Set<Activity> getToActivities() {
    return this.toActivities;
  }

  public void setToActivities(Set<Activity> toActivities) {
    this.toActivities = toActivities;
  }

  public BranchANDConDTO id(Long id) {
    this.id = id;
    return this;
  }

  public BranchANDConDTO fromMultipleConnection(MultipleCon fromMultipleConnection) {
    this.fromMultipleConnection = fromMultipleConnection;
    return this;
  }

  public BranchANDConDTO fromActivity(Activity fromActivity) {
    this.fromActivity = fromActivity;
    return this;
  }

  public BranchANDConDTO toMultipleCons(Set<MultipleCon> toMultipleCons) {
    this.toMultipleCons = toMultipleCons;
    return this;
  }

  public BranchANDConDTO toActivities(Set<Activity> toActivities) {
    this.toActivities = toActivities;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof BranchANDConDTO)) {
      return false;
    }
    BranchANDConDTO branchANDConDTO = (BranchANDConDTO) o;
    return Objects.equals(id, branchANDConDTO.id)
        && Objects.equals(fromMultipleConnection, branchANDConDTO.fromMultipleConnection)
        && Objects.equals(fromActivity, branchANDConDTO.fromActivity)
        && Objects.equals(toMultipleCons, branchANDConDTO.toMultipleCons)
        && Objects.equals(toActivities, branchANDConDTO.toActivities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fromMultipleConnection, fromActivity, toMultipleCons, toActivities);
  }

  @Override
  public String toString() {
    return "{"
        + " id='"
        + getId()
        + "'"
        + ", fromMultipleConnection='"
        + getFromMultipleConnection()
        + "'"
        + ", fromActivity='"
        + getFromActivity()
        + "'"
        + ", toMultipleCons='"
        + getToMultipleCons()
        + "'"
        + ", toActivities='"
        + getToActivities()
        + "'"
        + "}";
  }
}
