package banco.dao;

import banco.model.Cliente;
import banco.model.ContaCorrente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ContaCorrenteDAO {

    private static final Logger logger = Logger.getLogger(ContaCorrenteDAO.class.getName());

    public void salvar(ContaCorrente conta) {
        String sql = "INSERT INTO contas_correntes (numero_conta, saldo, limite_cheque, cliente_id) VALUES (?, ?, ?, ?)";
        Connection conn = ConexaoDB.getInstancia().getConexao();
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, conta.getNumeroConta());
                ps.setDouble(2, conta.getSaldo());
                ps.setDouble(3, conta.getLimiteChequeEspecial());
                ps.setLong(4, conta.getTitular().getId());
                ps.executeUpdate();
                try (ResultSet rsKeys = ps.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        conta.setId(rsKeys.getLong(1));
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao salvar ContaCorrente: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar conta corrente.", e);
        }
    }

    public ContaCorrente buscarPorNumero(String numeroConta) {
        String sql = "SELECT cc.id, cc.numero_conta, cc.saldo, cc.limite_cheque, " +
                     "c.id AS cliente_id, c.nome, c.cpf, c.telefone " +
                     "FROM contas_correntes cc " +
                     "JOIN clientes c ON cc.cliente_id = c.id " +
                     "WHERE cc.numero_conta = ?";
        try (PreparedStatement ps = ConexaoDB.getInstancia().getConexao().prepareStatement(sql)) {
            ps.setString(1, numeroConta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return montarContaCorrente(rs);
                }
            }
        } catch (SQLException e) {
            logger.severe("Erro ao buscar ContaCorrente por número: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar conta corrente.", e);
        }
        return null;
    }

    public List<ContaCorrente> listarTodas() {
        String sql = "SELECT cc.id, cc.numero_conta, cc.saldo, cc.limite_cheque, " +
                     "c.id AS cliente_id, c.nome, c.cpf, c.telefone " +
                     "FROM contas_correntes cc " +
                     "JOIN clientes c ON cc.cliente_id = c.id " +
                     "ORDER BY cc.id";
        List<ContaCorrente> lista = new ArrayList<>();
        try (PreparedStatement ps = ConexaoDB.getInstancia().getConexao().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(montarContaCorrente(rs));
            }
        } catch (SQLException e) {
            logger.severe("Erro ao listar ContasCorrente: " + e.getMessage());
            throw new RuntimeException("Erro ao listar contas correntes.", e);
        }
        return lista;
    }

    public void atualizar(ContaCorrente conta) {
        String sql = "UPDATE contas_correntes SET numero_conta = ?, saldo = ?, limite_cheque = ?, cliente_id = ? WHERE id = ?";
        Connection conn = ConexaoDB.getInstancia().getConexao();
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, conta.getNumeroConta());
                ps.setDouble(2, conta.getSaldo());
                ps.setDouble(3, conta.getLimiteChequeEspecial());
                ps.setLong(4, conta.getTitular().getId());
                ps.setLong(5, conta.getId());
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao atualizar ContaCorrente: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar conta corrente.", e);
        }
    }

    public void excluir(Long id) {
        String sqlTransacoes = "DELETE FROM transacoes WHERE conta_id = ? AND tipo_conta = 'CORRENTE'";
        String sqlConta = "DELETE FROM contas_correntes WHERE id = ?";
        Connection conn = ConexaoDB.getInstancia().getConexao();
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement psT = conn.prepareStatement(sqlTransacoes);
                 PreparedStatement psC = conn.prepareStatement(sqlConta)) {
                psT.setLong(1, id);
                psT.executeUpdate();
                psC.setLong(1, id);
                psC.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.severe("Erro ao excluir ContaCorrente: " + e.getMessage());
            throw new RuntimeException("Erro ao excluir conta corrente.", e);
        }
    }

    private ContaCorrente montarContaCorrente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente(
                rs.getLong("cliente_id"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("telefone")
        );
        return new ContaCorrente(
                rs.getLong("id"),
                rs.getString("numero_conta"),
                cliente,
                rs.getDouble("saldo"),
                rs.getDouble("limite_cheque")
        );
    }
}
