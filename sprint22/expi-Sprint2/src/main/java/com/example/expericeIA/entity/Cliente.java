package com.example.expericeIA.entity;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(name="invs", sequenceName = "SQ_T_USUARIO", allocationSize = 1)
@Table(name = "T_CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "invs")
    @Column(name="ID_CLIENTE")
    private Integer idCliente;

    @Column(name = "NOME_CLIENTE", length = 50, nullable = false)
    private String nomeCliente;

    @Column(name = "EMAIL_CLIENTE", length = 50, nullable = false)
    private String emailCliente;

    @Column(name = "TEL_CLIENTE")
    private String telefoneCliente;

    @Column(name = "CNPJ_CLIENTE", length = 20, nullable = false)
    private String cnpjCliente;

    @Column(name = "TP_EMPRESA", length = 200, nullable = false)
    private String tipoEmpresa;

    public Cliente() {}


    public Cliente(String nome, String email, String telefone, String cnpj, String tipoEmpresa) {
        super();
        this.nomeCliente = nome;
        this.emailCliente = email;
        this.telefoneCliente = telefone;
        this.cnpjCliente = cnpj;
        this.tipoEmpresa = tipoEmpresa;


    }
    @PostPersist
    private void executar() {
        System.out.println("Executando...");
    }

    public Integer getId() {
        return idCliente;
    }

    public void setId(Integer id) {
        this.idCliente = id;
    }

    public String getNome() {
        return nomeCliente;
    }

    public void setNome(String nome) {
        this.nomeCliente = nome;
    }

    public String getEmail() {
        return emailCliente;
    }

    public void setEmail(String email) {
        this.emailCliente = email;
    }

    public String getTelefone() {
        return telefoneCliente;
    }

    public void setTelefone(String telefone) {
        this.telefoneCliente = telefone;
    }

    public String getCnpj() {
        return cnpjCliente;
    }

    public void setCnpj(String cnpj) {
        this.cnpjCliente = cnpj;
    }
    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

}