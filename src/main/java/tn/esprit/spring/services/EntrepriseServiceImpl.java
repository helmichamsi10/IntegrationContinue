package tn.esprit.spring.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	private static final Logger LOG = LogManager.getLogger(EntrepriseServiceImpl.class);
	private static final String ENTREPRISEID = "Entreprise Id";
	private static final String DEPARTMENTID = "Department Id";
	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		LOG.info(MessageFormat.format("Entreprise already Created", entreprise.getName()));
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		LOG.info(MessageFormat.format("Departement already Created", dep.getName()));
		return dep.getId();
	}

	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		LOG.info("Get All Departement Names by Entreprise");
		Optional<Entreprise> entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId);

		Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);

		if (entrepriseManagedEntity.isPresent()) {
			depManagedEntity.get().setEntreprise(entrepriseManagedEntity.get());
			deptRepoistory.save(depManagedEntity.get());
			LOG.info(MessageFormat.format("Departement " + depId + "affected to Entreprise :", entrepriseId));
		} else {
			LOG.error("Error during affecting Departement to Entreprise");
		}
	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		LOG.info("Get All Departement Names by Entreprise");
		Optional<Entreprise> entreprise = entrepriseRepoistory.findById(entrepriseId);
		List<String> depNames = new ArrayList<>();
		if (entreprise.isPresent()) {
			for (Departement dep : entreprise.get().getDepartements()) {
				depNames.add(dep.getName());
				LOG.debug(dep.getName());
			}
		}
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		LOG.info("Get All Departement Names by Entreprise");
		Optional<Entreprise> entreprise = entrepriseRepoistory.findById(entrepriseId);
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		Departement dep = null;
		LOG.info(MessageFormat.format("Start Method Delete Departement By ID", depId));
		Optional<Departement> departement = deptRepoistory.findById(depId);
		if (departement.isPresent()) {
			dep = departement.get();
			deptRepoistory.delete(dep);
			LOG.info(MessageFormat.format("Department Id has been Deleted : ", depId));

		}

	}

	public Entreprise getEntrepriseById(int entrepriseId) {
		return entrepriseRepoistory.findById(entrepriseId).get();
	}

}
