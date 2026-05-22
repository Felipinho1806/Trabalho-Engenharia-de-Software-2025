package vvv.controller;
import vvv.model.Local;
import vvv.dao.LocalDAO;
import java.util.List;

public class LocalController {
    private LocalDAO localDAO;

    public LocalController() {
        this.localDAO = new LocalDAO();
    }

    // Método para adicionar um Local
    public void adicionarLocal(String nome, String endereco) {
        Local local = new Local();
        local.setNome(nome);
        local.setEndereco(endereco);
        
        localDAO.adicionarLocal(local);
    }

    // Método para listar todos os Locais
    public void listarLocais() {
        List<Local> locais = localDAO.listarLocais();
        for (Local local : locais) {
            System.out.println("Nome: " + local.getNome() +
                               ", Endereço: " + local.getEndereco());
        }
    }

    // Método para buscar um Local pelo nome
    public Local buscarLocalPorNome(String nome) {
        Local local = localDAO.buscarLocal(nome);
        if (local != null) {
            System.out.println("Local encontrado: " + local.getNome() + ", Endereço: " + local.getEndereco());
        } else {
            System.out.println("Local não encontrado.");
        }
        return local;
    }

    // Método para excluir um Local pelo nome
    public void excluirLocal(String nome) {
        boolean excluido = localDAO.excluirLocal(nome);
        if (excluido) {
            System.out.println("Local " + nome + " excluído com sucesso.");
        } else {
            System.out.println("Erro ao excluir o local: " + nome);
        }
    }
}