package tn.esprit.spring;

import java.text.ParseException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.utils.BaseJUnit49TestCase;

public class DepartementTest extends BaseJUnit49TestCase{
	private static final Logger LOG = LogManager.getLogger(DepartementTest.class);

	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	IEntrepriseService ientrepriseservice;
	
	private Entreprise entreprise;
	private Departement departement;
	
	@Override
    public void setUp() throws Exception {
        super.setUp();
        this.departement = new Departement();
        this.departement.setName("DATA SC DEPARTEMENT");

        this.entreprise = new Entreprise();
        this.entreprise.setName("TESLA");
        this.entreprise.setRaisonSocial("100000TUN103");
    }
	
	@Test
    public void tests() throws ParseException {
		ajouterDepartementTest();
        affecterDepartementAEntrepriseTest();
        getAllDepartementsNamesByEntrepriseTest();
        deleteDepartementByIdTest();
        LOG.info("TEST OF DEPARTMENT FINISHED SUCCESSFULY");
    }

	public void deleteDepartementByIdTest() {
		LOG.info("-------------------------- Start Method Delete Departement By Id ------------------------");
		LOG.info("The Dep will be deleted is : " , this.departement);
		ientrepriseservice.ajouterDepartement(this.departement);
		LOG.info(this.departement.getId());
		ientrepriseservice.deleteDepartementById(this.departement.getId());
		LOG.info(this.departement.getId());
		Assert.assertFalse(deptRepoistory.findById(this.departement.getId()).isPresent());
		LOG.info(" Delete Departement By Id has been finished successfuly ");
		LOG.info("-------------------------- Method By Id has been finished successfuly ---------------------");

	}
	
	public void getAllDepartementsNamesByEntrepriseTest() {
		LOG.info("-------------------- Start Method getAllDepartementsNamesByEntreprise -------------------------");
		List<String>depNames = ientrepriseservice.getAllDepartementsNamesByEntreprise(entreprise.getId());
		LOG.debug(depNames);
		Assert.assertTrue(!depNames.isEmpty());
		LOG.info("--------------------- End Method getAllDepartementsNamesByEntreprise ---------------------------");
	}

	public void affecterDepartementAEntrepriseTest() {
		LOG.info("------------------- Start Method affecter Departement An Entreprise ---------------------------");
		LOG.debug(this.departement);
		LOG.debug(this.entreprise);
		ientrepriseservice.ajouterEntreprise(entreprise);
		Assert.assertNull(departement.getEntreprise());
		LOG.info(departement.getEntreprise());
		ientrepriseservice.affecterDepartementAEntreprise(departement.getId(), entreprise.getId());
		Assert.assertFalse(ientrepriseservice.getAllDepartementsNamesByEntreprise(entreprise.getId()).isEmpty());
		LOG.info("The Method affecterDepartementAEntreprise has been finished successfuly");		
		LOG.info("End Method affecterDepartementAEntreprise");
	}
	
	public void ajouterDepartementTest() {
		LOG.info("--------------------- Start Method ADD Departement ------------------------");
		LOG.debug(this.departement);
		ientrepriseservice.ajouterDepartement(departement);
		Assert.assertTrue(ientrepriseservice.ajouterDepartement(departement) > 0);
		LOG.info("Departement has been Added successfly");
		LOG.info("--------------------- End Method ADD Departement --------------------------");
	}
	

}
