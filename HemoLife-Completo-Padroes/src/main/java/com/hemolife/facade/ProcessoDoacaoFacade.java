package com.hemolife.facade;

import com.hemolife.adapter.RegistroNacionalService;
import com.hemolife.factory.CertificadoFactory;
import com.hemolife.model.Doacao;
import com.hemolife.model.Usuario;
import com.hemolife.repository.DoacaoRepository;
import com.hemolife.repository.UsuarioRepository;
import com.hemolife.singleton.AuditoriaLogger;
import com.hemolife.strategy.PontuacaoPadrao;
import com.hemolife.strategy.PontuacaoStrategy;
import com.hemolife.strategy.PontuacaoUrgencia;
import com.hemolife.template.GeradorCertificado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * PADRÃO FACADE (Fachada):
 * Orquestra e unifica todos os microsserviços, padrões e regras do HemoLife.
 * O Controller ou o Menu interage SOMENTE com esta classe.
 */
@Service
public class ProcessoDoacaoFacade {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private DoacaoRepository doacaoRepository;
    @Autowired private RegistroNacionalService ministerioSaude;
    @Autowired private CertificadoFactory certificadoFactory;
    
    @Autowired private PontuacaoPadrao pontuacaoPadrao;
    @Autowired private PontuacaoUrgencia pontuacaoUrgencia;

    public void realizarDoacao(Long usuarioId, String tipoCertificado) {
        Optional<Usuario> optUser = usuarioRepository.findById(usuarioId);
        if (optUser.isEmpty()) return;
        Usuario doador = optUser.get();

        AuditoriaLogger.getInstance().log("Iniciando processo de doação para " + doador.getNome());

        // 1. STRATEGY: Calcular pontos com base no tipo sanguíneo (O- é raro)
        PontuacaoStrategy estrategia = doador.getTipoSanguineo().equals("O-") ? pontuacaoUrgencia : pontuacaoPadrao;
        int pontosGanhos = estrategia.calcularPontos(doador);
        doador.adicionarPontos(pontosGanhos);
        usuarioRepository.save(doador);
        AuditoriaLogger.getInstance().log("Pontos adicionados: " + pontosGanhos + ". Saldo atual: " + doador.getPontosAcumulados());

        // 2. Registar Doação no Banco
        Doacao novaDoacao = new Doacao(doador, LocalDateTime.now(), "CONCLUIDA");
        doacaoRepository.save(novaDoacao);

        // 3. ADAPTER: Notificar o Governo Federal
        ministerioSaude.sincronizarDoacaoNacional(doador);

        // 4. FACTORY + TEMPLATE METHOD: Emitir Certificado
        GeradorCertificado certificado = certificadoFactory.criarGerador(tipoCertificado);
        certificado.emitirCertificado(doador);
        
        AuditoriaLogger.getInstance().log("Processo de doação finalizado com sucesso.");
    }
}
