package com.hemolife.singleton;
import java.time.LocalDateTime;

/**
 * PADRÃO SINGLETON:
 * Classe única de auditoria de segurança e logs. O Ministério da Saúde exige
 * que todo hemocentro possua apenas um ponto focal de registros (instância única).
 */
public class AuditoriaLogger {
    private static AuditoriaLogger instance;

    private AuditoriaLogger() {}

    public static synchronized AuditoriaLogger getInstance() {
        if (instance == null) {
            instance = new AuditoriaLogger();
        }
        return instance;
    }

    public void log(String acao) {
        System.out.println("[AUDITORIA HEMOLIFE - " + LocalDateTime.now() + "] " + acao);
    }
}
