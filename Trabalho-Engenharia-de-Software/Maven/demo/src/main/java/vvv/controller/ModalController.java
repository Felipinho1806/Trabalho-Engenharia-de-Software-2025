package vvv.controller;
import vvv.model.Modal;
import vvv.dao.ModalDAO;
import java.util.List;

public class ModalController {
    private ModalDAO modalDAO;

    public ModalController() {
        this.modalDAO = new ModalDAO();
    }

    // Método para adicionar um Modal
    public void adicionarModal(int idProprietario, int capacidade, String tipo, String categoria, String marca) {
        Modal modal = new Modal();
        modal.setIdProprietario(idProprietario);
        modal.setCapacidade(capacidade);
        modal.setTipo(tipo);
        modal.setCategoria(categoria);
        modal.setMarca(marca);
        
        modalDAO.adicionarModal(modal);
    }

    // Método para listar todos os Modais
    public void listarModais() {
        List<Modal> modais = modalDAO.listarModais();
        for (Modal modal : modais) {
            System.out.println("Proprietário ID: " + modal.getIdProprietario() +
                               ", Capacidade: " + modal.getCapacidade() +
                               ", Tipo: " + modal.getTipo() +
                               ", Categoria: " + modal.getCategoria() +
                               ", Marca: " + modal.getMarca());
        }
    }

 // Método para buscar um Modal pelo id
    public void buscarModal(int id) {
        Modal modal = modalDAO.buscarModal(id);
        if (modal != null) {
            System.out.println("Modal encontrado:");
            System.out.println("Proprietário ID: " + modal.getIdProprietario() +
                               ", Capacidade: " + modal.getCapacidade() +
                               ", Tipo: " + modal.getTipo() +
                               ", Categoria: " + modal.getCategoria() +
                               ", Marca: " + modal.getMarca());
        } else {
            System.out.println("Modal não encontrado.");
        }
    }

    // Método para excluir um Modal pelo id
    public void excluirModal(int id) {
        boolean excluido = modalDAO.excluirModal(id);
        if (excluido) {
            System.out.println("Modal com ID " + id + " excluído com sucesso.");
        } else {
            System.out.println("Erro ao excluir o Modal com ID " + id);
        }
    }
}