package senac.dockerMiniProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.dockerMiniProject.entities.Clientes;
import senac.dockerMiniProject.services.ClientesService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController extends AbstractController{

    @Autowired
    private ClientesService clientesService;

    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody Clientes clientesDTO) {
        ResponseEntity<?> cliente = clientesService.create(clientesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping("/cliestes/lista")
    public ResponseEntity<List<Clientes>> getAll() {
        List<Clientes> all = clientesService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> getById(@PathVariable Long id) {
        return clientesService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clientes> update(@RequestBody Clientes clientesDto, @PathVariable Long id) {
        return clientesService.update(clientesDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return clientesService.delete(id);
    }
}
