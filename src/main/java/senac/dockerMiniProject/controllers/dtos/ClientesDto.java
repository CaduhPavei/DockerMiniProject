package senac.dockerMiniProject.controllers.dtos;
import senac.dockerMiniProject.entities.Clientes;
import senac.dockerMiniProject.entities.enums.Sexo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClientesDto {

    private String nome;
    private String sobrenome;
    private String email;
    private Sexo sexo;
    private LocalDate dataNascimento;
    private LocalDateTime dataCadastro;

    public ClientesDto(){}

    public ClientesDto(String nome, String sobrenome, String email, Sexo sexo, LocalDate dataNascimento, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public static ClientesDto fromEntity(Clientes clientes){
        return new ClientesDto(
                clientes.getNome(),
                clientes.getSobrenome(),
                clientes.getEmail(),
                clientes.getSexo(),
                clientes.getDataNascimento(),
                clientes.getDataCadastro()
        );
    }

    public static List<ClientesDto> fromEntituList(List<Clientes> clientes){
        List<ClientesDto> clientesDtoList = new ArrayList<>();
        for (Clientes cliente : clientes) {
            clientesDtoList.add(fromEntity(cliente));
        }
        return clientesDtoList;
    }

    public Clientes toEntity() {
        Clientes clientes = new Clientes();
        clientes.setNome(this.nome);
        clientes.setSobrenome(this.sobrenome);
        clientes.setEmail(this.email);
        clientes.setSexo(this.sexo);
        clientes.setDataNascimento(this.dataNascimento);
        clientes.setDataCadastro(this.dataCadastro);
        return clientes;
    }

    public static List<Clientes> toEntityList(List<ClientesDto> clienteDTOs) {
        List<Clientes> clientes = new ArrayList<>();
        for (ClientesDto clienteDTO : clienteDTOs) {
            clientes.add(clienteDTO.toEntity());
        }
        return clientes;
    }

}
