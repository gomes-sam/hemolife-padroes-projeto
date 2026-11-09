# HemoLife 2.0 - Sistema Completo Reconstruído 🩸

Este é o sistema **HemoLife** recriado do ZERO com a arquitetura completa do **Spring Boot (Java)**, e implementando nativamente os **6 Padrões de Projeto** exigidos de forma criativa e orgânica ao contexto de um banco de sangue!

## Estrutura do Sistema
O sistema roda sozinho através da classe `HemoLifeApplication.java` e usa um banco de dados em memória (`H2`) para facilitar a correção. Assim que for executado, a classe `HemoLifeDemoRunner.java` inicia automaticamente uma simulação no console demonstrando os padrões em funcionamento.

## Os 6 Padrões Utilizados e Suas Funções:

1. **SINGLETON (`singleton/AuditoriaLogger.java`)**
   - **Função:** Garante que o HemoLife possua apenas uma única instância (global) de registro de segurança e auditoria (logs) para rastrear o histórico do hemocentro, seguindo normas de saúde.

2. **ADAPTER (`adapter/RegistroNacionalAdapter.java`)**
   - **Função:** Atua como um tradutor. Adapta os dados de doação do HemoLife e envia-os para uma API externa do governo (Ministério da Saúde), permitindo que duas interfaces diferentes se comuniquem.

3. **STRATEGY (`strategy/PontuacaoStrategy.java`)**
   - **Função:** O HemoLife possui um sistema de gamificação de pontos. O padrão Strategy muda o algoritmo de cálculo de pontos em tempo de execução: doadores comuns recebem 20 pontos, mas se o doador for de um tipo sanguíneo raro (como O-), a estratégia muda automaticamente para entregar 100 pontos bônus.

4. **TEMPLATE METHOD (`template/GeradorCertificado.java`)**
   - **Função:** Define o "esqueleto" rígido para emitir um certificado de doação (Validar Dados -> Gerar Cabeçalho -> Gerar Corpo -> Salvar). As subclasses (`CertificadoTXT`, `CertificadoHTML`) apenas preenchem os "espaços em branco" sem alterar o processo de emissão.

5. **FACTORY METHOD (`factory/CertificadoFactory.java`)**
   - **Função:** É uma "fábrica". Ela centraliza e abstrai a criação dos certificados. O Controller do sistema só pede um formato (ex: "HTML"), e a Factory assume a responsabilidade de decidir qual classe instanciar (`new CertificadoHTML()`).

6. **FACADE (`facade/ProcessoDoacaoFacade.java`)**
   - **Função:** Simplificação. Realizar uma doação é complexo (salvar no banco, dar pontos, enviar para o governo, gerar certificado). A Facade agrupa toda essa complexidade dentro de um único método `realizarDoacao()`, deixando o sistema extremamente limpo e organizado.
