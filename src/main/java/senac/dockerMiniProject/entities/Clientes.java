package senac.dockerMiniProject.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import senac.dockerMiniProject.entities.enums.Sexo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Clientes extends EntityId implements UserDetails {

    @Column(name = "usuario", nullable = false)
    private String username;
    @Column(name = "senha", nullable = false)
    private String senha;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "sexo")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    public Clientes() {
    }

    public Clientes(String username, String senha, String nome, String sobrenome, String email, Sexo sexo, LocalDate dataNascimento, LocalDateTime dataCadastro, List<Endereco> enderecos) {
        this.username = username;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.enderecos = enderecos;
    }

    public Clientes(String nome, String sobrenome, String email, Sexo sexo, LocalDate dataNascimento) {
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return username;
    }
}