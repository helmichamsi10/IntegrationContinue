package tn.esprit.spring;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.utils.BaseJUnit49TestCase;

public class DepartementTest extends BaseJUnit49TestCase{
	private static final Logger LOG = LogManager.getLogger(DepartementTest.class);

	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	@Autowired
	IEntrepriseService ientrepriseservice;
	
	private Entreprise entreprise;
	private Departement departement;
	
	@Override
    public void setUp() throws Exception {
        super.setUp();
        this.departement = new Departement();
        this.departement.setName(getIdHelper().createRandomString(5));

        this.entreprise = new Entreprise();
        this.entreprise.setName(getIdHelper().createRandomString(5));
        this.entreprise.setRaisonSocial(getIdHelper().createRandomString(5));
    }
	
	@Test
    public void tests() throws ParseException {
		ajouterDepartement();
        affecterDepartementAEntreprise();
        getAllDepartementsNamesByEntreprise();
        deleteDepartementById();
    }

	public void deleteDepartementById() {
		LOG.info("StartMethodSeleteDepartementById");
		LOG.info(this.departement);
		departement.setId(ientrepriseservice.ajouterDepartement(departement));
		ientrepriseservice.deleteDepartementById(departement.getId());
		Assert.assertFalse(deptRepoistory.findById(departement.getId()).isPresent());
		LOG.info("EndDeleteDepartementById");
	}
	
	public void getAllDepartementsNamesByEntreprise() {
		LOG.info("Start Method getAllDepartementsNamesByEntreprise");
		entreprise.setId(ientrepriseservice.ajouterEntreprise(entreprise));
		List<String> depNames = ientrepriseservice.getAllDepartementsNamesByEntreprise(entreprise.getId());
		LOG.info(depNames);
		Assert.assertTrue(depNames.size() > 0);
		LOG.info("End Method getAllDepartementsNamesByEntreprise");
	}

	public void affecterDepartementAEntreprise() {
		LOG.info("Start Method affecterDepartementAEntreprise");
		LOG.info(this.departement);
		LOG.info(this.entreprise);
		ientrepriseservice.ajouterDepartement(departement);
		ientrepriseservice.ajouterEntreprise(entreprise);
		Assert.assertNull(departement.getEntreprise());
		ientrepriseservice.affecterDepartementAEntreprise(departement.getId(), entreprise.getId());
		Assert.assertNotNull(ientrepriseservice.getAllDepartementsNamesByEntreprise(entreprise.getId()));
		LOG.info("End Method affecterDepartementAEntreprise");
	}
	
	public void ajouterDepartement() {
		LOG.info("Start Method ajouterDepartement");
		LOG.info(this.departement);
		ientrepriseservice.ajouterDepartement(departement);
		Assert.assertTrue(ientrepriseservice.ajouterDepartement(departement) > 0);
		LOG.info("End Method ajouterDepartement");
	}
	

}
