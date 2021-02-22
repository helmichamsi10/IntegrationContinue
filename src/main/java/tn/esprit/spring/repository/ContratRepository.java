package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {

	@Query("SELECT c FROM Contrat c WHERE c.reference=:reference")
	public Contrat getContractByName(String reference);

	@Query("SELECT count(*) FROM Contrat")
	public int countContrat();

	@Query("SELECT typeContrat FROM Contrat")
	public List<String> ContratReferences();

	@Modifying
	@Transactional
	@Query("UPDATE Contrat c SET c.salaire=:salaire where c.reference=:reference")
	public void mettreAjoursalireByContractReferenceJPQL(@Param("salaire") float salaire,
			@Param("reference") String reference);

	//@Query("select typeContrat from Contrat c join employe e where e.contrat_reference=:contReference")
	//public String getDetailBycontratreferenceJPQL(@Param("contReference") String reference);

	
}
