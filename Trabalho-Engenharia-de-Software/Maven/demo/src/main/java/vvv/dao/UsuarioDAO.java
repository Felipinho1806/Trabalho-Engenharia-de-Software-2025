package vvv.dao;

import vvv.model.Usuario;
import java.sql.*;

public class UsuarioDAO {

    private Connection conexao;

    public UsuarioDAO() {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306", "usuario", "senha");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarUsuario(String email) {
        try {
            String sql = "SELECT * FROM usuario WHERE email = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean inserirUsuario(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirUsuario(int id_usuario) {
        try {
            String sql = "DELETE FROM usuario WHERE id_usuario = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id_usuario);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}