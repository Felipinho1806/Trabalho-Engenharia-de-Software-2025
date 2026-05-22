package vvv.dao;
import vvv.model.Bilhete;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BilheteDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/db_vvv";  
    private static final String USER = "root";  // Verificar se USER e PASSWORD estão de acordo com o bd !
    private static final String PASSWORD = "senha";  

    // Método para estabelecer a conexão com o banco de dados
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para adicionar um Bilhete no banco de dados
    public void adicionarBilhete(Bilhete bilhete) {
        String sql = "INSERT INTO bilhete (data_emissao) VALUES (?)";
        
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(bilhete.getDataEmissao().getTime()));
            stmt.executeUpdate();
            System.out.println("Bilhete adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os Bilhetes do banco de dados
    public List<Bilhete> listarBilhetes() {
        List<Bilhete> bilhetes = new ArrayList<>();
        String sql = "SELECT * FROM bilhete";
        
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Date dataEmissao = rs.getDate("data_emissao");
                
                Bilhete bilhete = new Bilhete();
                bilhete.setDataEmissao(dataEmissao);
                
                bilhetes.add(bilhete);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bilhetes;
    }

        // Método para buscar um bilhete pelo ID
        public Bilhete buscarBilhete(int id_bilhete) {
            String sql = "SELECT * FROM bilhete WHERE id_bilhete = ?";
            try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id_bilhete);
                ResultSet rs = stmt.executeQuery();
    
                if (rs.next()) {
                    Bilhete bilhete = new Bilhete();
                    bilhete.setDataEmissao(rs.getDate("data_emissao"));
                    return bilhete;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null; // Retorna null se não encontrar o bilhete
        }
    
        // Método para excluir um bilhete pelo ID
        public boolean excluirBilhete(int id_bilhete) {
            String sql = "DELETE FROM bilhete WHERE id_bilhete = ?";
            try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id_bilhete);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0; // Retorna true se o bilhete foi excluído com sucesso
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    
}