package fiap.gs.marinho.gs_java_marinho.service;

import fiap.gs.marinho.gs_java_marinho.entity.Incident;
import fiap.gs.marinho.gs_java_marinho.entity.User;
import fiap.gs.marinho.gs_java_marinho.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;


    }
    public List<User> listUser() {

        return userRepository.findAll();

    }
    public User findbyUser(Long id) {
        return userRepository.findById(id).get();
    }

    public User createUser(User user) {

         return userRepository.save(user);


    }


    public List<User> deleteUser(Long id) {
        userRepository.deleteById(id);
        return listUser();
    }

    public User updateUser(User user) {
        Optional<User> existingUserOptional = userRepository.findById(user.getId());
        if (!existingUserOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }
        User existingUser = existingUserOptional.get();

        // Preserve the incidents
        user.setIncidents(existingUser.getIncidents());

        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsuario(String usuario) {
        return userRepository.findByUsuario(usuario);
    }

    public Incident addIncidentToUser(Long userId, Incident incident) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getIncidents().add(incident);
        incident.setUsuario(user);
        userRepository.save(user);
        return incident;
    }
    public User patchUser(Long id, Map<String, Object> updates) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "usuario":
                    user.setUsuario((String) value);
                    break;
                case "role":
                    user.setRole((String) value);
                    break;

                default:
                    throw new IllegalArgumentException("Opcao invalida: " + key);
            }
        });

        return userRepository.save(user);
    }

}
