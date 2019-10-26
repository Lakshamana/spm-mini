package br.ufpa.labes.spm.repository.interfaces;

import br.ufpa.labes.spm.service.dto.SimpleArtifactDescriptorDTO;

import org.springframework.data.repository.NoRepositoryBean;

import br.ufpa.labes.spm.domain.Artifact;

@NoRepositoryBean
public interface ArtifactRepositoryQuery {

  public Object[] getArtifactsIdentsFromProcessModelWithoutTemplates(String ident);

  public Artifact getByName(String name);

  public SimpleArtifactDescriptorDTO[] getInputArtifactsForNormal(String normalIdent);

  public SimpleArtifactDescriptorDTO[] getOutputArtifactsForNormal(String identActivity);
}
