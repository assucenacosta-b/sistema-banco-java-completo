package banco.dao;

import banco.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario buscarPorLogin(String login) {
        String sql = "SELECT id, login, senha, nome, perfil FROM usuarios WHERE login = ?";
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuario por login.", e);
        }
        return null;
    }

    public Usuario buscarPorId(Long id) {
        String sql = "SELECT id, login, senha, nome, perfil FROM usuarios WHERE id = ?";
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuario por id.", e);
        }
        return null;
    }

    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (login, senha, nome, perfil) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());
            stmt.setString(4, usuario.getPerfil());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuario.", e);
        }
    }

    public void atualizar(Usuario usuario) {
       String sql = (usuario.getSenha() != null)
                ? "UPDATE usuarios SET login = ?, senha = ?, nome = ?, perfil = ? WHERE id = ?"
                : "UPDATE usuarios SET login = ?, nome = ?, perfil = ? WHERE id = ?";

        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            if (usuario.getSenha() != null) {
                stmt.setString(1, usuario.getLogin());
                stmt.setString(2, usuario.getSenha());
                stmt.setString(3, usuario.getNome());
                stmt.setString(4, usuario.getPerfil());
                stmt.setLong(5, usuario.getId());
            } else {
                stmt.setString(1, usuario.getLogin());
                stmt.setString(2, usuario.getNome());
                stmt.setString(3, usuario.getPerfil());
                stmt.setLong(4, usuario.getId());
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuario.", e);
        }
    }

    public void excluir(Long id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir usuario.", e);
        }
    }

    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, login, senha, nome, perfil FROM usuarios ORDER BY nome";

        try (Connection con = ConexaoDB.getInstancia().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuarios.", e);
        }
        return lista;
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getLong("id"));
        u.setLogin(rs.getString("login"));
        u.setSenha(rs.getString("senha"));
        u.setNome(rs.getString("nome"));
        u.setPerfil(rs.getString("perfil"));
        return u;
    }
}
