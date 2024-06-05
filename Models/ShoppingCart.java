package Models;

import java.util.ArrayList;
import javax.swing.JOptionPane; //OK 100%


public class ShoppingCart {

    // Lista de produtos no carrinho de compras
    private ArrayList<Product> productsInCart = new ArrayList<>();


    // Conteúdo textual do carrinho de compras, representando os produtos adicionados
    private String cartContent = "";


    /**
    Adiciona um produto ao carrinho de compras. Se o produto já estiver no carrinho,
    atualiza a quantidade.
    
    @param product         O produto a ser adicionado ou atualizado.
    @param purchaseSystem  Sistema de compras associado.
    @param total           Quantidade total do produto.
    */
    public void addProduct(Product product, PurchaseSystem purchaseSystem, int total) {
        boolean productExists = false;

        // Percorre a lista de produtos no carrinho

        for (int i = 0; i < productsInCart.size(); i++) {
            Product cartProduct = productsInCart.get(i);

             // Verifica se o produto já está no carrinho pelo código
            if (cartProduct.getCodeProduct() == product.getCodeProduct()) {

                // Atualiza a quantidade do produto existente
                cartProduct.setQuantityQuestion(String.valueOf(total));

                productExists = true;
                break; // Sai do loop pois já encontrou o produto
            }
        }

        // Se o produto não existe na lista, adiciona como novo
        if (!productExists) {
            product.setQuantityQuestion(String.valueOf(total)); // Define a quantidade do novo produto
            productsInCart.add(product); // Adiciona o Produto a lista
        }

        // Atualiza o conteúdo textual do carrinho de compras
        StringBuilder updatedInvoiceContent = new StringBuilder();
        for (Product productA : productsInCart) {
            updatedInvoiceContent.append(String.format("""
                        \nCódigo:%d -  %s -  %.2f (Un)  - Qtd: %d
                        """, productA.getCodeProduct(), productA.getNameProduct(), productA.getValueProduct(),
                    productA.getQuantityQuestion()));
        }
        //String titulo = "Titulo";
        cartContent =  String.format("                         Itens na Sacola :)\n") +updatedInvoiceContent.toString();
        cartContent += String.format("\n\n Valor total: %,.2f ", purchaseSystem.getCart());// Adiciona o valor total à
    }


    public void displayCart(PurchaseSystem purchaseSystem, int totalQtd) {

        //cartContent += String.format("\n\n Valor total: %,.2f ", purchaseSystem.getCart());                                                              // nota fiscal

        JOptionPane.showMessageDialog(null, cartContent, null, JOptionPane.PLAIN_MESSAGE);
    }
}