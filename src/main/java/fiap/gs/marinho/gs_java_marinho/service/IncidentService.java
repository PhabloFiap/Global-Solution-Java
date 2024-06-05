package fiap.gs.marinho.gs_java_marinho.service;

import fiap.gs.marinho.gs_java_marinho.entity.Incident;
import fiap.gs.marinho.gs_java_marinho.entity.User;
import fiap.gs.marinho.gs_java_marinho.repository.IncidentRepository;
import fiap.gs.marinho.gs_java_marinho.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    private IncidentRepository incidentRepository;
    private UserRepository userRepository;



    public IncidentService(IncidentRepository incidentRepository, UserRepository userRepository) {
        this.incidentRepository = incidentRepository;
        this.userRepository = userRepository;
    }

    public List<Incident> listIncident() {
        return incidentRepository.findAll();
    }

    public Incident findbyIncident(Long id) {
        return incidentRepository.findById(id).get();
    }

    public Incident createIncident(Long userId, Incident incident) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        incident.setUsuario(user);
        return incidentRepository.save(incident);
    }

    public List<Incident> deleteIncident(Long id) {
        incidentRepository.deleteById(id);
        return listIncident();
    }

    public Incident updateIncident(Incident incident) {
        if (incident.getUsuario() != null && incident.getUsuario().getId() != null) {
            User user = userRepository.findById(incident.getUsuario().getId()).orElseThrow(() -> new RuntimeException("User not found"));
            incident.setUsuario(user);
        }
        return incidentRepository.save(incident);
    }

//
//    public Optional<Incident> findIncident(String incident) {
//        return incidentRepository.findByIncident(incident);
//
//    }
}
