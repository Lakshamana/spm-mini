package br.ufpa.labes.spm.beans.editor;

import java.util.Objects;

public class XMLNode {
  private String nodeType;
  private String label;
  private Long objectId;
  private boolean isEdge = false;

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

  public XMLNode() {}

  public XMLNode(String nodeType, String label, Long objectId, boolean isEdge) {
    this.nodeType = nodeType;
    this.label = label;
    this.objectId = objectId;
    this.isEdge = isEdge;
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

  public void setIsEdge(boolean isEdge) {
    this.isEdge = isEdge;
  }

  public XMLNode nodeType(String nodeType) {
    this.nodeType = nodeType;
    return this;
  }

  public XMLNode label(String label) {
    this.label = label;
    return this;
  }

  public XMLNode objectId(Long objectId) {
    this.objectId = objectId;
    return this;
  }

  public XMLNode isEdge(boolean isEdge) {
    this.isEdge = isEdge;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof XMLNode)) {
      return false;
    }
    XMLNode xMLNode = (XMLNode) o;
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
