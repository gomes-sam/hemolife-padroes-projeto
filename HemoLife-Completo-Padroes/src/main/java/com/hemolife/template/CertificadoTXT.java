package com.hemolife.template;
import com.hemolife.model.Usuario;

public class CertificadoTXT extends GeradorCertificado {
    @Override
    protected String gerarCabecalho() {
        return "===================================\n" +
               "      CERTIFICADO HEMOLIFE         \n" +
               "===================================";
    }

    @Override
    protected String gerarCorpo(Usuario doador) {
        return "NOME: " + doador.getNome() + "\n" +
               "SANGUE: " + doador.getTipoSanguineo() + "\n" +
               "MENSAGEM: Você é um herói! Obrigado por doar.";
    }

    @Override
    protected String gerarRodape() {
        return "Ass: Direção do Banco de Sangue";
    }
}
