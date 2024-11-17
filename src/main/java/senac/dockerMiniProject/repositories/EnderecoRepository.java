package senac.dockerMiniProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.dockerMiniProject.entities.Endereco;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
