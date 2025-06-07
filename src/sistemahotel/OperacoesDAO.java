/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemahotel;

/**
 *
 * @author 2024020399
 */
public interface OperacoesDAO<T> {
    
    public void inserir(T t);
    
    public boolean excluir(T t);
    
    public boolean pesquisar(T t);
    
    public T buscar(T t);
    
}
