package com.hemolife.strategy;
import com.hemolife.model.Usuario;
import org.springframework.stereotype.Component;

/**
 * PADRÃO STRATEGY:
 * Estratégia aplicada quando o usuário possui sangue raro (O- ou AB-).
 * Multiplica a recompensa para incentivar mais doações.
 */
@Component
public class PontuacaoUrgencia implements PontuacaoStrategy {
    @Override
    public int calcularPontos(Usuario usuario) {
        return 100; // 100 pontos por sangue raro/urgente
    }
}
