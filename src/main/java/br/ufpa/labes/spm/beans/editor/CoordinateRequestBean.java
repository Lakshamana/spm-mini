package br.ufpa.labes.spm.beans.editor;

import java.util.Objects;

/** Applied to editor vertexes only */
public class CoordinateRequestBean {

  private Long processId;
  private Long referedObjectId;
  private String nodeType; // will be mapped to WebAPSEEObject.className property!
  private int x;
  private int y;

  public CoordinateRequestBean() {}

  public CoordinateRequestBean(
      Long processId, Long referedObjectId, String nodeType, int x, int y) {
    this.processId = processId;
    this.referedObjectId = referedObjectId;
    this.nodeType = nodeType;
    this.x = x;
    this.y = y;
  }

  public Long getProcessId() {
    return this.processId;
  }

  public void setProcessId(Long ProcessId) {
    this.processId = ProcessId;
  }

  public Long getReferedObjectId() {
    return this.referedObjectId;
  }

  public void setReferedObjectId(Long referedObjectId) {
    this.referedObjectId = referedObjectId;
  }

  public String getNodeType() {
    return this.nodeType;
  }

  public void setNodeType(String nodeType) {
    this.nodeType = nodeType;
  }

  public int getX() {
    return this.x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return this.y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public CoordinateRequestBean ProcessId(Long ProcessId) {
    this.processId = ProcessId;
    return this;
  }

  public CoordinateRequestBean referedObjectId(Long referedObjectId) {
    this.referedObjectId = referedObjectId;
    return this;
  }

  public CoordinateRequestBean nodeType(String nodeType) {
    this.nodeType = nodeType;
    return this;
  }

  public CoordinateRequestBean x(int x) {
    this.x = x;
    return this;
  }

  public CoordinateRequestBean y(int y) {
    this.y = y;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof CoordinateRequestBean)) {
      return false;
    }
    CoordinateRequestBean coordinateRequestBean = (CoordinateRequestBean) o;
    return Objects.equals(processId, coordinateRequestBean.processId)
        && Objects.equals(referedObjectId, coordinateRequestBean.referedObjectId)
        && Objects.equals(nodeType, coordinateRequestBean.nodeType)
        && x == coordinateRequestBean.x
        && y == coordinateRequestBean.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(processId, referedObjectId, nodeType, x, y);
  }

  @Override
  public String toString() {
    return "{"
        + " ProcessId='"
        + getProcessId()
        + "'"
        + ", referedObjectId='"
        + getReferedObjectId()
        + "'"
        + ", nodeType='"
        + getNodeType()
        + "'"
        + ", x='"
        + getX()
        + "'"
        + ", y='"
        + getY()
        + "'"
        + "}";
  }
}
