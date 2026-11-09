package com.hemolife.adapter.externo;

/**
 * SISTEMA EXTERNO (Simulado):
 * Uma API do Governo (Ministério da Saúde) que não podemos alterar o código fonte.
 * Ela possui métodos próprios e formatos diferentes.
 */
public class MinisterioSaudeAPI {
    public void registrarDoadorNacional(String documentoCPF, String dataCompleta) {
        System.out.println("[MINISTÉRIO DA SAÚDE] Recebido registro nacional para CPF: " + documentoCPF + " na data: " + dataCompleta);
    }
}
