package fiap.gs.marinho.gs_java_marinho.repository;

import fiap.gs.marinho.gs_java_marinho.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {


    //Optional<Incident> findByIncident(String incident);
}
