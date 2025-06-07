/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel;

/**
 *
 * @author 2024020399
 */
public class Funcionario extends Pessoa{
    private String cargo;

    public Funcionario(String nome, String cpf, String email, String cargo) {
        super(nome, cpf, email);
        this.cargo = cargo;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Funcionário: " + getNome() + ", Cargo: " + cargo);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}


