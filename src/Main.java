import javax.swing.JOptionPane;
import Models.Employee;
import Models.ShoppingCart;
import Models.pagamento;
import Models.Product;
import Models.PurchaseSystem;   

public class Main {
    public static void main(String[] args) {

        //Objetos instanciados apenas para o uso de métodos.
        var mainProduct = new Product();
        var purchaseSystem = new PurchaseSystem();
        var shoppingCart = new ShoppingCart();
        var pagamento = new pagamento();

        // Funcionário da Empresa
        var employeeOne = new Employee(10201520, "Gustavo", "Campos");

        // Produtos da Empresa
        var product1 = new Product(1001, "Feijão Tio João", 15.50);
        var product2 = new Product(1002, "Arroz Camil", 39.90);
        var product3 = new Product(1003, "Leite Ninho", 6.50);
        var product4 = new Product(1004, "Nescau", 7.99);
        var product5 = new Product(1005, "Macarrão Barilla", 12.00);
        var product6 = new Product(1006, "Óleo de Soja Liza", 8.50);
        var product7 = new Product(1007, "Café Pilão", 10.00);
        var product8 = new Product(1008, "Açúcar União", 4.50);

        // Ação do Funcionário cadastrando esses produtos. Se o mesmo não for cadastrado não será reconhecido como um Produto em nosso PurchaseSystem
        employeeOne.registerProduct(product1);
        employeeOne.registerProduct(product2);
        employeeOne.registerProduct(product3);
        employeeOne.registerProduct(product4);
        employeeOne.registerProduct(product5);
        employeeOne.registerProduct(product6);
        employeeOne.registerProduct(product7);
        employeeOne.registerProduct(product8);

        // Ação que registra todos os produtos cadastrados de forma individual por cada Funcionário
        purchaseSystem.getRegisteredProducts(employeeOne);

        int total = 0;
        int[] totals = {0, 0, 0, 0, 0, 0, 0, 0};

        //Mensagem de Bem-vindo
        JOptionPane.showMessageDialog(null, "Seja bem-vindo ao SuperMarket");

        // Contador que auxilia na verificação de se algum produto foi cadastrado.
        int counterDecision2 = 0;

        boolean validationLoopDecision = true;
        while (validationLoopDecision) {

            //Se a condição for atendida o que está antes dos dois pontos será , caso contrário o após
            String messageDecision = (counterDecision2 == 0) 
            ? "Você está adicionando seu primeiro produto. Deseja continuar?" : "Deseja adicionar mais algum produto ao carrinho?";

            int decision = 
            JOptionPane.showConfirmDialog(null, messageDecision, "Ir para pagamento", JOptionPane.YES_OPTION);

            // Se sim, ele adiciona um Produto.
            if (decision == JOptionPane.YES_OPTION) {

                boolean validationLoopCode = true;
                while (validationLoopCode) {

                    //Necessário para o uso dos condicionais
                    int replyDisplayProduct = JOptionPane.showConfirmDialog(null,
                            "Deseja consultar nossa lista de produtos?", null, JOptionPane.YES_NO_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                    
                    if (replyDisplayProduct == JOptionPane.YES_OPTION) {
                            purchaseSystem.displayRegisteredProducts();
                    } else {
                        //Não mostrará a lista com todos os produtos em nosso Sistema de compra, cadastrado pelo funcionário
                    }
                    

                    //Código do Produto, valor será usado no Switch Case
                    mainProduct.setCodeProduct(JOptionPane.showInputDialog(null, "Código do Produto"));

                    // Trabalhando com exceção, mostrado no setCode.
                    if (mainProduct.getCodeProduct() == -1) { 
                        JOptionPane.showMessageDialog(null, "Cancelando a inserção do produto.", "null",
                                JOptionPane.WARNING_MESSAGE);
                        break; // Cancelando o Produto. O BREAK serve para quebrar e voltar ao laço anterior: validationLoopDecision
                    }

                    //Cases dos Código Válidos.
                    switch (mainProduct.getCodeProduct()) {
                        case 1001:
                            // Produto selecionado, vamos perguntar quantos o cliente deseja Adicionar
                            product1.setQuantityQuestion(JOptionPane.showInputDialog(null, "Quantos você deseja comprar?"));

                            // Trabalhando com exceção, mostrado no setQuantity.
                            if (product1.getQuantityQuestion() == -1) { 
                                JOptionPane.showMessageDialog(null, "Operação cancelada. O produto não foi adicionado.", "null", JOptionPane.WARNING_MESSAGE);
                                validationLoopCode = false; //Verificar se é necessário mesmo essa linha
                                break;
                            } else {
                                purchaseSystem.calculatePrice(product1); //Calcular o Valor de forma Individual

                                //Uso do Swap para registrar corretamente o valor
                                totals[0] += product1.getQuantityQuestion(); 
                                total = totals[0];

                                //Método responsável por Adicionar o Produto no carrinho de Compra
                                shoppingCart.addProduct(product1, purchaseSystem, total);
                            }

                            //Se o Produto foi adicionado, contador ++
                            ++counterDecision2;
                            validationLoopCode = false; // pergunte novamente se o usuário deseja adicionar o produto, voltando para o Primeiro Laço
                            break;

                        case 1002:
                            // Produto selecionado, vamos perguntar quantos o cliente deseja Adicionar
                            product2.setQuantityQuestion(JOptionPane.showInputDialog(null, "Quantos você deseja comprar?"));

                            if (product2.getQuantityQuestion() == -1) {
                                JOptionPane.showMessageDialog(null, "Cancelando a inserção do produto.", "null",
                                        JOptionPane.WARNING_MESSAGE);
                                validationLoopCode = false;
                                break;
                            } else {
                                purchaseSystem.calculatePrice(product2);

                                totals[1] += product2.getQuantityQuestion();
                                total = totals[1];

                                shoppingCart.addProduct(product2, purchaseSystem, total);
                            }

                            ++counterDecision2;
                            validationLoopCode = false; 
                            break;

                        case 1003:
                            product3.setQuantityQuestion(JOptionPane.showInputDialog(null, "Quantos você deseja comprar?"));
                            
                            if (product3.getQuantityQuestion() == -1) {
                                JOptionPane.showMessageDialog(null, "Cancelando a inserção do produto.", "null",
                                        JOptionPane.WARNING_MESSAGE);
                                validationLoopCode = false;
                                break;
                            } else {
                                purchaseSystem.calculatePrice(product3);

                                totals[2] += product3.getQuantityQuestion();
                                total = totals[2];

                                shoppingCart.addProduct(product3, purchaseSystem, total);
                            }

                            ++counterDecision2;
                            validationLoopCode = false;
                            break;

                        case 1004:
                            product4.setQuantityQuestion(JOptionPane.showInputDialog(null, "Quantos você deseja comprar?"));
                            
                            if (product4.getQuantityQuestion() == -1) {
                                JOptionPane.showMessageDialog(null, "Cancelando a inserção do produto.", "null",
                                        JOptionPane.WARNING_MESSAGE);
                                validationLoopCode = false;
                                break;
                            } else {
                                purchaseSystem.calculatePrice(product4);

                                totals[3] += product4.getQuantityQuestion();
                                total = totals[3];

                                shoppingCart.addProduct(product4, purchaseSystem, total);
                            }

                            ++counterDecision2;
                            validationLoopCode = false;
                            break;

                        case 1005:
                            product5.setQuantityQuestion(JOptionPane.showInputDialog(null, "Quantos você deseja comprar?"));

                            if (product5.getQuantityQuestion() == -1) {
                                JOptionPane.showMessageDialog(null, "Cancelando a inserção do produto.", "null",
                                        JOptionPane.WARNING_MESSAGE);
                                validationLoopCode = false;
                                break;
                            } else {
                                purchaseSystem.calculatePrice(product5);

                                totals[4] += product5.getQuantityQuestion();
                                total = totals[4];

                                shoppingCart.addProduct(product5, purchaseSystem, total);
                            }

                            ++counterDecision2;
                            validationLoopCode = false;
                            break;

                        case 1006:
                            product6.setQuantityQuestion(JOptionPane.showInputDialog(null, "Quantos você deseja comprar?"));

                            if (product6.getQuantityQuestion() == -1) {
                                JOptionPane.showMessageDialog(null, "Cancelando a inserção do produto.", "null",
                                        JOptionPane.WARNING_MESSAGE);
                                validationLoopCode = false;
                                break;
                            } else {
                                purchaseSystem.calculatePrice(product6);

                                totals[5] += product6.getQuantityQuestion();
                                total = totals[5];

                                shoppingCart.addProduct(product6, purchaseSystem, total);
                            }

                            ++counterDecision2;
                            validationLoopCode = false;
                            break;

                        case 1007:
                            product7.setQuantityQuestion(JOptionPane.showInputDialog(null, "Quantos você deseja comprar?"));

                            if (product7.getQuantityQuestion() == -1) {
                                JOptionPane.showMessageDialog(null, "Cancelando a inserção do produto.", "null",
                                        JOptionPane.WARNING_MESSAGE);
                                validationLoopCode = false;
                                break;
                            } else {
                                purchaseSystem.calculatePrice(product7);

                                totals[6] += product7.getQuantityQuestion();
                                total = totals[6];

                                shoppingCart.addProduct(product7, purchaseSystem, total);
                            }

                            ++counterDecision2;
                            validationLoopCode = false;
                            break;

                        case 1008:
                            product8.setQuantityQuestion(JOptionPane.showInputDialog(null, "Quantos você deseja comprar?"));

                            if (product8.getQuantityQuestion() == -1) {
                                JOptionPane.showMessageDialog(null, "Cancelando a inserção do produto.", "null",
                                        JOptionPane.WARNING_MESSAGE);
                                validationLoopCode = false;
                                break;
                            } else {
                                purchaseSystem.calculatePrice(product8);

                                totals[7] += product8.getQuantityQuestion();
                                total = totals[7];

                                shoppingCart.addProduct(product8, purchaseSystem, total);
                            }

                            ++counterDecision2;
                            validationLoopCode = false;
                            break;

                        default:

                            JOptionPane.showMessageDialog(null, "O produto selecionado não foi encontrado. Por favor, verifique o código e tente novamente.");
                            break;
                    }
                }
            } else { // Senão ele finaliza a compra
                if (purchaseSystem.getCart() == 0) {
                    JOptionPane.showMessageDialog(null, "Nenhum produto foi adicionado!");
                    break;
                }

                Object[] options = {"Visualizar Itens no Carrinho", "Finalizar Compra"};

                // Exibe o JOptionPane com as opções personalizadas
                int reply = JOptionPane.showOptionDialog(
                    null,
                    "Seus produtos estão na sacola. O que gostaria de fazer a seguir",
                    "Confirmação",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
                );

                if (reply == 0) {
                    shoppingCart.displayCart(purchaseSystem, total);
                    //System.exit(0);
                } else if (reply == 1) {
                    JOptionPane.showMessageDialog(null, "Processando pagamento. Por favor, aguarde...");

                    String[] opcoes = {"Cartão", "Pix", "Dinheiro"};
                    int formaPagamento = JOptionPane.showOptionDialog(
                        null, "Selecione a forma de pagamento:",
                        null,
                        
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]);

                    if (formaPagamento == 0) {
                        pagamento.pagarComCartao(purchaseSystem.getCart());
                        
                        System.exit(0);
                    } else if (formaPagamento == 1) {
                        pagamento.pagarComPix(purchaseSystem.getCart());

                        System.exit(0);
                    } else{
                        pagamento.pagarEmDinheiro(purchaseSystem.getCart());

                        System.exit(0);
                    }
                }
            }
        }
    }
}