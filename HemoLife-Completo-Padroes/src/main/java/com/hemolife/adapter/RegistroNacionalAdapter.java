package com.hemolife.adapter;
import com.hemolife.adapter.externo.MinisterioSaudeAPI;
import com.hemolife.model.Usuario;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

/**
 * PADRÃO ADAPTER:
 * Implementa a nossa interface interna (RegistroNacionalService) adaptando
 * a chamada para a classe legada/externa do governo (MinisterioSaudeAPI).
 */
@Service
public class RegistroNacionalAdapter implements RegistroNacionalService {
    
    private final MinisterioSaudeAPI apiExterna;

    public RegistroNacionalAdapter() {
        this.apiExterna = new MinisterioSaudeAPI();
    }

    @Override
    public void sincronizarDoacaoNacional(Usuario usuario) {
        String cpfMascarado = usuario.getCpf().replaceAll("[^0-9]", "");
        String data = LocalDate.now().toString();
        
        // Chamando a API externa convertendo os nossos dados
        apiExterna.registrarDoadorNacional(cpfMascarado, data);
    }
}
