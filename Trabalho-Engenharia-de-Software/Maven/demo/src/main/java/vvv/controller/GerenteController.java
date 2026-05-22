package vvv.controller;
import vvv.dao.GerenteDAO;
import vvv.model.Gerente;

public class GerenteController {
    
    private Gerente gerenteLogado;
    
    public boolean logar(String email, String senha) {
        GerenteDAO gerenteDAO = new GerenteDAO();
        Gerente gerente = gerenteDAO.buscarGerentePorEmail(email);

        if (gerente != null && gerente.getSenha().equals(senha)) {
            this.gerenteLogado = gerente;
            System.out.println("Gerente logado: " + gerente.getNome());
            return true;
        } else {
            System.out.println("Email ou senha incorretos.");
            return false;
        }
    }

    public void deslogar() {
        if (gerenteLogado != null) {
            System.out.println("Gerente " + gerenteLogado.getNome() + " deslogado.");
            this.gerenteLogado = null;
        } else {
            System.out.println("Nenhum gerente está logado.");
        }
    }

    public boolean cadastrarGerente(String nome, String email, String senha, String cargo) {
        GerenteDAO gerenteDAO = new GerenteDAO();
        boolean sucesso = gerenteDAO.inserirGerente(new Gerente(nome, email, senha, 0));

        if (sucesso) {
            System.out.println("Gerente cadastrado com sucesso!");
            return true;
        } else {
            System.out.println("Erro ao cadastrar gerente.");
            return false;
        }
    }

    public void registrarVenda() {
        if (gerenteLogado == null) {
            System.out.println("É necessário estar logado para registrar uma venda.");
            return;
        }

        gerenteLogado.registrarVenda();
    }

    public void aprovarReserva() {
        if (gerenteLogado == null) {
            System.out.println("É necessário estar logado para aprovar uma reserva.");
            return;
        }

        gerenteLogado.aprovarReserva();
    }

    public void registrarFuncionario() {
        if (gerenteLogado == null) {
            System.out.println("É necessário estar logado para registrar um funcionário.");
            return;
        }

        gerenteLogado.registrarFuncionario();
    }

    public void cadastrarModais() {
        if (gerenteLogado == null) {
            System.out.println("É necessário estar logado para cadastrar modais.");
            return;
        }

        gerenteLogado.cadastrarModais();
    }

    public void registrarLocais() {
        if (gerenteLogado == null) {
            System.out.println("É necessário estar logado para registrar locais.");
            return;
        }

        gerenteLogado.registrarLocais();
    }

    public Gerente getGerenteLogado() {
        return gerenteLogado;
    }
}
