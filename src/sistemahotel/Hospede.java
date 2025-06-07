/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel;

/**
 *
 * @author 2024020399
 */
public class Hospede extends Pessoa{
    private int numeroDeReservas;

    public Hospede(String nome, String cpf, String email, int numeroDeReservas) {
        super(nome, cpf, email);
        this.numeroDeReservas = numeroDeReservas;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Hóspede: " + getNome() + ", CPF: " + getCpf() + ", Email: " + getEmail());
    }

    public int getNumeroDeReservas() {
        return numeroDeReservas;
    }

    public void setNumeroDeReservas(int numeroDeReservas) {
        this.numeroDeReservas = numeroDeReservas;
    }
}


