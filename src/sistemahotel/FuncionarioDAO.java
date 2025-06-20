package sistemahotel;

import java.util.HashMap;
import java.util.Map;

public class FuncionarioDAO implements OperacoesDAO<Funcionario> {
    private final Map<String, Funcionario> funcionarios = new HashMap<>();

    @Override
    public void inserir(Funcionario f) {
        funcionarios.put(f.getCpf(), f);
    }

    @Override
    public boolean excluir(Funcionario f) {
        return funcionarios.remove(f.getCpf()) != null;
    }

    @Override
    public boolean pesquisar(Funcionario f) {
        return funcionarios.containsKey(f.getCpf());
    }

    @Override
    public Funcionario buscar(Funcionario f) {
        return funcionarios.get(f.getCpf());
    }

    // Método extra (opcional): buscar por CPF diretamente
    public Funcionario buscarPorCpf(String cpf) {
        return funcionarios.get(cpf);
    }
}
