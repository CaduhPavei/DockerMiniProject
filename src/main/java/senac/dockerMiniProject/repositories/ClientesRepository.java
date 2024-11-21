package senac.dockerMiniProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import senac.dockerMiniProject.entities.Clientes;

import java.util.Optional;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {
    Optional<Object> findByEmail(String email);

    UserDetails findByUsername(String login);
}
