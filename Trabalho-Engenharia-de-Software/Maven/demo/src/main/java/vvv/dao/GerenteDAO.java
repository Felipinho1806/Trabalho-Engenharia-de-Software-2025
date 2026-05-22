package vvv.dao;

import vvv.model.Gerente;
import java.sql.*;

public class GerenteDAO {

    private Connection conexao;

    public GerenteDAO() {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306", "usuario", "senha");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Gerente buscarGerentePorEmail(String email) {
        try {
            String sql = "SELECT * FROM gerentes WHERE email = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Gerente(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getInt("id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean inserirGerente(Gerente gerente) {
        try {
            String sql = "INSERT INTO gerentes (nome, email, senha, cargo) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, gerente.getNome());
            stmt.setString(2, gerente.getEmail());
            stmt.setString(3, gerente.getSenha());


            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
