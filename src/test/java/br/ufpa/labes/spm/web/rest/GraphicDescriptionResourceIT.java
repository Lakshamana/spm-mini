package br.ufpa.labes.spm.web.rest;

import br.ufpa.labes.spm.SpmApp;
import br.ufpa.labes.spm.domain.GraphicDescription;
import br.ufpa.labes.spm.repository.GraphicDescriptionRepository;
import br.ufpa.labes.spm.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static br.ufpa.labes.spm.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** Integration tests for the {@link GraphicDescriptionResource} REST controller. */
@SpringBootTest(classes = SpmApp.class)
public class GraphicDescriptionResourceIT {

  private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
  private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

  @Autowired private GraphicDescriptionRepository graphicDescriptionRepository;

  @Autowired private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired private ExceptionTranslator exceptionTranslator;

  @Autowired private EntityManager em;

  @Autowired private Validator validator;

  private MockMvc restGraphicDescriptionMockMvc;

  private GraphicDescription graphicDescription;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final GraphicDescriptionResource graphicDescriptionResource =
        new GraphicDescriptionResource(graphicDescriptionRepository);
    this.restGraphicDescriptionMockMvc =
        MockMvcBuilders.standaloneSetup(graphicDescriptionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator)
            .build();
  }

  /**
   * Create an entity for this test.
   *
   * <p>This is a static method, as tests for other entities might also need it, if they test an
   * entity which requires the current entity.
   */
  public static GraphicDescription createEntity(EntityManager em) {
    GraphicDescription graphicDescription =
        new GraphicDescription().description(DEFAULT_DESCRIPTION);
    return graphicDescription;
  }
  /**
   * Create an updated entity for this test.
   *
   * <p>This is a static method, as tests for other entities might also need it, if they test an
   * entity which requires the current entity.
   */
  public static GraphicDescription createUpdatedEntity(EntityManager em) {
    GraphicDescription graphicDescription =
        new GraphicDescription().description(UPDATED_DESCRIPTION);
    return graphicDescription;
  }

  @BeforeEach
  public void initTest() {
    graphicDescription = createEntity(em);
  }

  @Test
  @Transactional
  public void createGraphicDescription() throws Exception {
    int databaseSizeBeforeCreate = graphicDescriptionRepository.findAll().size();

    // Create the GraphicDescription
    restGraphicDescriptionMockMvc
        .perform(
            post("/api/graphic-descriptions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(graphicDescription)))
        .andExpect(status().isCreated());

    // Validate the GraphicDescription in the database
    List<GraphicDescription> graphicDescriptionList = graphicDescriptionRepository.findAll();
    assertThat(graphicDescriptionList).hasSize(databaseSizeBeforeCreate + 1);
    GraphicDescription testGraphicDescription =
        graphicDescriptionList.get(graphicDescriptionList.size() - 1);
    assertThat(testGraphicDescription.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
  }

  @Test
  @Transactional
  public void createGraphicDescriptionWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = graphicDescriptionRepository.findAll().size();

    // Create the GraphicDescription with an existing ID
    graphicDescription.setId(1L);

    // An entity with an existing ID cannot be created, so this API call must fail
    restGraphicDescriptionMockMvc
        .perform(
            post("/api/graphic-descriptions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(graphicDescription)))
        .andExpect(status().isBadRequest());

    // Validate the GraphicDescription in the database
    List<GraphicDescription> graphicDescriptionList = graphicDescriptionRepository.findAll();
    assertThat(graphicDescriptionList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getAllGraphicDescriptions() throws Exception {
    // Initialize the database
    graphicDescriptionRepository.saveAndFlush(graphicDescription);

    // Get all the graphicDescriptionList
    restGraphicDescriptionMockMvc
        .perform(get("/api/graphic-descriptions?sort=id,desc"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(graphicDescription.getId().intValue())))
        .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
  }

  @Test
  @Transactional
  public void getGraphicDescription() throws Exception {
    // Initialize the database
    graphicDescriptionRepository.saveAndFlush(graphicDescription);

    // Get the graphicDescription
    restGraphicDescriptionMockMvc
        .perform(get("/api/graphic-descriptions/{id}", graphicDescription.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(graphicDescription.getId().intValue()))
        .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
  }

  @Test
  @Transactional
  public void getNonExistingGraphicDescription() throws Exception {
    // Get the graphicDescription
    restGraphicDescriptionMockMvc
        .perform(get("/api/graphic-descriptions/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateGraphicDescription() throws Exception {
    // Initialize the database
    graphicDescriptionRepository.saveAndFlush(graphicDescription);

    int databaseSizeBeforeUpdate = graphicDescriptionRepository.findAll().size();

    // Update the graphicDescription
    GraphicDescription updatedGraphicDescription =
        graphicDescriptionRepository.findById(graphicDescription.getId()).get();
    // Disconnect from session so that the updates on updatedGraphicDescription are not directly
    // saved in db
    em.detach(updatedGraphicDescription);
    updatedGraphicDescription.description(UPDATED_DESCRIPTION);

    restGraphicDescriptionMockMvc
        .perform(
            put("/api/graphic-descriptions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedGraphicDescription)))
        .andExpect(status().isOk());

    // Validate the GraphicDescription in the database
    List<GraphicDescription> graphicDescriptionList = graphicDescriptionRepository.findAll();
    assertThat(graphicDescriptionList).hasSize(databaseSizeBeforeUpdate);
    GraphicDescription testGraphicDescription =
        graphicDescriptionList.get(graphicDescriptionList.size() - 1);
    assertThat(testGraphicDescription.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
  }

  @Test
  @Transactional
  public void updateNonExistingGraphicDescription() throws Exception {
    int databaseSizeBeforeUpdate = graphicDescriptionRepository.findAll().size();

    // Create the GraphicDescription

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restGraphicDescriptionMockMvc
        .perform(
            put("/api/graphic-descriptions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(graphicDescription)))
        .andExpect(status().isBadRequest());

    // Validate the GraphicDescription in the database
    List<GraphicDescription> graphicDescriptionList = graphicDescriptionRepository.findAll();
    assertThat(graphicDescriptionList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  public void deleteGraphicDescription() throws Exception {
    // Initialize the database
    graphicDescriptionRepository.saveAndFlush(graphicDescription);

    int databaseSizeBeforeDelete = graphicDescriptionRepository.findAll().size();

    // Delete the graphicDescription
    restGraphicDescriptionMockMvc
        .perform(
            delete("/api/graphic-descriptions/{id}", graphicDescription.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<GraphicDescription> graphicDescriptionList = graphicDescriptionRepository.findAll();
    assertThat(graphicDescriptionList).hasSize(databaseSizeBeforeDelete - 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(GraphicDescription.class);
    GraphicDescription graphicDescription1 = new GraphicDescription();
    graphicDescription1.setId(1L);
    GraphicDescription graphicDescription2 = new GraphicDescription();
    graphicDescription2.setId(graphicDescription1.getId());
    assertThat(graphicDescription1).isEqualTo(graphicDescription2);
    graphicDescription2.setId(2L);
    assertThat(graphicDescription1).isNotEqualTo(graphicDescription2);
    graphicDescription1.setId(null);
    assertThat(graphicDescription1).isNotEqualTo(graphicDescription2);
  }
}
