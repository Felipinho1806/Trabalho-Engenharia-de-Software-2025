package vvv.dao;

import vvv.model.Reserva;
import java.sql.*;


public class ReservaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/db_vvv";  
    private static final String USER = "root";  
    private static final String PASSWORD = "senha";  

    
    // Método para estabelecer a conexão com o banco de dados
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para adicionar uma Reserva ao banco de dados
    public void adicionarReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (valor, data, status) VALUES (?, ?, ?)";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, reserva.getValor());
            stmt.setDate(2, new java.sql.Date(reserva.getData().getTime()));
            stmt.setString(3, reserva.getStatus());
            stmt.executeUpdate();
            System.out.println("Reserva adicionada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar uma Reserva por ID
    public Reserva buscarReserva(int id_reserva) {
        String sql = "SELECT * FROM reserva WHERE id_reserva = ?";
        Reserva reserva = null;

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_reserva);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                reserva = new Reserva();
                reserva.setValor(rs.getDouble("valor"));
                reserva.setData(rs.getDate("data"));
                reserva.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserva;
    }

    // Método para excluir uma Reserva por ID
    public boolean excluirReserva(int id_reserva) {
        String sql = "DELETE FROM reserva WHERE id_reserva = ?";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_reserva);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
