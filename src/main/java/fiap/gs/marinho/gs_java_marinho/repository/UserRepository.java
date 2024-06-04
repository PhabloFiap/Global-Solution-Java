package fiap.gs.marinho.gs_java_marinho.repository;

import fiap.gs.marinho.gs_java_marinho.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsuario(String usuario);
}