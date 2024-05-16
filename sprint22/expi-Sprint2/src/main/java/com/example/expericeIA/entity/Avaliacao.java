package com.example.expericeIA.entity;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(name="invs", sequenceName = "SQ_T_AVALIACAO", allocationSize = 1)
@Table(name = "T_AVALIACAO")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "invs")
    @Column(name="ID_AVALIACAO")
    private Integer idAvaliacao;

    @Column(name = "AVALIACAO_NOTA", precision = 2, scale = 0, nullable = false)
    private Integer avaliacaoNota;

    @Column(name = "AVALIACAO_FEEDBACK", length = 500, nullable = false)
    private String avaliacaoFeedback;

    public Avaliacao() {}

    public Avaliacao(Integer id, Integer nota, String feedback) {
        super();
        this.idAvaliacao = id;
        this.avaliacaoNota = nota;
        this.avaliacaoFeedback = feedback;
    }

    @PostPersist
    private void executar() {
        System.out.println("Executando...");
    }
    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public String getAvaliacaoFeedback() {
        return avaliacaoFeedback;
    }

    public void setAvaliacaoFeedback(String avaliacaoFeedback) {
        this.avaliacaoFeedback = avaliacaoFeedback;
    }

    public Integer getAvaliacaoNota() {
        return avaliacaoNota;
    }

    public void setAvaliacaoNota(Integer avaliacaoNota) {
        this.avaliacaoNota = avaliacaoNota;
    }


}



