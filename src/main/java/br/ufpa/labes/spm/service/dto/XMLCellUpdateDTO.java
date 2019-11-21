package br.ufpa.labes.spm.service.dto;

import javax.validation.constraints.NotNull;

import br.ufpa.labes.spm.beans.editor.XMLCell;

/** XMLCellUpdateDTO */
public class XMLCellUpdateDTO {

  @NotNull private Long processModelId;

  @NotNull private XMLCell xmlCell;

  public Long getProcessModelId() {
    return processModelId;
  }

  public void setProcessModelId(Long processModelId) {
    this.processModelId = processModelId;
  }

  public XMLCell getXmlCell() {
    return xmlCell;
  }

  public void setXmlCell(XMLCell xmlCell) {
    this.xmlCell = xmlCell;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((processModelId == null) ? 0 : processModelId.hashCode());
    result = prime * result + ((xmlCell == null) ? 0 : xmlCell.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    XMLCellUpdateDTO other = (XMLCellUpdateDTO) obj;
    if (processModelId == null) {
      if (other.processModelId != null) return false;
    } else if (!processModelId.equals(other.processModelId)) return false;
    if (xmlCell == null) {
      if (other.xmlCell != null) return false;
    } else if (!xmlCell.equals(other.xmlCell)) return false;
    return true;
  }
}
