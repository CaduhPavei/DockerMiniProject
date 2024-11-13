package senac.dockerMiniProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import senac.dockerMiniProject.controllers.dtos.ClientesDto;
import senac.dockerMiniProject.entities.Clientes;
import senac.dockerMiniProject.repositories.ClientesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {
    @Autowired
    private ClientesRepository clientesRepository;

    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public ResponseEntity<Clientes> create(ClientesDto clientesDTO) {
        Clientes clientes = new Clientes(
                clientesDTO.getNome(),
                clientesDTO.getSobrenome(),
                clientesDTO.getEmail(),
                clientesDTO.getSexo(),
                clientesDTO.getDataNascimento(),
                clientesDTO.getDataCadastro()
        );

        clientesRepository.save(clientes);
        return ResponseEntity.ok(clientes);
    }

    public List<Clientes> findAll() {
        List<Clientes> clientes = clientesRepository.findAll();
        return clientes;
    }

    public ResponseEntity<Clientes> findById(Long id) {
        Optional<Clientes> clientes = clientesRepository.findById(id);
        if (clientes.isPresent()) {
            return ResponseEntity.ok(clientes.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Clientes> update(ClientesDto clientesDTO, Long id) {
        Optional<Clientes> optionalClientes = clientesRepository.findById(id);

        if (optionalClientes.isPresent()) {
            Clientes clientes = optionalClientes.get();
            clientes.setNome(clientesDTO.getNome());
            clientes.setSobrenome(clientesDTO.getSobrenome());
            clientes.setEmail(clientesDTO.getEmail());
            clientes.setSexo(clientesDTO.getSexo());
            clientes.setDataNascimento(clientesDTO.getDataNascimento());
            clientes.setDataCadastro(clientesDTO.getDataCadastro());

            clientesRepository.save(clientes);
            return ResponseEntity.ok(clientes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> delete(Long id) {
        Optional<Clientes> optionalClientes = clientesRepository.findById(id);

        if (optionalClientes.isPresent()) {
            clientesRepository.delete(optionalClientes.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
