package banco.service;
import banco.dao.UsuarioDAO;
import banco.model.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario autenticar(String login, String senha) {
        Usuario usuario = usuarioDAO.buscarPorLogin(login);
        if (usuario == null) {
            return null;
        }
        String hashDigitado = gerarHashSHA256(senha);
        if (hashDigitado.equals(usuario.getSenha())) {
            return usuario;
        }
        return null;
    }

    public Usuario buscarPorLogin(String login) {
        return usuarioDAO.buscarPorLogin(login);
    }

    public void cadastrar(Usuario usuario) {
        validarCampos(usuario);
        usuarioDAO.salvar(usuario);
    }

    public void atualizar(Usuario usuario) {
        validarCampos(usuario);
        usuarioDAO.atualizar(usuario);
    }

    public void excluir(Long id) {
        Usuario usuario = usuarioDAO.buscarPorId(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario nao encontrado.");
        }
        if (id == 1L) {
            throw new IllegalStateException("O usuario administrador inicial nao pode ser excluido.");
        }
        usuarioDAO.excluir(id);
    }

    public List<Usuario> listarTodos() {
        return usuarioDAO.listarTodos();
    }

    private void validarCampos(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome e obrigatorio.");
        }
        if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("Login e obrigatorio.");
        }
        if (usuario.getPerfil() == null || usuario.getPerfil().trim().isEmpty()) {
            throw new IllegalArgumentException("Perfil e obrigatorio.");
        }
    }

    public String gerarHashSHA256(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(texto.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo SHA-256 nao disponivel.", e);
        }
    }
}
