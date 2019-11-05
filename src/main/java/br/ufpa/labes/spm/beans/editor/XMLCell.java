package br.ufpa.labes.spm.beans.editor;

import java.util.Objects;

public class XMLCell {
  private String nodeType;
  private String label;
  private Long objectId;
  private boolean isEdge = false;
  private String style;
  private String sourceNode;
  private String targetNode;

  public static final String NORMAL = "Normal",
      DECOMPOSED = "Decomposed",
      REQAGENT = "ReqAgent",
      REQWORKGROUP = "ReqWorkGroup",
      ARTIFACT = "Artifact",
      JOINCON = "Join",
      BRANCHCON = "Branch",
      BRANCHANDCON = "BranchAND",
      ARTIFACTCON = "ArtifactCon",
      FEEDBACK = "Feedback",
      SEQUENCE = "Sequence";

  public XMLCell() {}

  public XMLCell(String nodeType, String label, Long objectId, boolean isEdge) {
    this.nodeType = nodeType;
    this.label = label;
    this.objectId = objectId;
    this.style = nodeType.toLowerCase();
    this.isEdge = isEdge;
    this.sourceNode = null;
    this.targetNode = null;
  }

  public XMLCell(String nodeType, String label, Long objectId, String style, boolean isEdge) {
    this.nodeType = nodeType;
    this.label = label;
    this.objectId = objectId;
    this.style = style;
    this.isEdge = isEdge;
    this.sourceNode = null;
    this.targetNode = null;
  }

  public XMLCell(
      String nodeType,
      String label,
      Long objectId,
      String style,
      boolean isEdge,
      String sourceNode,
      String targetNode) {
    this.nodeType = nodeType;
    this.label = label;
    this.objectId = objectId;
    this.style = style;
    this.isEdge = true;
    this.sourceNode = null;
    this.targetNode = null;
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

  public Long getobjectId() {
    return this.objectId;
  }

  public void setobjectId(Long objectId) {
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

  public String getSourceNode() {
    return this.sourceNode;
  }

  public void setSourceNode(String sourceNode) {
    this.sourceNode = sourceNode;
  }

  public String getTargetNode() {
    return this.targetNode;
  }

  public void setTargetNode(String targetNode) {
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

  public XMLCell objectId(Long objectId) {
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

  public XMLCell sourceNode(String sourceNode) {
    this.sourceNode = sourceNode;
    return this;
  }

  public XMLCell targetNode(String targetNode) {
    this.targetNode = targetNode;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof XMLCell)) {
      return false;
    }
    XMLCell xMLNode = (XMLCell) o;
    return Objects.equals(nodeType, xMLNode.nodeType)
        && Objects.equals(label, xMLNode.label)
        && Objects.equals(objectId, xMLNode.objectId)
        && isEdge == xMLNode.isEdge;
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeType, label, objectId, isEdge);
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
        + "}";
  }
}