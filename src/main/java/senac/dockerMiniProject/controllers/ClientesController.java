package senac.dockerMiniProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.dockerMiniProject.entities.Clientes;
import senac.dockerMiniProject.entities.Endereco;
import senac.dockerMiniProject.entities.dtos.ClientesDto;
import senac.dockerMiniProject.entities.dtos.EnderecoDto;
import senac.dockerMiniProject.services.ClientesService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController extends AbstractController{

    @Autowired
    private ClientesService clientesService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClientesDto clientesDTO) {
        ResponseEntity<?> cliente = clientesService.create(clientesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClientesDto>> getAll() {
        List<Clientes> all = clientesService.findAll();
        List<ClientesDto> clientesDtos = ClientesDto.fromEntityList(all);
        return ResponseEntity.ok(clientesDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> getById(@PathVariable Long id) {
        return clientesService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clientes> update(@RequestBody ClientesDto clientesDto, @PathVariable Long id) {
        return clientesService.update(clientesDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return clientesService.delete(id);
    }
}
