package Models;

import javax.swing.JOptionPane; //OK 100%


public class Product {

    private int codeProduct; // Código do Produto 

    String nameProduct; // Nome do Produto
 
    double valueProduct; // Valor do Produto

    int quantityQuestion; // Quantidade 

    String message; 


    

    // Construtor para inicializar um produto com código, nome e valor.
    public Product(int codeProduct, String nameProduct, double valueProduct) {
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.valueProduct = valueProduct;
    }

    // Constructor Padrão, Usado para acessar os 'métodos acessores'.
    public Product() {

    }


    // Método para definir o código do produto
    public void setCodeProduct( String productCode ) {

        try {
            // Se o código for nulo (BOTÃO CANCELAR), define como -1 por padrão (Evitando a Exceção NullPointerException causado pelo .trim())
            if (productCode == null) {
                productCode = "-1"; //Usado P/ configurar o Laço de Repetição no MAIN
            } else if (productCode.trim().isEmpty()) {

                // Se o código estiver vazio, solicita que o usuário insira um valor
                boolean validationEmptyCode = true;
                while ( validationEmptyCode ) {
                    if (productCode.trim().isEmpty()) { // Enquando Código = isEmpty (VAZIO)

                        message = "Por favor, insira um valor válido para o código do produto.";
                        JOptionPane.showMessageDialog(null, message, null, JOptionPane.WARNING_MESSAGE);
                        productCode = JOptionPane.showInputDialog(null, "Por favor, digite o código novamente.");

                        if (productCode == null) { // NullPointerException, Configurando Botão Cancelar.
                            productCode = "-1";
                        }  

                    } else { //Se o código não for NULL (BotãoCancelar) || Vazio (BotãoOK): ele é um código Válido
                        validationEmptyCode = false;
                    }
                }

            }

            //Converter P/INT o código válido
            this.codeProduct = Integer.parseInt(productCode);

        } catch (NumberFormatException e) { // Caso Contenha caractere que impossibilida a conversão para INT.

            //Aviso de Erro
            JOptionPane.showMessageDialog(null, "O código do produto deve ser um número inteiro. Por favor, tente novamente.\"", null, JOptionPane.ERROR_MESSAGE);

            //Código recebe um Valor "Correto", resultado trabalhado no Laço validationLoopCode.
            productCode = "1";

            //Necessário converter para cair no Switch.
            this.codeProduct = Integer.parseInt(productCode);
        }
    }


    public void setQuantityQuestion(String quantityQuestion){
        try {
                        
            // Define -1 se o usuário clicar em Cancelar
            if (quantityQuestion == null) {
                quantityQuestion = "-1"; //Resultado Usado No Main
            } else if (quantityQuestion.trim().isEmpty()) {//Se for vazio

                // Solicita ao usuário que insira a quantidade se estiver vazio

                boolean quantatyLoop = true;
                while ( quantatyLoop ) {

                    if (quantityQuestion.trim().isEmpty()) {

                        //Equanto estiver vazio, mensagem de Erro
                        JOptionPane.showMessageDialog(null, "Por favor, insira um valor para a quantidade do produto.", null, JOptionPane.WARNING_MESSAGE);

                        message = "Por favor, insira a quantidade do produto.";
                        quantityQuestion = JOptionPane.showInputDialog(null, message);

                        // Define -1 se o usuário clicar em Cancelar
                        if (quantityQuestion == null) {
                            quantityQuestion = "-1";
                        }
                    } else{
                        //Se for um código Válido, quebrar o Looping
                        quantatyLoop = false;
                    } 
                
                }
            }

            //Necesário Converter P/Número para ser atendido pelas Condicionais
            this.quantityQuestion = Integer.parseInt(quantityQuestion);
            
        } catch(NumberFormatException e){
            System.out.println("Aa");
        }
    }


    //Método de Acesso, seus valores são armazenados em uma variável total e passados como parâmetro na Nota Fiscal.
    public int getQuantityQuestion(){
        return quantityQuestion;
    }


    // Métodos acessores para obter o nome, valor e código do produto, que serão puxados pelo Construtor Padrão
    public String getNameProduct() {
        return nameProduct;
    }

    public double getValueProduct() {
        return valueProduct;
    }

    public int getCodeProduct() {
        return codeProduct;
    }

    // Sobrescreve o método toString para fornecer uma representação significativa do produto
    @Override
      public String toString() {
        return String.format("""
            Nome do produto: %s
            Códio do produto: %d
            Valor do produto: %,.2f
                """, getNameProduct(), getCodeProduct(), getValueProduct());
    }
}