/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemahotel;

import sistemahotel.Reserva.QuartoIndisponivelException;

/**
 *
 * @author 2024020399
 */
public class SistemaHotel {

    //TODO: Criar interface de interação
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Quarto quarto1 = new Quarto(101, TipoQuarto.SIMPLES, 150.0);
        Quarto quarto2 = new Quarto(102, TipoQuarto.LUXO, 300.0);
        

        Hospede hospede1 = new Hospede("João Silva", "123.456.789-00", "joao@email.com", 5);
        Funcionario funcionario1 = new Funcionario("Carlos Souza", "987.654.321-00", "carlos@hotel.com", "Recepcionista");

        Reserva reserva1 = new Reserva(1, quarto1, hospede1, StatusReserva.PENDENTE, 3);

        try {
            reserva1.confirmarReserva();
            double valor = reserva1.calcularPrecoTotal();
            reserva1.getPagamento().processarPagamento(valor);
        } catch (QuartoIndisponivelException e) {
            System.out.println("Erro ao confirmar reserva: " + e.getMessage());
        }

        // Exibir informações
        hospede1.exibirInformacoes();
        funcionario1.exibirInformacoes();
    }
    
}
