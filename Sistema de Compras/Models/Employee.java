
package Models;


import java.util.ArrayList; //OK 100%


// Classe que define um funcionário
public class Employee {

    private int employeeRegistration; // Número de matricula do Funcionário

    private String firstName; //Primeiro nome do Funcionário

    @SuppressWarnings("unused")
    private String lastName; //Sobrenome do Funcionário

    // Lista de produtos cadastrados pelo funcionário
    private ArrayList<Product> registeredProductsList = new ArrayList<>();



    // Constructor: Define como um funcionário deve ser criado
    public Employee(int employeeRegistration, String firstName, String lastName) {
        this.employeeRegistration = employeeRegistration;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    // Método Cadastrar Produto pelo Funcionário. Recebe o produto padrão que será adicionado a lista dos Produtos Cadastrado
    public void registerProduct(Product productDefault) {
        registeredProductsList.add(productDefault); 
    }



    // 'Métodos Acessor', usado no toString()
    public String getFirstName() {
        return firstName;
    }

    public int getEmployeeRegistration() {
        return employeeRegistration;
    }


    // Método para obter a lista de produtos cadastrados pelo funcionário
    // É utilizado para passar os produtos cadastrados para o sistema de compras (PurchaseSystem.java).
    public ArrayList<Product> getRegisteredProductsList() {
        return registeredProductsList;
    }


    // Sobrescreve o método toString para fornecer uma representação significativa do funcionário
    @Override
    public String toString() {
        String messageToString = String.format("""
                Matricula: %s
                Nome: %s
                    """, getEmployeeRegistration(), getFirstName());
        return messageToString;
    }
}