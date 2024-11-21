package senac.dockerMiniProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import senac.dockerMiniProject.configs.TokenService;
import senac.dockerMiniProject.dtos.LoginDto;
import senac.dockerMiniProject.enterprise.ValidationException;
import senac.dockerMiniProject.entities.Clientes;
import senac.dockerMiniProject.entities.enums.Sexo;
import senac.dockerMiniProject.repositories.ClientesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public ResponseEntity<?> create(Clientes clientesDTO) {

        if (clientesDTO.getNome() == null || clientesDTO.getNome().trim().isEmpty()) {
            throw new ValidationException("O campo 'nome' é obrigatório.");
        }
        if (clientesDTO.getSobrenome() == null || clientesDTO.getSobrenome().trim().isEmpty()) {
            throw new ValidationException("O campo 'sobrenome' é obrigatório.");
        }
        if (clientesDTO.getEmail() == null || clientesDTO.getEmail().trim().isEmpty()) {
            throw new ValidationException("O campo 'email' é obrigatório.");
        }
        if (clientesRepository.findByEmail(clientesDTO.getEmail()).isPresent()) {
            throw new ValidationException("O e-mail informado já está em uso.");
        }

        if (!Sexo.isValid(clientesDTO.getSexo())) {
            throw new ValidationException("O valor de 'sexo' é inválido. Valores permitidos: MASCULINO, FEMININO, NÃOINFORMADO, OUTROS.");
        }

        clientesDTO.setSenha(new BCryptPasswordEncoder().encode(clientesDTO.getPassword()));

        clientesRepository.save(clientesDTO);
        return ResponseEntity.ok(clientesDTO);
    }

    private static Clientes getClientes(Clientes clientesDTO) {
        Sexo sexo = clientesDTO.getSexo() != null ? clientesDTO.getSexo() : Sexo.NÃO_INFORMADO;

        Clientes cliente = new Clientes(
                clientesDTO.getNome(),
                clientesDTO.getSobrenome(),
                clientesDTO.getEmail(),
                sexo,
                clientesDTO.getDataNascimento()
        );

        if (clientesDTO.getEnderecos() != null && !clientesDTO.getEnderecos().isEmpty()) {
            if (clientesDTO.getEnderecos().size() > 5) {
                throw new ValidationException("Não é permitido cadastrar mais de 5 endereços.");
            }
            cliente.setEnderecos(clientesDTO.getEnderecos());
        }
        return cliente;
    }

    public ResponseEntity<Clientes> update(Clientes clientesDTO, Long id) {
        Optional<Clientes> optionalCliente = clientesRepository.findById(id);

        if (optionalCliente.isPresent()) {
            Clientes cliente = optionalCliente.get();

            cliente.setNome(clientesDTO.getNome());
            cliente.setSobrenome(clientesDTO.getSobrenome());
            cliente.setEmail(clientesDTO.getEmail());
            cliente.setSexo(clientesDTO.getSexo());

            if (clientesDTO.getEnderecos() != null && !clientesDTO.getEnderecos().isEmpty()) {
                if (clientesDTO.getEnderecos().size() > 5) {
                    throw new ValidationException("Não é permitido cadastrar mais de 5 endereços.");
                }
                cliente.setEnderecos(clientesDTO.getEnderecos());
            }

            clientesRepository.save(cliente);
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Clientes> findAll() {
        return clientesRepository.findAll();
    }

    public ResponseEntity<Clientes> findById(Long id) {
        Optional<Clientes> cliente = clientesRepository.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> delete(Long id) {
        Optional<Clientes> cliente = clientesRepository.findById(id);
        if (cliente.isPresent()) {
            clientesRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public String login(LoginDto loginDto) throws Exception {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.usuario(), loginDto.senha());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((Clientes) auth.getPrincipal());
        return token;
    }

}
