package br.ufpa.labes.spm.beans.editor;

import java.util.Objects;

public class XMLNode {
  private String nodeType;
  private String label;
  private String nodeId;
  private String style;
  private boolean isEdge = false;
  private int x;
  private int y;
  private int width;
  private int height;

  public static final String
    NORMAL = "Normal",
    DECOMPOSED = "Decomposed",
    REQAGENT = "ReqAgent",
    REQWORKGROUP = "ReqWorkGroup",
    ARTIFACT = "Artifact",
    JOINCON = "Join",
    BRANCHCON = "Branch",
    ARTIFACTCON = "ArtifactCon";

  public XMLNode() {}

  public XMLNode(String nodeType, String label, String nodeId, String style, boolean isEdge, int x, int y, int width, int height) {
    this.nodeType = nodeType;
    this.label = label;
    this.nodeId = nodeId;
    this.style = style;
    this.isEdge = isEdge;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
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

  public String getNodeId() {
    return this.nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

  public String getStyle() {
    return this.style;
  }

  public void setStyle(String style) {
    this.style = style;
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

  public int getWidth() {
    return this.width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return this.height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public XMLNode nodeType(String nodeType) {
    this.nodeType = nodeType;
    return this;
  }

  public XMLNode label(String label) {
    this.label = label;
    return this;
  }

  public XMLNode nodeId(String nodeId) {
    this.nodeId = nodeId;
    return this;
  }

  public XMLNode style(String style) {
    this.style = style;
    return this;
  }

  public XMLNode isEdge(boolean isEdge) {
    this.isEdge = isEdge;
    return this;
  }

  public XMLNode x(int x) {
    this.x = x;
    return this;
  }

  public XMLNode y(int y) {
    this.y = y;
    return this;
  }

  public XMLNode width(int width) {
    this.width = width;
    return this;
  }

  public XMLNode height(int height) {
    this.height = height;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof XMLNode)) {
            return false;
        }
        XMLNode xMLNode = (XMLNode) o;
        return Objects.equals(nodeType, xMLNode.nodeType) && Objects.equals(label, xMLNode.label) && Objects.equals(nodeId, xMLNode.nodeId) && Objects.equals(style, xMLNode.style) && isEdge == xMLNode.isEdge && x == xMLNode.x && y == xMLNode.y && width == xMLNode.width && height == xMLNode.height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeType, label, nodeId, style, isEdge, x, y, width, height);
  }

  @Override
  public String toString() {
    return "{" +
      " nodeType='" + getNodeType() + "'" +
      ", label='" + getLabel() + "'" +
      ", nodeId='" + getNodeId() + "'" +
      ", style='" + getStyle() + "'" +
      ", isEdge='" + isIsEdge() + "'" +
      ", x='" + getX() + "'" +
      ", y='" + getY() + "'" +
      ", width='" + getWidth() + "'" +
      ", height='" + getHeight() + "'" +
      "}";
  }

}
