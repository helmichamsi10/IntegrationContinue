package tn.esprit.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.DepartmentPOJO;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.EntrepriseDTO;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;

@RestController
public class RestControlEntreprise {
	private static final Logger LOG = LogManager.getLogger(EntrepriseServiceImpl.class);

	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;

	@PostMapping("/ajouterEntreprise")
	@ResponseBody
	public int ajouterEntreprise(@RequestBody EntrepriseDTO ssiiConsulting) {
		LOG.info("Start Method ajouterEntreprise");
		ientrepriseservice.ajouterEntreprise(ssiiConsulting.getEntreprise());
		LOG.info("End Method ajouterEntreprise");
		return ssiiConsulting.getId();
	}

	// http://localhost:8081/SpringMVC/servlet/affecterDepartementAEntreprise/1/1
	@PutMapping(value = "/affecterDepartementAEntreprise/{iddept}/{identreprise}")
	public void affecterDepartementAEntreprise(@PathVariable("iddept") int depId,
			@PathVariable("identreprise") int entrepriseId) {
		LOG.info("Start Method affecterDepartementAEntreprise");
		ientrepriseservice.affecterDepartementAEntreprise(depId, entrepriseId);
		LOG.info("End Method affecterDepartementAEntreprise");
	}

	// http://localhost:8081/SpringMVC/servlet/deleteEntrepriseById/1
	@DeleteMapping("/deleteEntrepriseById/{identreprise}")
	@ResponseBody
	public void deleteEntrepriseById(@PathVariable("identreprise") int entrepriseId) {
		LOG.info("Start Method deleteEntrepriseById");
		ientrepriseservice.deleteEntrepriseById(entrepriseId);
		LOG.info("End Method deleteEntrepriseById");
	}

	// http://localhost:8081/SpringMVC/servlet/getEntrepriseById/1
	@GetMapping(value = "getEntrepriseById/{identreprise}")
	@ResponseBody
	public Entreprise getEntrepriseById(@PathVariable("identreprise") int entrepriseId) {
		LOG.info("Start Method getEntrepriseById");
		LOG.info("End Method getEntrepriseById");
		return ientrepriseservice.getEntrepriseById(entrepriseId);
	}

	@PostMapping("/ajouterDepartement")
	@ResponseBody
	public int ajouterDepartement(@RequestBody DepartmentPOJO departmentPOJO) {
		LOG.info("Start Method ajouterDepartement");
		ientrepriseservice.ajouterDepartement(departmentPOJO.getDepartement());
		LOG.info("End Method ajouterDepartement");
		return departmentPOJO.getId();
	}

	// http://localhost:8081/SpringMVC/servlet/getAllDepartementsNamesByEntreprise/1
	@GetMapping(value = "getAllDepartementsNamesByEntreprise/{identreprise}")
	@ResponseBody
	public List<String> getAllDepartementsNamesByEntreprise(@PathVariable("identreprise") int entrepriseId) {
		LOG.info("Start Method getAllDepartementsNamesByEntreprise");
		List<String> depNames = ientrepriseservice.getAllDepartementsNamesByEntreprise(entrepriseId);
		LOG.info("End Method getAllDepartementsNamesByEntreprise");
		return depNames;

	}

	// URL : http://localhost:8081/SpringMVC/servlet/deleteDepartementById/3
	@DeleteMapping("/deleteDepartementById/{iddept}")
	@ResponseBody
	public void deleteDepartementById(@PathVariable("iddept") int depId) {
		LOG.info("Start Method getAllDepartementsNamesByEntreprise");
		ientrepriseservice.deleteDepartementById(depId);
		LOG.info("End Method getAllDepartementsNamesByEntreprise");

	}
}
