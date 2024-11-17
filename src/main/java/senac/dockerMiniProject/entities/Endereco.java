package senac.dockerMiniProject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import senac.dockerMiniProject.entities.enums.Estados;

import java.time.LocalDateTime;

@Entity
public class Endereco extends EntityId{
    @Column(name = "rua")
    private String rua;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "uf")
    private Estados uf;
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Clientes cliente;
    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    public Endereco(){}

    public Endereco(String rua, String bairro, String cidade, Estados uf, Clientes cliente) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cliente = cliente;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estados getUf() {
        return uf;
    }

    public void setUf(Estados uf) {
        this.uf = uf;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
