package senac.dockerMiniProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senac.dockerMiniProject.services.ClientesService;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController extends AbstractController{

    @Autowired
    private ClientesService clientesService;
}
