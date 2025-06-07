/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel;

/**
 *
 * @author 2024020399
 */
public class Pagamento {
    private Reserva reserva;

    public Pagamento(Reserva reserva) {
        this.reserva = reserva;
    }

    public void processarPagamento(double valor) {
        System.out.println("Pagamento de R$ " + valor + " realizado para a reserva " + reserva.getNumeroReserva());
    }
}


