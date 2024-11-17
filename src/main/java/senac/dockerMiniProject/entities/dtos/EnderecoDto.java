package senac.dockerMiniProject.entities.dtos;

import senac.dockerMiniProject.entities.Clientes;
import senac.dockerMiniProject.entities.Endereco;
import senac.dockerMiniProject.entities.enums.Estados;

import java.util.ArrayList;
import java.util.List;


public class EnderecoDto {

    private String rua;
    private String bairro;
    private String cidade;
    private Estados uf;
    private Endereco cliente;

    public EnderecoDto(String rua, String bairro, String cidade, Estados uf, Clientes cliente){}

    public EnderecoDto(String rua, String bairro, String cidade, Estados uf, Endereco cliente) {
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

    public Endereco getCliente() {
        return cliente;
    }

    public void setCliente(Endereco cliente) {
        this.cliente = cliente;
    }
    
    public static EnderecoDto fromEntity(Endereco endereco){
        return new EnderecoDto(
                endereco.getRua(),
                endereco.getBairro(),
                endereco.getCidade(), 
                endereco.getUf(),
                endereco.getCliente()
        );
    }
    
    public static List<EnderecoDto>fromEntityList(List<Endereco> enderecos){
        List<EnderecoDto> enderecosDtoList = new ArrayList<>();
        for (Endereco endereco : enderecos) {
            enderecosDtoList.add(fromEntity(endereco));
        }
        return enderecosDtoList;
    }

    public Endereco toEntity(){
        Endereco endereco = new Endereco();
        endereco.setRua(this.rua);
        endereco.setBairro(this.bairro);
        endereco.setCidade(this.cidade);
        endereco.setUf(this.uf);
        endereco.setCidade(String.valueOf(this.cliente));
        return endereco;
    }

    public static List<Endereco> toEntityList(List<EnderecoDto> enderecoDtos) {
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDto enderecoDto : enderecoDtos) {
            enderecos.add(enderecoDto.toEntity());
        }
        return enderecos;
    }
}