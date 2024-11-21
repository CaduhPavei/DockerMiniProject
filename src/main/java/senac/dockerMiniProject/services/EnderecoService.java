package senac.dockerMiniProject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import senac.dockerMiniProject.enterprise.ValidationException;
import senac.dockerMiniProject.entities.Endereco;
import senac.dockerMiniProject.dtos.EnderecoDto;
import senac.dockerMiniProject.repositories.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public ResponseEntity<?> create(EnderecoDto enderecoDto) {
        if (enderecoDto.getRua() == null || enderecoDto.getRua().trim().isEmpty()) {
            throw new ValidationException("O campo 'rua' é obrigatório.");
        }
        if (enderecoDto.getBairro() == null || enderecoDto.getBairro().trim().isEmpty()) {
            throw new ValidationException("O campo 'bairro' é obrigatório.");
        }
        if (enderecoDto.getCidade() == null || enderecoDto.getCidade().trim().isEmpty()) {
            throw new ValidationException("O campo 'cidade' é obrigatório.");
        }
        if (enderecoDto.getUf() == null || enderecoDto.getUf().trim().isEmpty()) {
            throw new ValidationException("O campo 'uf' é obrigatório.");
        }
        Long clienteId = enderecoDto.getCliente().getId();
        List<Endereco> enderecosDoCliente = enderecoRepository.findByClienteId(clienteId);

        if (enderecosDoCliente.size() >= 5) {
            throw new ValidationException("O cliente já possui o limite máximo de 5 endereços.");
        }

        Endereco enderecos = new Endereco(
                enderecoDto.getRua(),
                enderecoDto.getBairro(),
                enderecoDto.getCidade(),
                enderecoDto.getUf(),
                enderecoDto.getCliente().getCliente()
        );

        enderecoRepository.save(enderecos);
        return ResponseEntity.ok(enderecos);
    }

    public List<Endereco> findAll() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos;
    }

    public ResponseEntity<Endereco> findById(Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isPresent()) {
            return ResponseEntity.ok(endereco.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Endereco> update(EnderecoDto enderecoDto, Long id) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);

        if (optionalEndereco.isPresent()) {
            Endereco endereco = optionalEndereco.get();
            endereco.setRua(endereco.getRua());
            endereco.setBairro(endereco.getBairro());
            endereco.setCidade(endereco.getCidade());
            endereco.setUf(endereco.getUf());

            enderecoRepository.save(endereco);
            return ResponseEntity.ok(endereco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> delete(Long id) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);

        if (optionalEndereco.isPresent()) {
            enderecoRepository.delete(optionalEndereco.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
