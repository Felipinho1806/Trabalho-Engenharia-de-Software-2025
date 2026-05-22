package vvv.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import vvv.model.Cliente;

public class ClienteDAO {
    private static final String URL = "jdbc:mysql://localhost:3306";  
    private static final String USER = "root";  
    private static final String PASSWORD = "senha";  

    // Método para estabelecer conexão com o banco de dados
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para adicionar cliente no banco de dados
    public void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, email, senha, cpf, telefone) VALUES (?, ?, ?, ?, ?)"; 
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getSenha());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getTelefone());
            stmt.executeUpdate();
            System.out.println("Cliente adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar os clientes do banco de dados
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String cpf = rs.getString("cpf");
                String telefone = rs.getString("telefone");
                Cliente cliente = new Cliente(nome, email, senha, cpf, telefone); 
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

        // Método para buscar um cliente pelo email
        public Cliente buscarClientePorEmail(String email) {
            String sql = "SELECT * FROM cliente WHERE email = ?";
            
            try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    return new Cliente(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    
        // Método para excluir um cliente pelo ID
        public boolean excluirCliente(int id_cliente) {
            String sql = "DELETE FROM cliente WHERE id_cliente = ?";
            
            try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id_cliente);
                int linhasAfetadas = stmt.executeUpdate();
                return linhasAfetadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
}