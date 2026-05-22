package vvv.controller;
import vvv.model.PontoDeVendas;
import vvv.dao.PontoDeVendasDAO;
import vvv.dao.UsuarioDAO;

import java.util.List;


public class PontoDeVendasController {
    private PontoDeVendasDAO pontoDeVendasDAO;

    public PontoDeVendasController() {
        this.pontoDeVendasDAO = new PontoDeVendasDAO();
    }

    // Método para adicionar um PontoDeVendas
    public void adicionarPontoDeVendas(String nome, String endereco, String telefone) {
        PontoDeVendas ponto = new PontoDeVendas();
        ponto.setNome(nome);
        ponto.setEndereco(endereco);
        ponto.setTelefone(telefone);
        
        pontoDeVendasDAO.adicionarPontoDeVendas(ponto);
    }

    // Método para listar todos os PontosDeVendas
    public void listarPontosDeVendas() {
        List<PontoDeVendas> pontos = pontoDeVendasDAO.listarPontosDeVendas();
        for (PontoDeVendas ponto : pontos) {
            System.out.println("Nome: " + ponto.getNome() +
                               ", Endereço: " + ponto.getEndereco() +
                               ", Telefone: " + ponto.getTelefone());
        }
    }

    public void buscarPontoDeVendas(String nome) {
        PontoDeVendas ponto = pontoDeVendasDAO.buscarPontoDeVendas(nome);
        if (ponto != null) {
            System.out.println("Ponto de Vendas encontrado:");
            System.out.println( "Nome: " + ponto.getNome() +
                               ", Endereço: " + ponto.getEndereco() +
                               ", Telefone: " + ponto.getTelefone());
        } else {
            System.out.println("Ponto de Vendas não encontrado.");
        }
    }

    // Método para excluir um usuário pelo ID
    public void excluirUsuario(int id) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.excluirUsuario(id);

        if (sucesso) {
            System.out.println("Usuário com ID " + id + " foi excluído com sucesso.");
        } else {
            System.out.println("Erro ao excluir usuário com ID " + id + ". Verifique se o ID existe.");
        }
    }
}
