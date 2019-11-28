package br.ufpa.labes.spm.service.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import br.ufpa.labes.spm.beans.editor.XMLCell;

/** XMLCellUpdateDTO */
public class XMLCellUpdateDTO {

  @NotNull private String username;

  @NotNull private Long processModelId;

  @NotNull private XMLCell xmlCell;

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getProcessModelId() {
    return this.processModelId;
  }

  public void setProcessModelId(Long processModelId) {
    this.processModelId = processModelId;
  }

  public XMLCell getXmlCell() {
    return this.xmlCell;
  }

  public void setXmlCell(XMLCell xmlCell) {
    this.xmlCell = xmlCell;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof XMLCellUpdateDTO)) {
      return false;
    }
    XMLCellUpdateDTO xMLCellUpdateDTO = (XMLCellUpdateDTO) o;
    return Objects.equals(username, xMLCellUpdateDTO.username)
        && Objects.equals(processModelId, xMLCellUpdateDTO.processModelId)
        && Objects.equals(xmlCell, xMLCellUpdateDTO.xmlCell);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, processModelId, xmlCell);
  }

  @Override
  public String toString() {
    return "{"
        + " username='"
        + getUsername()
        + "'"
        + ", processModelId='"
        + getProcessModelId()
        + "'"
        + ", xmlCell='"
        + getXmlCell()
        + "'"
        + "}";
  }
}
