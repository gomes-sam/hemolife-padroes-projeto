package com.hemolife.strategy;
import com.hemolife.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PontuacaoPadrao implements PontuacaoStrategy {
    @Override
    public int calcularPontos(Usuario usuario) {
        return 20; // 20 pontos por doação comum
    }
}
