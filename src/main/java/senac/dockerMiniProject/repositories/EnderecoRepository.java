package senac.dockerMiniProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.dockerMiniProject.entities.Endereco;

import java.util.List;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByClienteId(Long clienteId);
}
