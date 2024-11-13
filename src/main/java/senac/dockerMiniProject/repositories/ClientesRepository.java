package senac.dockerMiniProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.dockerMiniProject.entities.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {
}
