package vvv.controller;
import java.util.Date;
import vvv.model.Bilhete;
import vvv.dao.BilheteDAO;
import java.util.List;

public class BilheteController {
    private BilheteDAO bilheteDAO;
// 
    public BilheteController() {
        this.bilheteDAO = new BilheteDAO();
    }

    // Método para adicionar um Bilhete
    public void adicionarBilhete(Date dataEmissao) {
        Bilhete bilhete = new Bilhete();
        bilhete.setDataEmissao(dataEmissao);
        
        bilheteDAO.adicionarBilhete(bilhete);
    }

    // Método para listar todos os Bilhetes
    public void listarBilhetes() {
        List<Bilhete> bilhetes = bilheteDAO.listarBilhetes();
        for (Bilhete bilhete : bilhetes) {
            System.out.println("Data de Emissão: " + bilhete.getDataEmissao());
        }
    }

    // Método para buscar um Bilhete por ID
    public void buscarBilhete(int id_bilhete) {
        Bilhete bilhete = bilheteDAO.buscarBilhete(id_bilhete);
        if (bilhete != null) {
            System.out.println("Bilhete encontrado:");
            System.out.println("Data de Emissão: " + bilhete.getDataEmissao());
        } else {
            System.out.println("Bilhete com ID " + id_bilhete + " não encontrado.");
        }
    }

    // Método para excluir um Bilhete por ID
    public void excluirBilhetePorId(int id_bilhete) {
        boolean sucesso = bilheteDAO.excluirBilhete(id_bilhete);
        if (sucesso) {
            System.out.println("Bilhete com ID " + id_bilhete + " excluído com sucesso.");
        } else {
            System.out.println("Erro ao excluir bilhete com ID " + id_bilhete + ". Ele pode não existir.");
        }
    }
}