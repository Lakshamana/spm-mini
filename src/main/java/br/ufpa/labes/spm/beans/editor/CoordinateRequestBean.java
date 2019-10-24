package br.ufpa.labes.spm.beans.editor;

public class CoordinateRequestBean {

  private String processId;

  private String[] idents;

  private String[] xs;

  private String[] ys;

  private String[] types;

  private String[] nodeTypes;

  private String[] referredObjs;


  public String getProcessId() {
    return processId;
  }

  public void setProcessId(String processId) {
    this.processId = processId;
  }

  public String[] getIdents() {
    return idents;
  }

  public void setIdents(String[] idents) {
    this.idents = idents;
  }

  public String[] getXs() {
    return xs;
  }

  public void setXs(String[] xs) {
    this.xs = xs;
  }

  public String[] getYs() {
    return ys;
  }

  public void setYs(String[] ys) {
    this.ys = ys;
  }

  public String[] getTypes() {
    return types;
  }

  public void setTypes(String[] types) {
    this.types = types;
  }

  public String[] getNodeTypes() {
    return nodeTypes;
  }

  public void setNodeTypes(String[] nodeTypes) {
    this.nodeTypes = nodeTypes;
  }

  public String[] getReferredObjs() {
    return referredObjs;
  }

  public void setReferredObjs(String[] referredObjs) {
    this.referredObjs = referredObjs;
  }
}
