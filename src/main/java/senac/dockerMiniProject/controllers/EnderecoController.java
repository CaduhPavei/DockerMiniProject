package senac.dockerMiniProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.dockerMiniProject.entities.Endereco;
import senac.dockerMiniProject.entities.dtos.EnderecoDto;
import senac.dockerMiniProject.services.EnderecoService;

import java.util.List;

public class EnderecoController extends AbstractController{

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EnderecoDto enderecoDto) {
        ResponseEntity<?> endereco = enderecoService.create(enderecoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDto>> getAll() {
        List<Endereco> all = enderecoService.findAll();
        List<EnderecoDto> enderecoDtos = EnderecoDto.fromEntityList(all);
        return ResponseEntity.ok(enderecoDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getById(@PathVariable Long id) {
        return enderecoService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> update(@RequestBody EnderecoDto enderecoDto, @PathVariable Long id) {
        return enderecoService.update(enderecoDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return enderecoService.delete(id);
    }
}
