/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemahotel;

/**
 *
 * @author 2024020399
 */
public enum TipoQuarto {
    SIMPLES(150.0),
    LUXO(250),
    SUITE(400);
    
    private final double precoPorNoite;
    
    TipoQuarto(double preco) {
        this.precoPorNoite = preco;
    }

    public double getPrecoPorNoite() {
        return precoPorNoite;
    }
}


