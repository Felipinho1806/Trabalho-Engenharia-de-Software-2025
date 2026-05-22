package vvv.dao;

import vvv.model.PontoDeVendas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PontoDeVendasDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/db_vvv"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "senha"; 

    // Método para estabelecer a conexão com o banco de dados
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para adicionar um PontoDeVendas no banco de dados
    public void adicionarPontoDeVendas(PontoDeVendas ponto) {
        String sql = "INSERT INTO pontoDeVendas (nome, endereco, telefone) VALUES (?, ?, ?)";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ponto.getNome());
            stmt.setString(2, ponto.getEndereco());
            stmt.setString(3, ponto.getTelefone());
            stmt.executeUpdate();
            System.out.println("Ponto de Vendas adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar os PontosDeVendas do banco de dados
    public List<PontoDeVendas> listarPontosDeVendas() {
        List<PontoDeVendas> pontos = new ArrayList<>();
        String sql = "SELECT * FROM pontoDeVendas";
        
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                
                PontoDeVendas ponto = new PontoDeVendas();
                ponto.setNome(nome);
                ponto.setEndereco(endereco);
                ponto.setTelefone(telefone);
                
                pontos.add(ponto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pontos;
    }

    // Método para buscar um PontoDeVendas pelo nome
    public PontoDeVendas buscarPontoDeVendas(String nome) {
        String sql = "SELECT * FROM pontoDeVendas WHERE nome = ?";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                PontoDeVendas ponto = new PontoDeVendas();
                ponto.setNome(rs.getString("nome"));
                ponto.setEndereco(rs.getString("endereco"));
                ponto.setTelefone(rs.getString("telefone"));
                
                return ponto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para excluir um PontoDeVendas pelo ID
    public boolean excluirPontoDeVendas(int id_pontoDeVendas) {
        String sql = "DELETE FROM pontoDeVendas WHERE id_pontoDeVendas = ?";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_pontoDeVendas);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}