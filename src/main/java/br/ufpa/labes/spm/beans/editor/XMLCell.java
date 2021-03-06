package br.ufpa.labes.spm.beans.editor;

import java.util.Objects;

public class XMLCell {
  private String nodeType;
  private String label;
  private String objectId;
  private boolean isEdge = false;
  private String style;
  private Object sourceNode;
  private Object targetNode;
  private int x;
  private int y;

  public static final String NORMAL = "Normal",
      DECOMPOSED = "Decomposed",
      REQAGENT = "ReqAgent",
      REQWORKGROUP = "ReqWorkGroup",
      ARTIFACT = "Artifact",
      JOINCON = "JoinCon",
      BRANCHCON = "BranchCon",
      BRANCHANDCON = "BranchANDCon",
      ARTIFACTCON = "ArtifactCon",
      FEEDBACK = "Feedback",
      SEQUENCE = "Sequence",
      CONNECTOR = "Connector";

  public XMLCell() {}

  public XMLCell(String nodeType, String label, Object objectId, boolean isEdge) {
    this.nodeType = nodeType;
    this.label = label;
    this.objectId = String.valueOf(objectId);
    this.style = nodeType.toLowerCase();
    this.isEdge = isEdge;
    this.sourceNode = null;
    this.targetNode = null;
  }

  public XMLCell(String nodeType, String label, Object objectId, String style, boolean isEdge) {
    this.nodeType = nodeType;
    this.label = label;
    this.objectId = String.valueOf(objectId);
    this.style = style;
    this.isEdge = isEdge;
    this.sourceNode = null;
    this.targetNode = null;
  }

  public XMLCell(
      String nodeType,
      String label,
      Object objectId,
      boolean isEdge,
      Object sourceNode,
      Object targetNode) {
    this.nodeType = nodeType;
    this.label = label;
    this.objectId = String.valueOf(objectId);
    this.style = nodeType.toLowerCase();
    this.isEdge = true;
    this.sourceNode = sourceNode;
    this.targetNode = targetNode;
  }

  public String getNodeType() {
    return this.nodeType;
  }

  public void setNodeType(String nodeType) {
    this.nodeType = nodeType;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getobjectId() {
    return this.objectId;
  }

  public void setobjectId(String objectId) {
    this.objectId = objectId;
  }

  public boolean isIsEdge() {
    return this.isEdge;
  }

  public boolean getIsEdge() {
    return this.isEdge;
  }

  public String getStyle() {
    return this.style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public Object getSourceNode() {
    return this.sourceNode;
  }

  public void setSourceNode(Object sourceNode) {
    this.sourceNode = sourceNode;
  }

  public Object getTargetNode() {
    return this.targetNode;
  }

  public void setTargetNode(Object targetNode) {
    this.targetNode = targetNode;
  }

  public void setIsEdge(boolean isEdge) {
    this.isEdge = isEdge;
  }

  public XMLCell nodeType(String nodeType) {
    this.nodeType = nodeType;
    return this;
  }

  public XMLCell label(String label) {
    this.label = label;
    return this;
  }

  public XMLCell objectId(String objectId) {
    this.objectId = objectId;
    return this;
  }

  public XMLCell isEdge(boolean isEdge) {
    this.isEdge = isEdge;
    return this;
  }

  public XMLCell style(String style) {
    this.style = style;
    return this;
  }

  public XMLCell sourceNode(Object sourceNode) {
    this.sourceNode = sourceNode;
    return this;
  }

  public XMLCell targetNode(Object targetNode) {
    this.targetNode = targetNode;
    return this;
  }

  @Override
  public String toString() {
    return "{"
        + " nodeType='"
        + getNodeType()
        + "'"
        + ", label='"
        + getLabel()
        + "'"
        + ", objectId='"
        + getobjectId()
        + "'"
        + ", isEdge='"
        + isIsEdge()
        + "'"
        + ", style='"
        + getStyle()
        + "'"
        + ", sourceNode='"
        + getSourceNode()
        + "'"
        + ", targetNode='"
        + getTargetNode()
        + "'"
        + ", x='"
        + getX()
        + "'"
        + ", y='"
        + getY()
        + "'"
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof XMLCell)) {
      return false;
    }
    XMLCell xMLCell = (XMLCell) o;
    return Objects.equals(nodeType, xMLCell.nodeType)
        && Objects.equals(label, xMLCell.label)
        && Objects.equals(objectId, xMLCell.objectId)
        && isEdge == xMLCell.isEdge
        && Objects.equals(style, xMLCell.style)
        && Objects.equals(sourceNode, xMLCell.sourceNode)
        && Objects.equals(targetNode, xMLCell.targetNode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeType, label, objectId, isEdge, style, sourceNode, targetNode);
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
