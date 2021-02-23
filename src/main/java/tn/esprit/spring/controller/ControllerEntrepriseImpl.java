package tn.esprit.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

@Controller
public class ControllerEntrepriseImpl {
	private static final Logger LOG = LogManager.getLogger(ControllerEntrepriseImpl.class);
	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;

	public int ajouterEntreprise(Entreprise ssiiConsulting) {
		LOG.info("Start Method ajouterEntreprise");
		ientrepriseservice.ajouterEntreprise(ssiiConsulting);
		LOG.info("End Method ajouterEntreprise");
		return ssiiConsulting.getId();
	}

	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		LOG.info("Start Method affecterDepartementAEntreprise");
		ientrepriseservice.affecterDepartementAEntreprise(depId, entrepriseId);
		LOG.info("End Method affecterDepartementAEntreprise");

	}

	public void deleteEntrepriseById(int entrepriseId) {
		LOG.info("Start Method deleteEntrepriseById");
		ientrepriseservice.deleteEntrepriseById(entrepriseId);
		LOG.info("End Method deleteEntrepriseById");
	}

	public Entreprise getEntrepriseById(int entrepriseId) {
		LOG.info("Start Method getEntrepriseById");
		LOG.info("End Method getEntrepriseById");
		return ientrepriseservice.getEntrepriseById(entrepriseId);
	}

	public int ajouterDepartement(Departement dep) {
		LOG.info("Start Method ajouterDepartement");
		LOG.info("End Method ajouterDepartement");
		return ientrepriseservice.ajouterDepartement(dep);
	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		LOG.info("Start Method getAllDepartementsNamesByEntreprise");
		LOG.info("End Method getAllDepartementsNamesByEntreprise");
		return ientrepriseservice.getAllDepartementsNamesByEntreprise(entrepriseId);
	}

	public void deleteDepartementById(int depId) {
		LOG.info("Start Method deleteDepartementById");
		ientrepriseservice.deleteDepartementById(depId);
		LOG.info("End Method deleteDepartementById");
	}
}
