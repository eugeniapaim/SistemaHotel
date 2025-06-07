/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author 2024020399
 */
public class Reserva {
    private static final Map<Integer, Reserva> reservas = new HashMap<>();
    private int numeroReserva;
    private Quarto quarto;
    private Hospede hospede;
    private StatusReserva status;
    private int dias;
    private Pagamento pagamento;

    public Reserva(int numeroReserva, Quarto quarto, Hospede hospede, StatusReserva status, int dias) {
        this.numeroReserva = numeroReserva;
        this.quarto = quarto;
        this.hospede = hospede;
        this.status = status;
        this.dias = dias;
        this.pagamento = new Pagamento(this);
        reservas.put(numeroReserva, this);
    }

    public double calcularPrecoTotal() {
        return quarto.calcularPrecoTotal(dias);
    }

    public void confirmarReserva() {
        if (quarto.isOcupado()) {
            throw new QuartoIndisponivelException("Quarto já ocupado.");
        }
        quarto.ocupar();
        status = StatusReserva.CONFIRMADA;
    }

    public void cancelarReserva() {
        quarto.desocupar();
        status = StatusReserva.CANCELADA;
    }

    public int getNumeroReserva() {
        return numeroReserva;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public static Reserva buscarReserva(int numeroReserva) {
        return reservas.get(numeroReserva);
    }
    public class ReservaInvalidaException extends Exception {
        public ReservaInvalidaException(String message) {
            super(message);
        }
    }

}

