package vvv.controller;
import vvv.dao.FuncionarioDAO;
import vvv.model.Funcionario;

public class FuncionarioController {
    
    private Funcionario funcionarioLogado;
    
    public boolean logar(String email, String senha) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorEmail(email);

        if (funcionario != null && funcionario.getSenha().equals(senha)) {
            this.funcionarioLogado = funcionario;
            System.out.println("Funcionário logado: " + funcionario.getNome());
            return true;
        } else {
            System.out.println("Email ou senha incorretos.");
            return false;
        }
    }

    public void deslogar() {
        if (funcionarioLogado != null) {
            System.out.println("Funcionário " + funcionarioLogado.getNome() + " deslogado.");
            this.funcionarioLogado = null;
        } else {
            System.out.println("Nenhum funcionário está logado.");
        }
    }

    public boolean cadastrarFuncionario(String nome, String email, String senha) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        boolean sucesso = funcionarioDAO.inserirFuncionario(new Funcionario(nome, email, senha, 0));

        if (sucesso) {
            System.out.println("Funcionário cadastrado com sucesso!");
            return true;
        } else {
            System.out.println("Erro ao cadastrar funcionário.");
            return false;
        }
    }

    public void realizarVenda() {
        if (funcionarioLogado == null) {
            System.out.println("É necessário estar logado para realizar uma venda.");
            return;
        }

        funcionarioLogado.realizarVenda();
    }

    public void consultarReserva() {
        if (funcionarioLogado == null) {
            System.out.println("É necessário estar logado para consultar uma reserva.");
            return;
        }

        funcionarioLogado.consultarReserva();
    }

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }
}