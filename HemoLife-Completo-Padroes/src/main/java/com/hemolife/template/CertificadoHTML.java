package com.hemolife.template;
import com.hemolife.model.Usuario;

public class CertificadoHTML extends GeradorCertificado {
    @Override
    protected String gerarCabecalho() {
        return "<html>\n<head><title>Certificado HemoLife</title></head>\n<body>";
    }

    @Override
    protected String gerarCorpo(Usuario doador) {
        return "<h1>Ato Heroico de Doação</h1>\n<p>Certificamos que <b>" + doador.getNome() + 
               "</b> (Sangue: " + doador.getTipoSanguineo() + ") doou sangue e salvou vidas!</p>";
    }

    @Override
    protected String gerarRodape() {
        return "</body>\n</html>";
    }
}
