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
public class PessoaDAO implements OperacoesDAO<Pessoa>{
    
    private HashMap<String, Pessoa> pessoas = new HashMap<>();

    
    @Override
    public void inserir(Pessoa p) {
        // Aqui estamos usando o ID da pessoa como chave
        pessoas.put(p.getCpf(), p);  // Supondo que Pessoa tenha um método getId()
    }
    
    
}
