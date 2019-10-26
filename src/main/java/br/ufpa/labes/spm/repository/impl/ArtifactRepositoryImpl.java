package br.ufpa.labes.spm.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufpa.labes.spm.repository.interfaces.ArtifactRepositoryQuery;
import br.ufpa.labes.spm.service.dto.SimpleArtifactDescriptorDTO;
import br.ufpa.labes.spm.domain.Artifact;
import br.ufpa.labes.spm.domain.ArtifactCon;
import br.ufpa.labes.spm.domain.InvolvedArtifact;

public class ArtifactRepositoryImpl implements ArtifactRepositoryQuery {

  @PersistenceContext private EntityManager em;

  @Override
  public Object[] getArtifactsIdentsFromProcessModelWithoutTemplates(String ident) {
    String hql =
        " select DISTINCT theArtifact.ident from "
            + ArtifactCon.class.getName()
            + " as artfCon where (artfCon.ident like '"
            + ident
            + ".%')"
            + " and (artfCon.theArtifact.isTemplate = 'false')";

    Query query = this.em.createQuery(hql);

    List result = query.getResultList();
    int size = result.size();
    System.out.println("templates lista: " + result);
    return result.toArray();
  }

  @Override
  public Artifact getByName(String name) {
    String hql =
        " select DISTINCT artifact from "
            + Artifact.class.getName()
            + " as artifact where artifact.name =:name";

    Query query = this.em.createQuery(hql);
    query.setParameter("name", name);
    return (Artifact) query.getSingleResult();
  }

  @Override
  public SimpleArtifactDescriptorDTO[] getInputArtifactsForNormal(String normalIdent) {

    String hql =
        " from "
            + InvolvedArtifact.class.getName()
            + " as involvedArtifact where (involvedArtifact.inInvolvedArtifacts.ident=:normalID) ";
    Query query = this.em.createQuery(hql);

    query.setParameter("normalID", normalIdent);

    List<InvolvedArtifact> result = query.getResultList();

    ArrayList<Artifact> inputArtifacts = new ArrayList<Artifact>();

    for (InvolvedArtifact involvedArtifact : result) {
      if (involvedArtifact.getTheArtifact() != null)
        inputArtifacts.add(involvedArtifact.getTheArtifact());
    }

    Artifact[] inputArtifactsArray = new Artifact[inputArtifacts.size()];
    inputArtifactsArray = inputArtifacts.toArray(inputArtifactsArray);

    SimpleArtifactDescriptorDTO[] artifactDescriptors =
        new SimpleArtifactDescriptorDTO[inputArtifactsArray.length];

    for (int i = 0; i < inputArtifactsArray.length; i++) {
      if (inputArtifactsArray[i] != null) {
        String artifactIdent = inputArtifactsArray[i].getIdent();
        String artifactName = inputArtifactsArray[i].getName();
        String latestVersion = inputArtifactsArray[i].getLatestVersion();
        String fileName = inputArtifactsArray[i].getFileName();
        String repositoryIdent = null;
        // if (inputArtifactsArray[i].getTheRepository() != null)
        //   repositoryIdent = inputArtifactsArray[i].getTheRepository().getIdent();

        artifactDescriptors[i] =
            new SimpleArtifactDescriptorDTO(
                artifactIdent, artifactName, latestVersion, fileName, null);
      }
    }
    System.out.println("no RepositoryQuery: " + artifactDescriptors.length);
    return artifactDescriptors;
  }

  @Override
  public SimpleArtifactDescriptorDTO[] getOutputArtifactsForNormal(String normalIdent) {
    String hql =
        " from "
            + InvolvedArtifact.class.getName()
            + " as involvedArtifact where (involvedArtifact.outInvolvedArtifacts.ident=:normalID) ";

    Query query = this.em.createQuery(hql);

    query.setParameter("normalID", normalIdent);

    List<InvolvedArtifact> result = query.getResultList();
    Iterator<InvolvedArtifact> resultIterator = result.iterator();

    ArrayList<Artifact> outputArtifacts = new ArrayList<Artifact>();

    for (InvolvedArtifact involvedArtifact : result) {
      if (involvedArtifact.getTheArtifact() != null)
        outputArtifacts.add(involvedArtifact.getTheArtifact());
    }

    Artifact[] outputArtifactsArray = new Artifact[outputArtifacts.size()];
    outputArtifactsArray = outputArtifacts.toArray(outputArtifactsArray);

    SimpleArtifactDescriptorDTO[] artifactDescriptors =
        new SimpleArtifactDescriptorDTO[outputArtifactsArray.length];

    for (int i = 0; i < outputArtifactsArray.length; i++) {
      if (outputArtifactsArray[i] != null) {
        String artifactIdent = outputArtifactsArray[i].getIdent();
        String artifactName = outputArtifactsArray[i].getName();
        String latestVersion = outputArtifactsArray[i].getLatestVersion();
        String fileName = outputArtifactsArray[i].getFileName();

        String repositoryIdent = null;
        // if (outputArtifactsArray[i].getTheRepository() != null)
        //   repositoryIdent = outputArtifactsArray[i].getTheRepository().getIdent();

        artifactDescriptors[i] =
            new SimpleArtifactDescriptorDTO(
                artifactIdent, artifactName, latestVersion, fileName, null);
      }
    }
    return artifactDescriptors;
  }
}
