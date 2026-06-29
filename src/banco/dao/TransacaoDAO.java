package banco.dao;

import banco.model.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransacaoDAO {

    private static final Logger logger = Logger.getLogger(TransacaoDAO.class.getName());

    public void salvar(Transacao transacao, Connection conexao) throws SQLException {
        String sql = "INSERT INTO transacoes (conta_id, tipo_conta, descricao, valor, data_hora) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, transacao.getContaId());
            stmt.setString(2, transacao.getTipoConta());
            stmt.setString(3, transacao.getDescricao());
            stmt.setDouble(4, transacao.getValor());
            stmt.setTimestamp(5, Timestamp.valueOf(transacao.getDataHora()));
            stmt.executeUpdate();
        }
    }

     public void salvar(Transacao transacao) {
        Connection conexao = ConexaoDB.getInstancia().getConexao();
        try {
            salvar(transacao, conexao);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao salvar transacao", e);
            throw new RuntimeException("Erro ao salvar transacao.", e);
        }
    }

    public List<Transacao> listarPorConta(Long contaId, String tipoConta) {
        String sql = "SELECT id, conta_id, tipo_conta, descricao, valor, data_hora FROM transacoes "
                + "WHERE conta_id = ? AND tipo_conta = ? ORDER BY data_hora DESC";
        List<Transacao> lista = new ArrayList<>();
        Connection conexao = ConexaoDB.getInstancia().getConexao();
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, contaId);
            stmt.setString(2, tipoConta);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar transacoes da conta", e);
            throw new RuntimeException("Erro ao listar transacoes da conta.", e);
        }
        return lista;
    }

    private Transacao mapear(ResultSet rs) throws SQLException {
        Transacao t = new Transacao();
        t.setId(rs.getLong("id"));
        t.setContaId(rs.getLong("conta_id"));
        t.setTipoConta(rs.getString("tipo_conta"));
        t.setDescricao(rs.getString("descricao"));
        t.setValor(rs.getDouble("valor"));
        t.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
        return t;
    }
}
