package fiap.gs.marinho.gs_java_marinho.service;

import fiap.gs.marinho.gs_java_marinho.entity.Incident;
import fiap.gs.marinho.gs_java_marinho.entity.User;
import fiap.gs.marinho.gs_java_marinho.repository.IncidentRepository;
import fiap.gs.marinho.gs_java_marinho.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    public Incident patchIncident(Long id, Map<String, Object> updates) {
        Incident incident = incidentRepository.findById(id).orElseThrow(() -> new RuntimeException("Incident not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "Tipo":
                    incident.setTipo((String) value);
                    break;
                case "Descricao":
                    incident.setDescricao((String) value);
                    break;
                case "Lugar":
                    incident.setLugar((String) value);
                    break;
                case "tempo":
                incident.setTimestamp((LocalDateTime)value);
                    break;

                default:
                    throw new IllegalArgumentException("Opcao invalida: " + key);
            }
        });

        return incidentRepository.save(incident);

    }

   }

//
//    public Optional<Incident> findIncident(String incident) {
//        return incidentRepository.findByIncident(incident);
//
//    }

