package com.example.expericeIA.entity;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(name="invs", sequenceName = "SQ_T_LOGIN", allocationSize = 1)
@Table(name = "T_LOGIN")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "invs")
    @Column(name="ID_LOGIN")
    private Integer idLogin;

    @Column(name = "EMAIL_LOGIN", length = 50, nullable = false)
    private String emailLogin;

    @Column(name = "SENHA_LOGIN")
    private String senhaLogin;

    public Login() {}

    public Login(Integer idLogin, String emailLogin, String senhaLogin) {
        super();
        this.idLogin = idLogin;
        this.emailLogin = emailLogin;
        this.senhaLogin = senhaLogin;
    }

    @PostPersist
    private void executar() {
        System.out.println("Executando...");
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public String getSenhaLogin() {
        return senhaLogin;
    }

    public void setSenhaLogin(String senhaLogin) {
        this.senhaLogin = senhaLogin;
    }
}
