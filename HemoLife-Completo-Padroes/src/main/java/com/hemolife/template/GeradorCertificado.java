package com.hemolife.template;
import com.hemolife.model.Usuario;
import com.hemolife.singleton.AuditoriaLogger;

/**
 * PADRÃO TEMPLATE METHOD:
 * Define a ordem de execução (o esqueleto) para gerar os comprovantes,
 * delegando os detalhes de formatação para as subclasses (TXT, HTML).
 */
public abstract class GeradorCertificado {
    
    // O Template Method é final para que a ordem não seja alterada
    public final void emitirCertificado(Usuario doador) {
        validarDados(doador);
        String cabecalho = gerarCabecalho();
        String corpo = gerarCorpo(doador);
        String rodape = gerarRodape();
        
        String documentoCompleto = cabecalho + "\n" + corpo + "\n" + rodape;
        salvarArquivo(documentoCompleto);
    }

    private void validarDados(Usuario doador) {
        if(doador == null) throw new IllegalArgumentException("Doador invalido!");
    }

    protected abstract String gerarCabecalho();
    protected abstract String gerarCorpo(Usuario doador);
    protected abstract String gerarRodape();

    private void salvarArquivo(String conteudo) {
        AuditoriaLogger.getInstance().log("Certificado emitido:\n" + conteudo + "\n----------------------");
    }
}
