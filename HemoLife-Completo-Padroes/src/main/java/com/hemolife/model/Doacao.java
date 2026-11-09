package com.hemolife.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Usuario usuario;
    
    private LocalDateTime dataDoacao;
    private String status;

    public Doacao() {}

    public Doacao(Usuario usuario, LocalDateTime dataDoacao, String status) {
        this.usuario = usuario;
        this.dataDoacao = dataDoacao;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public LocalDateTime getDataDoacao() { return dataDoacao; }
    public String getStatus() { return status; }
}
