package fiap.gs.marinho.gs_java_marinho.service;

import fiap.gs.marinho.gs_java_marinho.entity.Incident;
import fiap.gs.marinho.gs_java_marinho.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    private IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<Incident> listIncident() {
        return incidentRepository.findAll();
    }

    public Incident findbyIncident(Long id) {
        return incidentRepository.findById(id).get();
    }

    public List<Incident> createIncident(Incident incident) {
        incidentRepository.save(incident);
        return listIncident();
    }

    public List<Incident> deleteIncident(Long id) {
        incidentRepository.deleteById(id);
        return listIncident();
    }

    public List<Incident> updateIncident(Incident incident) {
        incidentRepository.save(incident);
        return listIncident();
    }

//
//    public Optional<Incident> findIncident(String incident) {
//        return incidentRepository.findByIncident(incident);
//
//    }
}
