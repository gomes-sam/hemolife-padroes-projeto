package com.hemolife.model;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String tipoSanguineo;
    private int pontosAcumulados = 0;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTipoSanguineo() { return tipoSanguineo; }
    public void setTipoSanguineo(String tipoSanguineo) { this.tipoSanguineo = tipoSanguineo; }
    public int getPontosAcumulados() { return pontosAcumulados; }
    public void setPontosAcumulados(int pontosAcumulados) { this.pontosAcumulados = pontosAcumulados; }
    
    public void adicionarPontos(int pontos) { this.pontosAcumulados += pontos; }
}
