package com.hemolife.factory;
import com.hemolife.template.CertificadoHTML;
import com.hemolife.template.CertificadoTXT;
import com.hemolife.template.GeradorCertificado;
import org.springframework.stereotype.Component;

/**
 * PADRÃO FACTORY METHOD:
 * Fabrica a instância correta do certificado com base na escolha do usuário,
 * centralizando a regra de criação (New).
 */
@Component
public class CertificadoFactory {
    
    public GeradorCertificado criarGerador(String formato) {
        if (formato.equalsIgnoreCase("HTML")) {
            return new CertificadoHTML();
        } else if (formato.equalsIgnoreCase("TXT")) {
            return new CertificadoTXT();
        }
        throw new IllegalArgumentException("Formato de certificado não suportado: " + formato);
    }
}
