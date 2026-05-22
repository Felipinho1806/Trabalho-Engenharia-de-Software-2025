package vvv.dao;

import vvv.model.ProprietarioModal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioModalDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/db_vvv"; 
    private static final String USER = "root";  
    private static final String PASSWORD = "senha"; 

    // Método para estabelecer a conexão com o banco de dados
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para adicionar um ProprietarioModal no banco de dados
    public void adicionarProprietarioModal(ProprietarioModal proprietario) {
        String sql = "INSERT INTO proprietario_modal (email, telefone) VALUES (?, ?)";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, proprietario.getEmail());
            stmt.setString(2, proprietario.getTelefone());
            stmt.executeUpdate();
            System.out.println("Proprietário do Modal adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar os ProprietariosModal do banco de dados
    public List<ProprietarioModal> listarProprietarios() {
        List<ProprietarioModal> proprietarios = new ArrayList<>();
        String sql = "SELECT * FROM proprietario_modal";
        
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                
                ProprietarioModal proprietario = new ProprietarioModal();
                proprietario.setEmail(email);
                proprietario.setTelefone(telefone);
                
                proprietarios.add(proprietario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proprietarios;
    }

    // Método para buscar um ProprietarioModal pelo email
    public ProprietarioModal buscarProprietario(String email) {
        String sql = "SELECT * FROM proprietarioModal WHERE email = ?";
        ProprietarioModal proprietario = null;
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                proprietario = new ProprietarioModal();
                proprietario.setEmail(rs.getString("email"));
                proprietario.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proprietario;
    }

    // Método para excluir um ProprietarioModal pelo email
    public boolean excluirProprietario(String email) {
        String sql = "DELETE FROM proprietarioModal WHERE email = ?";
        boolean excluido = false;
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                excluido = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return excluido;
    }
}