package br.ufpa.labes.spm;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

  @Test
  void servicesAndRepositoriesShouldNotDependOnWebLayer() {

    JavaClasses importedClasses =
        new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.ufpa.labes.spm");

    noClasses()
        .that()
        .resideInAnyPackage("..service..")
        .or()
        .resideInAnyPackage("..repository..")
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage("..br.ufpa.labes.spm.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
  }
}
