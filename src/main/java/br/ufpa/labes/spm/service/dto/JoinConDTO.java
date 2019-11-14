package br.ufpa.labes.spm.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import br.ufpa.labes.spm.domain.Activity;
import br.ufpa.labes.spm.domain.MultipleCon;
import br.ufpa.labes.spm.domain.ProcessModel;

import java.util.Objects;

/** A DTO for the {@link br.ufpa.labes.spm.domain.JoinCon} entity. */
public class JoinConDTO implements Serializable {

  private Long id;

  @NotNull private String ident;

  private String kindJoin;

  private MultipleCon toMultipleCon;
  private Activity toActivity;

  private Set<MultipleCon> fromMultipleCons = new HashSet<>();
  private Set<Activity> fromActivities = new HashSet<>();

  @NotNull private ProcessModel theProcessModel;

  public JoinConDTO() {}

  public JoinConDTO(Long id, String ident, String kindJoin, MultipleCon toMultipleCon, Activity toActivity, Set<MultipleCon> fromMultipleCons, Set<Activity> fromActivities, ProcessModel theProcessModel) {
    this.id = id;
    this.ident = ident;
    this.kindJoin = kindJoin;
    this.toMultipleCon = toMultipleCon;
    this.toActivity = toActivity;
    this.fromMultipleCons = fromMultipleCons;
    this.fromActivities = fromActivities;
    this.theProcessModel = theProcessModel;
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

  public String getKindJoin() {
    return this.kindJoin;
  }

  public void setKindJoin(String kindJoin) {
    this.kindJoin = kindJoin;
  }

  public MultipleCon getToMultipleCon() {
    return this.toMultipleCon;
  }

  public void setToMultipleCon(MultipleCon toMultipleCon) {
    this.toMultipleCon = toMultipleCon;
  }

  public Activity getToActivity() {
    return this.toActivity;
  }

  public void setToActivity(Activity toActivity) {
    this.toActivity = toActivity;
  }

  public Set<MultipleCon> getFromMultipleCons() {
    return this.fromMultipleCons;
  }

  public void setFromMultipleCons(Set<MultipleCon> fromMultipleCons) {
    this.fromMultipleCons = fromMultipleCons;
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

  public JoinConDTO id(Long id) {
    this.id = id;
    return this;
  }

  public JoinConDTO ident(String ident) {
    this.ident = ident;
    return this;
  }

  public JoinConDTO kindJoin(String kindJoin) {
    this.kindJoin = kindJoin;
    return this;
  }

  public JoinConDTO toMultipleCon(MultipleCon toMultipleCon) {
    this.toMultipleCon = toMultipleCon;
    return this;
  }

  public JoinConDTO toActivity(Activity toActivity) {
    this.toActivity = toActivity;
    return this;
  }

  public JoinConDTO fromMultipleCons(Set<MultipleCon> fromMultipleCons) {
    this.fromMultipleCons = fromMultipleCons;
    return this;
  }

  public JoinConDTO fromActivities(Set<Activity> fromActivities) {
    this.fromActivities = fromActivities;
    return this;
  }

  public JoinConDTO theProcessModel(ProcessModel theProcessModel) {
    this.theProcessModel = theProcessModel;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JoinConDTO)) {
            return false;
        }
        JoinConDTO joinConDTO = (JoinConDTO) o;
        return Objects.equals(id, joinConDTO.id) && Objects.equals(ident, joinConDTO.ident) && Objects.equals(kindJoin, joinConDTO.kindJoin) && Objects.equals(toMultipleCon, joinConDTO.toMultipleCon) && Objects.equals(toActivity, joinConDTO.toActivity) && Objects.equals(fromMultipleCons, joinConDTO.fromMultipleCons) && Objects.equals(fromActivities, joinConDTO.fromActivities) && Objects.equals(theProcessModel, joinConDTO.theProcessModel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ident, kindJoin, toMultipleCon, toActivity, fromMultipleCons, fromActivities, theProcessModel);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", ident='" + getIdent() + "'" +
      ", kindJoin='" + getKindJoin() + "'" +
      ", toMultipleCon='" + getToMultipleCon() + "'" +
      ", toActivity='" + getToActivity() + "'" +
      ", fromMultipleCons='" + getFromMultipleCons() + "'" +
      ", fromActivities='" + getFromActivities() + "'" +
      ", theProcessModel='" + getTheProcessModel() + "'" +
      "}";
  }
}
