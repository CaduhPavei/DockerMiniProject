package senac.dockerMiniProject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import senac.dockerMiniProject.entities.enums.Sexo;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Clientes extends EntityId{

    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "sexo")
    private Sexo sexo;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

}
