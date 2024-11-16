package senac.dockerMiniProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senac.dockerMiniProject.controllers.dtos.ClientesDto;
import senac.dockerMiniProject.entities.Clientes;
import senac.dockerMiniProject.services.ClientesService;

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
}
