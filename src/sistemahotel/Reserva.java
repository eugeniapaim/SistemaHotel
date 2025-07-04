/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel;
import java.util.HashMap;
/**
 *
 * @author 2024020399
 */
public class Reserva {
    private static final HashMap<Integer, Reserva> reservas = new HashMap<>();
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

    public static HashMap<Integer, Reserva> getReservas() {
        return reservas;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public int getDias() {
        return dias;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    
    public double calcularPrecoTotal() {
        return quarto.calcularPrecoTotal(dias);
    }

    public void confirmarReserva() throws QuartoIndisponivelException {
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
    
    public class QuartoIndisponivelException extends Exception {
        public QuartoIndisponivelException(String message) {
            super(message);
        }
    }
    private static int proximoCodigoReserva = 1;

    private static int gerarCodigoReserva() {
    return proximoCodigoReserva++;
}



}

