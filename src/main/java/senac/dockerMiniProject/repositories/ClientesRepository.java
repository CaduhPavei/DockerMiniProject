package senac.dockerMiniProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.dockerMiniProject.entities.Clientes;

import java.util.Optional;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {
    Optional<Object> findByEmail(String email);
}
