package tn.esprit.spring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.utils.BaseJUnit49TestCase;
@SpringBootTest
public class EntrepriseTest extends BaseJUnit49TestCase {
	private static final Logger LOG = LogManager.getLogger(EntrepriseTest.class);

	@Autowired
	IEntrepriseService entServ;

	private Entreprise entreprise;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.entreprise = new Entreprise();
		this.entreprise.setName(getIdHelper().createRandomString(5));
		this.entreprise.setRaisonSocial(getIdHelper().createRandomString(20));
	}

	@Test
	public void tests() {
		ajouterEntrepriseTest();
		getEntrepriseByIdTest();
		deleteEntrepriseByIdTest();
	}

	public void ajouterEntrepriseTest() {
		LOG.info("Start ajouterEntrepriseTest Message method test");
		LOG.info(this.entreprise);
		this.entreprise.setId(entServ.ajouterEntreprise(this.entreprise));
		assertTrue(this.entreprise.getId() > 0);
		LOG.info(this.entreprise);
		LOG.info("Entreprise id" + this.entreprise.getId());
		LOG.info("End ajouterEntreprise method test");

	}

	public void getEntrepriseByIdTest() {
		LOG.info("Start getEntrepriseById method test");
		LOG.info("Entreprise id" + this.entreprise.getId());
		assertNotNull(entServ.getEntrepriseById(this.entreprise.getId()));
		LOG.info("End getEntrepriseById method test");

	}

	public void deleteEntrepriseByIdTest() {
		LOG.info("Start deleteEntrepriseById method test");
		LOG.info("Entreprise id" + this.entreprise.getId());
		entServ.deleteEntrepriseById(this.entreprise.getId());
		assertNull(entServ.getEntrepriseById(this.entreprise.getId()));
		LOG.info("End deleteEntrepriseById method test");

	}

}
