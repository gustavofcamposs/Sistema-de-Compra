package Models;


import java.util.Random;//import para criar o codio pix

import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.TimerTask;

public class pagamento {

    /*public String EscolherFormaDePagamento (){

        String[] opcoes = {"Dinheiro", "Pix", "Cartão"};
        String formaPagamento = (String) JOptionPane.showInputDialog(null, "Selecione a forma de pagamento:",
                "Forma de Pagamento", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        return formaPagamento;
    }*/


    String validadorResposta = "";

    // Método para pagamento via PIX
    public void pagarComPix(double valor) {

        JOptionPane.showMessageDialog(null, """
                            Para o pagamento via pix 
                Te mandaremos um codigo de chave aleatoria pix!
                """, null, JOptionPane.WARNING_MESSAGE);
    
        Random random = new Random();
        StringBuilder serie = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            if (i % 2 == 0) { // Se o índice é par, adicione uma letra
                char letra = (char) ('A' + random.nextInt(26));
                serie.append(letra);
            } else { // Caso contrário, adicione um número
                int numero = random.nextInt(10); // Números de 0 a 9
                serie.append(numero);
            }
        }
    
        // Exibe a chave PIX gerada
        JOptionPane.showMessageDialog(null, "Sua chave PIX é: " + serie.toString());
    
        System.out.println("AA");
    
        // Configura o cronômetro para 15 segundos
        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                try {
                    JOptionPane.showMessageDialog(null, "Pagamento via PIX efetuado com sucesso.");
                    System.out.println("BB");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Exibe a mensagem após 15 segundos
                timer.cancel(); // Cancela o cronômetro
            }
        };
    
        // Agenda a tarefa para ser executada após 15 segundos (15000 milissegundos)
        timer.schedule(tarefa, 15000);
        System.out.println("CC");
    }


    // Método para pagamento em dinheiro
    public void pagarEmDinheiro(double valor) {

        boolean validador = true;
        while (validador) {

            //Puxando o quanto em dinheiro o cliente tem.
            double quantiaDinheiro = Double.parseDouble(JOptionPane.showInputDialog(null, """
                            Para o pagamento por dinheiro
                    Coloque o quanto dinheiro, você tem?
                    """));
            // Verificar se a quantia em dinheiro é suficiente
            if (quantiaDinheiro >= valor) {
                //verificação de troco
                double troco = quantiaDinheiro - valor;
                if (troco > 0){
                    //Ajustado
                    validadorResposta = String.format("Pagamento de R$ %,.2f realizado com sucesso em dinheiro. Seu troco é de R$ %,.2f .", valor, troco);
                    JOptionPane.showMessageDialog(null, validadorResposta);
                }else{
                    validadorResposta = String.format("Pagamento de R$ %,.2f realizado com sucesso em dinheiro.", valor);
                    JOptionPane.showMessageDialog(null, validadorResposta);
                }
                validador = false;
            } else {
                JOptionPane.showMessageDialog(null, "Quantia em dinheiro insuficiente para realizar o pagamento!!");
                String dinheiroInsuficiente = JOptionPane.showInputDialog(null, "Deseja novamente tentar o pagamento?");
                if (dinheiroInsuficiente.equals("Não")) {
                    validador = false;
                }
            }
        }
    }

    // Método para pagamento com cartão
    public void pagarComCartao(double valor) {

        String[] opcoes = {"Crédito", "Débito"};
        String tipoPagamento = (String) JOptionPane.showInputDialog(null, "Selecione o tipo de pagamento:",
                "Tipo de Pagamento", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (tipoPagamento.equals("Crédito")) {
            boolean validador = false;
            int numParcelas;

            while (!validador) {
                String[] parcelas = {"1", "2", "3", "4", "5"};

                String numParcelasStr = (String) JOptionPane.showInputDialog(null, "Selecione o número de parcelas:",
                        "Número de Parcelas", JOptionPane.QUESTION_MESSAGE, null, parcelas, parcelas[0]);

                // Se o usuário clicar em Cancelar, saia do loop
                if (numParcelasStr == null) {
                    int escolha = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar a operação?",
                            "Cancelar Operação", JOptionPane.YES_NO_OPTION);
                    if (escolha == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Operação cancelada.");
                    }
                } else {
                    numParcelas = Integer.parseInt(numParcelasStr);

                    // Aplicar juros dependendo do número de parcelas
                    double valorComJuros = valor;
                    switch (numParcelas) {
                        case 1:
                            validador = true;
                            break;

                        case 2:
                            valorComJuros = valor + (valor * 0.1);
                            validador = true;
                            break;

                        case 3:
                            valorComJuros = valor + (valor * 0.2);
                            validador = true;
                            break;

                        case 4:
                            valorComJuros = valor + (valor * 0.3);
                            validador = true;
                            break;

                        case 5:
                            valorComJuros = valor + (valor * 0.4);
                            validador = true;
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida. Selecione um número de parcelas válido.");
                    }

                    if (validador) {
                        //Ajeitado 
                        validadorResposta = String.format("Pagamento de R$ %,.2f realizado com sucesso em %d vezes, dando o total de R$ %,.2f",
                        valor, numParcelas, valorComJuros);
                        JOptionPane.showMessageDialog(null, validadorResposta);
                    }
                }
            }
        }else{
            //Ajeitado
            validadorResposta = String.format("Pagamento de R$ %,.2f realizado com sucesso (Débito).", valor);
            JOptionPane.showMessageDialog(null, validadorResposta);
        }
    }
}
