package tn.esprit.spring.entities;

import java.util.List;

public class DepartmentPOJO {

	private int id;

	private String name;

	private List<Employe> employes;

	private List<Mission> missions;

	private Entreprise entreprise;

	public DepartmentPOJO() {
		super();
		this.getDepartement();
	}

	public Departement getDepartement() {
		Departement dep = new Departement();
		dep.setEmployes(this.getEmployes());
		dep.setEntreprise(this.getEntreprise());
		dep.setMissions(this.getMissions());
		dep.setName(this.getName());
		return dep;

	}

	public int getId() {

		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
}
