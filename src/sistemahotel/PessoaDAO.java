package sistemahotel;

import java.util.HashMap;

public class PessoaDAO implements OperacoesDAO<Pessoa> {
    private final HashMap<String, Pessoa> pessoas = new HashMap<>();

    @Override
    public void inserir(Pessoa p) {
        pessoas.put(p.getCpf(), p);
    }

    @Override
    public boolean excluir(Pessoa p) {
        return pessoas.remove(p.getCpf()) != null;
    }

    @Override
    public boolean pesquisar(Pessoa p) {
        return pessoas.containsKey(p.getCpf());
    }

    @Override
    public Pessoa buscar(Pessoa p) {
        return pessoas.get(p.getCpf());
    }

    // Método extra (opcional): buscar por CPF diretamente
    public Pessoa buscarPorCpf(String cpf) {
        return pessoas.get(cpf);
    }
}
