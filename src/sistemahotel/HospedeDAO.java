package sistemahotel;

import java.util.ArrayList;
import java.util.HashMap;

public class HospedeDAO implements OperacoesDAO<Hospede> {
    private final HashMap<String, Hospede> hospedes = new HashMap<>();

    @Override
    public void inserir(Hospede h) {
        hospedes.put(h.getCpf(), h);
    }

    @Override
    public boolean excluir(Hospede h) {
        return hospedes.remove(h.getCpf()) != null;
    }

    @Override
    public boolean pesquisar(Hospede h) {
        return hospedes.containsKey(h.getCpf());
    }

    @Override
    public Hospede buscar(Hospede h) {
        return hospedes.get(h.getCpf());
    }

    // Método extra opcional: buscar por CPF diretamente
    public Hospede buscarPorCpf(String cpf) {
        return hospedes.get(cpf);
    }
    
    public Hospede buscarPorEmail(String email) {
    for (Hospede h : hospedes.values()) {
        if (h.getEmail().equalsIgnoreCase(email)) {
            return h;
        }
    }
    return null;
    }
    
    private ArrayList<Reserva> reservas = new ArrayList<>();

    public void adicionarReserva(Reserva r) {
        reservas.add(r);
    }
    
    

    
}
