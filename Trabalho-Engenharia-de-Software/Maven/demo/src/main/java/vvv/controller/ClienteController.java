package vvv.controller;
import java.util.List;
import vvv.dao.ClienteDAO;
import vvv.model.Cliente;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public void adicionarCliente(String nome, String email, int cpf, String telefone) {
        Cliente cliente = new Cliente();
        clienteDAO.adicionarCliente(cliente);
    }

    public void listarClientes() {
        List<Cliente> clientes = clienteDAO.listarClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public Cliente buscarClientePorEmail(String email) {
        Cliente cliente = clienteDAO.buscarClientePorEmail(email);
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
        return cliente;
    }
}
