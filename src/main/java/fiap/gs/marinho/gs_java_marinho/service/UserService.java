package fiap.gs.marinho.gs_java_marinho.service;

import fiap.gs.marinho.gs_java_marinho.entity.User;
import fiap.gs.marinho.gs_java_marinho.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return userRepository.save(user);

    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsuario(String usuario) {
        return userRepository.findByUsuario(usuario);
    }
}