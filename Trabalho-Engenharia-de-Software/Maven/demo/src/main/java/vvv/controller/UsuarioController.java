package vvv.controller;
import vvv.model.Usuario;

import vvv.dao.UsuarioDAO;

public class UsuarioController {
    
    private Usuario usuarioLogado;
    
    public boolean logar(String email, String senha) {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.buscarUsuario(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            this.usuarioLogado = usuario;
            System.out.println("Usuário logado com sucesso: " + usuario.getNome());
            return true;
        } else {
            System.out.println("Email ou senha incorretos.");
            return false;
        }
    }

    public void deslogar() {
        if (usuarioLogado != null) {
            System.out.println("Usuário " + usuarioLogado.getNome() + " deslogado.");
            this.usuarioLogado = null;
        } else {
            System.out.println("Nenhum usuário está logado.");
        }
    }

    public boolean cadastrarCliente(String nome, String email, String senha) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.inserirUsuario(new Usuario(nome, email, senha));

        if (sucesso) {
            System.out.println("Cliente cadastrado com sucesso!");
            return true;
        } else {
            System.out.println("Erro ao cadastrar cliente.");
            return false;
        }
    }

    public void solicitarReserva(String destino, String data) {
        if (usuarioLogado == null) {
            System.out.println("É necessário estar logado para solicitar uma reserva.");
            return;
        }

        System.out.println("Reserva solicitada para " + usuarioLogado.getNome() +
                " no destino " + destino + " na data " + data + ".");
        
       
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }


    // Método para excluir um usuário pelo ID
    public void excluirUsuarioPorId(int id) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.excluirUsuario(id);

        if (sucesso) {
            System.out.println("Usuário com ID " + id + " foi excluído com sucesso.");
        } else {
            System.out.println("Erro ao excluir usuário com ID " + id + ". Verifique se o ID existe.");
        }
    }    
}