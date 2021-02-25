package tn.esprit.spring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.utils.BaseJUnit49TestCase;


class TimesheetApplicationTests extends BaseJUnit49TestCase {

	private static final Logger LOG = LogManager.getLogger(TimesheetApplicationTests.class);

	@Autowired
	IEntrepriseService entServ;

	private Entreprise entreprise;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.entreprise = new Entreprise();
		this.entreprise.setName("hichem");
		this.entreprise.setRaisonSocial("123");
	}

	@Test
	public void tests() {
//		ajouterEntrepriseTest();
//		getEntrepriseByIdTest();
		deleteEntrepriseByIdTest();
	}

	public void ajouterEntrepriseTest() {
		this.entreprise = new Entreprise();
		this.entreprise.setName("hichem");
		this.entreprise.setRaisonSocial("123");
		LOG.info("Start ajouterEntrepriseTest Message method test");
		LOG.info(this.entreprise);
		this.entreprise.setId(entServ.ajouterEntreprise(this.entreprise));
		assertTrue(this.entreprise.getId() > 0);
		LOG.info(this.entreprise);
		LOG.info("Entreprise id" + this.entreprise.getId());
		LOG.info("End ajouterEntreprise method test");
		LOG.info("start methode getEntrepriseBuTest");
		getEntrepriseByIdTest(this.entreprise);
		LOG.info("End getEntrepriseByTest method test");

	}

	public void getEntrepriseByIdTest(Entreprise entreprise) {
		LOG.info("Start getEntrepriseById method test");
		LOG.info("Entreprise id" + this.entreprise.getId());
		assertNotNull(entServ.getEntrepriseById(this.entreprise.getId()));
		LOG.info("End getEntrepriseById method test");

	}

	public void deleteEntrepriseByIdTest() {
		this.entreprise = new Entreprise();
		ajouterEntrepriseTest();
		LOG.info("Start deleteEntrepriseById method test");
		LOG.info("Entreprise id" + this.entreprise.getId());
		entServ.deleteEntrepriseById(this.entreprise.getId());
		//assertNull(entServ.getEntrepriseById(this.entreprise.getId())== null);
		LOG.info("End deleteEntrepriseById method test");

	}


}
