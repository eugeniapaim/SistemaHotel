/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel;

/**
 *
 * @author 2024020399
 */
public class Quarto {
    private int numero;
    private TipoQuarto tipo;
    private double precoPorNoite;
    private boolean ocupado;

    public Quarto(int numero, TipoQuarto tipo, double precoPorNoite) {
        this.numero = numero;
        this.tipo = tipo;
        this.precoPorNoite = precoPorNoite;
        this.ocupado = false;
    }

    public double calcularPrecoTotal(int dias) {
        return precoPorNoite * dias;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void ocupar() {
        this.ocupado = true;
    }

    public void desocupar() {
        this.ocupado = false;
    }

    public int getNumero() {
        return numero;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public double getPrecoPorNoite() {
        return precoPorNoite;
    }

    public void setPrecoPorNoite(double precoPorNoite) {
        this.precoPorNoite = precoPorNoite;
    }
    
    public class QuartoIndisponivelException extends RuntimeException {
    public QuartoIndisponivelException(String message) {
        super(message);
    }
}

}

