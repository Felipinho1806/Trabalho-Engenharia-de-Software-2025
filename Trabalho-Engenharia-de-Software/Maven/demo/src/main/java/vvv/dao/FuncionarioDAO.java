package vvv.dao;

import vvv.model.Funcionario;
import java.sql.*;

public class FuncionarioDAO {

    private Connection conexao;

    public FuncionarioDAO() {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_vvv", "usuario", "senha");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Funcionario buscarFuncionarioPorEmail(String email) {
        try {
            String sql = "SELECT * FROM funcionario WHERE email = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Funcionario(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getDouble("totalVendas")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean inserirFuncionario(Funcionario funcionario) {
        try {
            String sql = "INSERT INTO funcionario (nome, email, senha) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getEmail());
            stmt.setString(3, funcionario.getSenha());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirFuncionario(int id_funcionario) {
        try {
            String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_funcionario);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}