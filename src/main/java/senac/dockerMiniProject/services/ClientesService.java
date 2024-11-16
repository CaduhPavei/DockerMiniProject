package senac.dockerMiniProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import senac.dockerMiniProject.controllers.dtos.ClientesDto;
import senac.dockerMiniProject.enterprise.ValidationException;
import senac.dockerMiniProject.entities.Clientes;
import senac.dockerMiniProject.entities.enums.Sexo;
import senac.dockerMiniProject.repositories.ClientesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    private Sexo sexo;
    @Autowired
    private ClientesRepository clientesRepository;

    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public ResponseEntity<?> create(ClientesDto clientesDTO) {
        if (clientesDTO.getNome() == null || clientesDTO.getNome().trim().isEmpty()) {
            throw new ValidationException("O campo 'nome' é obrigatório.");
        }
        if (clientesDTO.getSobrenome() == null || clientesDTO.getSobrenome().trim().isEmpty()) {
            throw new ValidationException("O campo 'sobrenome' é obrigatório.");
        }

        if (clientesDTO.getEmail() == null || clientesDTO.getEmail().trim().isEmpty()) {
            throw new ValidationException("O campo 'email' é obrigatório.");
        }
        if (clientesRepository.findByEmail(clientesDTO.getEmail()).isPresent()){
            throw new ValidationException("O e-mail informado já está em uso.");
        }

        if (!Sexo.isValid(clientesDTO.getSexo())) {
            throw new ValidationException("O valor de 'sexo' é inválido. Valores permitidos: MASCULINO, FEMININO, NÃOINFORMADO, OUTROS.");
        }
        Sexo sexo = clientesDTO.getSexo() != null ? clientesDTO.getSexo() : Sexo.NÃO_INFORMADO;

        Clientes clientes = new Clientes(
                clientesDTO.getNome(),
                clientesDTO.getSobrenome(),
                clientesDTO.getEmail(),
                sexo,
                clientesDTO.getDataNascimento()
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
