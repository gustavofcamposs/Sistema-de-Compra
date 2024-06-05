package Models;

import java.util.ArrayList;
import javax.swing.JOptionPane; //OK 100%
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class PurchaseSystem {

    private double cart; // Total do carrinho de compras

    private ArrayList<Product> registeredProducts = new ArrayList<>(); // Lista de produtos cadastrados



    // Método para calcular o preço do produtoDa Compra, assim que ele falar a quantidade, esse valor é adicionado aqui.
    public void calculatePrice(Product product) {


        String messageForCart = "";
        if (product.getQuantityQuestion() == 1) {
            messageForCart = ("Seu produto foi adicionado!");
        } else {
            messageForCart = ("Seus produtos foram adicionado!");
        }

        cart += product.getQuantityQuestion() * product.getValueProduct();

        // Exibe a nota fiscal em uma janela de diálogo, de acordo com a mensagem acima
        JOptionPane.showMessageDialog(null, messageForCart);
    }


    //Armazena o valor do Produto*Quantidade, esse get é usado no Invoices.
    public double getCart() {
        return cart;
    }


    
    /*Método que armazena todos os produtos cadastrados pelo funcionário, ou seja, todos os produtos 
    existentes em nossa empresa, em nosso sistema de compra.*/
    public ArrayList<Product> getRegisteredProducts(Employee employee) {
        registeredProducts = employee.getRegisteredProductsList();
        return registeredProducts;
    }


    
    /*Método responsável por listar todos os produtos cadastrados pelo funcionário.Se eu instanciar um produto e não informar*
    que o funcionário o cadastrou, ele não deve aparecer nesta lista. Pois a nossa lista tem como parâmetro a lista de produtos
    cadastrados por cada funcionário. Esse método se conecta com o getRegisteredProducts que se relaciona com o Funcionário*/
    public void displayRegisteredProducts() {
        String messageTeste = "";
        for (Product product : registeredProducts) {
            if (product != null) {
                messageTeste += String.format("""
                    Código: %d - %s  R$: %.2f(Un)\n
                        """,  product.getCodeProduct(), product.getNameProduct(), product.getValueProduct());
            }
        }
                JTextArea textArea = new JTextArea(messageTeste.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(300, 200)); // Ajuste as dimensões conforme necessário

        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Produtos", JOptionPane.PLAIN_MESSAGE);
    }
}