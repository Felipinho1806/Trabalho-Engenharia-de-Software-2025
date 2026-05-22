package vvv.dao;

import java.util.Date;
import vvv.model.Viagem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViagemDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/db_vvv";  // Substitua pelo nome do seu banco
    private static final String USER = "root";  // Substitua pelo seu usuário do MySQL
    private static final String PASSWORD = "senha";  // Substitua pela sua senha do MySQL

    // Método para estabelecer a conexão com o banco de dados
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para adicionar uma Viagem no banco de dados
    public void adicionarViagem(Viagem viagem) {
        String sql = "INSERT INTO viagem (data, horaPartida, horaChegada) VALUES (?, ?, ?)";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(viagem.getData().getTime()));
            stmt.setString(2, viagem.getHoraPartida());
            stmt.setString(3, viagem.getHoraChegada());
            stmt.executeUpdate();
            System.out.println("Viagem adicionada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todas as Viagens do banco de dados
    public List<Viagem> listarViagens() {
        List<Viagem> viagens = new ArrayList<>();
        String sql = "SELECT * FROM viagem";
        
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Date data = rs.getDate("data");
                String horaPartida = rs.getString("horaPartida");
                String horaChegada = rs.getString("horaChegada");
                
                Viagem viagem = new Viagem();
                viagem.setData(data);
                viagem.setHoraPartida(horaPartida);
                viagem.setHoraChegada(horaChegada);
                
                viagens.add(viagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viagens;
    }

    // Método para buscar uma viagem por data
    public Viagem buscarViagem(Date data) {
        String sql = "SELECT * FROM viagem WHERE data = ?";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(data.getTime()));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Viagem viagem = new Viagem();
                viagem.setData(rs.getDate("data"));
                viagem.setHoraPartida(rs.getString("horaPartida"));
                viagem.setHoraChegada(rs.getString("horaChegada"));

                return viagem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para excluir uma viagem pelo ID
    public boolean excluirViagem(int id_viagem) {
        String sql = "DELETE FROM viagem WHERE id_viagem = ?";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_viagem);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}