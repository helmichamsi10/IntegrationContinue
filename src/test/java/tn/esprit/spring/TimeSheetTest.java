package tn.esprit.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;
import tn.esprit.spring.utils.BaseJUnit49TestCase;

public class TimeSheetTest extends BaseJUnit49TestCase {
	private static final Logger LOG = LogManager.getLogger(TimeSheetTest.class);
	@Autowired
	ITimesheetService itimesheetservice;
	@Autowired
	IEntrepriseService iEntrepriseService;
	@Autowired
	IEmployeService iEmployeService;

	@Autowired
	MissionRepository missionRepository;

	@Autowired
	TimesheetRepository timesheetRepository;
	private Mission mission;
	private Departement departement;
	private Employe employe;
	private Employe chefDepartement;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.mission = new Mission();
		this.mission.setName(getIdHelper().createRandomString(5));
		this.mission.setDescription(getIdHelper().createRandomString(20));
		this.departement = new Departement();
		this.departement.setName(getIdHelper().createRandomString(5));

		this.employe = new Employe();
		this.employe.setNom(getIdHelper().createRandomString(5));
		this.employe.setPrenom(getIdHelper().createRandomString(5));
		this.employe.setEmail(getIdHelper().createRandomString(12));
		this.employe.setPassword(getIdHelper().createRandomString(5));
		this.employe.setActif(true);
		this.employe.setRole(Role.INGENIEUR);

		this.chefDepartement = new Employe();
		this.chefDepartement.setNom(getIdHelper().createRandomString(5));
		this.chefDepartement.setPrenom(getIdHelper().createRandomString(5));
		this.chefDepartement.setEmail(getIdHelper().createRandomString(12));
		this.chefDepartement.setPassword(getIdHelper().createRandomString(5));
		this.chefDepartement.setActif(true);
		this.chefDepartement.setRole(Role.CHEF_DEPARTEMENT);
	}

	@Test
	public void tests() throws ParseException {
		addMissionTest();
		addMissionToDepartementTest();
		addTimesheetTest();
		validerTimesheetTest();
		findAllMissionByEmployeJPQLTest();
		getAllEmployeByMissionTest();
	}

	public void addMissionTest() {
		LOG.info("Method addMissionTest ");
		LOG.info(this.mission);
		assertTrue(itimesheetservice.ajouterMission(this.mission) > 0);
		LOG.info(this.mission + " created");
	}

	public void addMissionToDepartementTest() {
		LOG.info("Method addMissionToDepartementTest ");
		this.departement.setId(iEntrepriseService.ajouterDepartement(this.departement));
		assertTrue(this.departement.getId() > 0);
		LOG.info(this.mission);
		LOG.info(this.departement);
		itimesheetservice.affecterMissionADepartement(this.mission.getId(), this.departement.getId());
		Optional<Mission> missionR = missionRepository.findById(this.mission.getId());
		if (missionR.isPresent()) {
			this.mission = missionR.get();
			LOG.debug(MessageFormat.format("Fetched Mission: {0}", this.mission.getId()));
		}
		assertEquals(this.mission.getDepartement().getId(), this.departement.getId());
		LOG.info(this.mission);
	}

	public void addTimesheetTest() throws ParseException {
		LOG.info("Method ajouterTimesheetTest ");
		assertTrue(iEmployeService.addOrUpdateEmploye(this.employe) > 0);
		assertTrue(iEmployeService.addOrUpdateEmploye(this.chefDepartement) > 0);
		iEmployeService.affecterEmployeADepartement(this.chefDepartement.getId(), this.departement.getId());
		LOG.info(this.mission);
		LOG.info(this.employe);
		Date dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-14");
		Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-14");
		itimesheetservice.ajouterTimesheet(this.mission.getId(), this.employe.getId(), dateDebut, dateFin);
		Iterable<Timesheet> timesheets = timesheetRepository.findAll();
		for (Timesheet ts : timesheets) {
			if ((ts.getMission().getId() == this.mission.getId())
					&& (ts.getEmploye().getId() == this.employe.getId())) {
				List<Timesheet> timesheet = new ArrayList();
				timesheet.add(ts);
				this.mission.setTimesheets(timesheet);
			}
		}
		assertNotNull(this.mission.getTimesheets().get(0).getTimesheetPK());
		LOG.info(this.mission.getTimesheets().get(0));
	}

	public void validerTimesheetTest() throws ParseException {
		LOG.info("Method validerTimesheetTest ");
		assertTrue(iEmployeService.addOrUpdateEmploye(this.chefDepartement) > 0);
		LOG.info(this.mission);
		LOG.info(this.chefDepartement);
		Date dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-14");
		Date dateFin = new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-14");
		itimesheetservice.validerTimesheet(this.mission.getId(), this.employe.getId(), dateDebut, dateFin,
				this.chefDepartement.getId());
		Iterable<Timesheet> timesheets = timesheetRepository.findAll();
		for (Timesheet ts : timesheets) {
			if ((ts.getMission().getId() == this.mission.getId())
					&& (ts.getEmploye().getId() == this.employe.getId())) {
				List<Timesheet> timesheet = new ArrayList();
				timesheet.add(ts);
				this.mission.setTimesheets(timesheet);
			}
		}
//		assertTrue(this.mission.getTimesheets().get(0).isValide());
		LOG.info(this.mission.getTimesheets().get(0));
	}

	public void findAllMissionByEmployeJPQLTest() {
		LOG.info("Method findAllMissionByEmployeJPQLTest ");
		List<Mission> missions = itimesheetservice.findAllMissionByEmployeJPQL(this.employe.getId());
		LOG.info(missions);
		assertTrue(missions.size() > 0);
	}

	public void getAllEmployeByMissionTest() {
		LOG.info("Method getAllEmployeByMissionTest ");
		List<Employe> employes = itimesheetservice.getAllEmployeByMission(this.mission.getId());
		LOG.info(employes);
		assertTrue(employes.size() > 0);
	}
}