package com.hemolife;

import com.hemolife.facade.ProcessoDoacaoFacade;
import com.hemolife.model.Usuario;
import com.hemolife.repository.UsuarioRepository;
import com.hemolife.singleton.AuditoriaLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class HemoLifeDemoRunner implements CommandLineRunner {

    @Autowired
    private ProcessoDoacaoFacade doacaoFacade;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        AuditoriaLogger.getInstance().log("Sistema HemoLife iniciado em modo interativo.");

        boolean executando = true;

        while (executando) {
            System.out.println("\n==================================================");
            System.out.println("            HEMOLIFE - BANCO DE SANGUE            ");
            System.out.println("==================================================");
            System.out.println("1 - Realizar Nova Doação de Sangue");
            System.out.println("2 - Listar Doadores Cadastrados");
            System.out.println("0 - Encerrar Sistema");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    System.out.println("\n--- DADOS DO DOADOR ---");
                    System.out.print("Nome completo: ");
                    String nome = scanner.nextLine().trim();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine().trim();

                    System.out.print("E-mail: ");
                    String email = scanner.nextLine().trim();

                    System.out.print("Tipo Sanguíneo (ex: A+, O-, B+, AB-): ");
                    String tipoSanguineo = scanner.nextLine().trim().toUpperCase();

                    // Salva o novo doador na base de dados
                    Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setCpf(cpf);
                    usuario.setEmail(email);
                    usuario.setTipoSanguineo(tipoSanguineo);
                    usuario = usuarioRepository.save(usuario);

                    System.out.println("\nEscolha o formato do Certificado de Doação:");
                    System.out.println("1 - HTML (.html)");
                    System.out.println("2 - TXT (.txt)");
                    System.out.print("Opção: ");
                    String formatoOpcao = scanner.nextLine().trim();
                    String formato = formatoOpcao.equals("2") ? "TXT" : "HTML";

                    // Aciona todos os padrões integrados através da Facade
                    System.out.println();
                    doacaoFacade.realizarDoacao(usuario.getId(), formato);
                    break;

                case "2":
                    System.out.println("\n--- LISTA DE DOADORES E PONTOS ACUMULADOS ---");
                    List<Usuario> doadores = usuarioRepository.findAll();
                    if (doadores.isEmpty()) {
                        System.out.println("Nenhum doador registrado no momento.");
                    } else {
                        for (Usuario u : doadores) {
                            System.out.println("ID: " + u.getId() +
                                    " | Nome: " + u.getNome() +
                                    " | Sangue: " + u.getTipoSanguineo() +
                                    " | Pontos: " + u.getPontosAcumulados());
                        }
                    }
                    break;

                case "0":
                    System.out.println("\nEncerrando o sistema HemoLife...");
                    AuditoriaLogger.getInstance().log("Sistema HemoLife finalizado.");
                    executando = false;
                    break;

                default:
                    System.out.println("Opção inválida! Escolha 1, 2 ou 0.");
            }
        }
    }
}