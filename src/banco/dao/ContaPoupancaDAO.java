package banco.dao;

import banco.model.Cliente;
import banco.model.ContaPoupanca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaPoupancaDAO {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void salvar(ContaPoupanca conta) {
        String sql = "INSERT INTO contas_poupanca (numero_conta, saldo, taxa_rendimento, cliente_id) VALUES (?, ?, ?, ?)";
        Connection conexao = ConexaoDB.getInstancia().getConexao();
        try {
            conexao.setAutoCommit(false);
            try (PreparedStatement stmt = conexao.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, conta.getNumeroConta());
                stmt.setDouble(2, conta.getSaldo());
                stmt.setDouble(3, conta.getTaxaRendimentoMensal());
                stmt.setLong(4, conta.getTitular().getId());
                stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        conta.setId(rs.getLong(1));
                    }
                }
                conexao.commit();
            } catch (SQLException e) {
                conexao.rollback();
                throw e;
            } finally {
                conexao.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar conta poupanca: " + e.getMessage(), e);
        }
    }

    public ContaPoupanca buscarPorNumero(String numeroConta) {
        String sql = "SELECT id, numero_conta, saldo, taxa_rendimento, cliente_id FROM contas_poupanca WHERE numero_conta = ?";
        Connection conexao = ConexaoDB.getInstancia().getConexao();
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, numeroConta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar conta poupanca por numero: " + e.getMessage(), e);
        }
        return null;
    }

    public List<ContaPoupanca> listarTodas() {
        String sql = "SELECT id, numero_conta, saldo, taxa_rendimento, cliente_id FROM contas_poupanca ORDER BY numero_conta";
        List<ContaPoupanca> lista = new ArrayList<>();
        Connection conexao = ConexaoDB.getInstancia().getConexao();
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar contas poupanca: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizar(ContaPoupanca conta) {
        String sql = "UPDATE contas_poupanca SET numero_conta = ?, saldo = ?, taxa_rendimento = ?, cliente_id = ? WHERE id = ?";
        Connection conexao = ConexaoDB.getInstancia().getConexao();
        try {
            conexao.setAutoCommit(false);
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, conta.getNumeroConta());
                stmt.setDouble(2, conta.getSaldo());
                stmt.setDouble(3, conta.getTaxaRendimentoMensal());
                stmt.setLong(4, conta.getTitular().getId());
                stmt.setLong(5, conta.getId());
                stmt.executeUpdate();
                conexao.commit();
            } catch (SQLException e) {
                conexao.rollback();
                throw e;
            } finally {
                conexao.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar conta poupanca: " + e.getMessage(), e);
        }
    }

    public void excluir(Long id) {
        String sqlTransacoes = "DELETE FROM transacoes WHERE conta_id = ? AND tipo_conta = 'POUPANCA'";
        String sqlConta = "DELETE FROM contas_poupanca WHERE id = ?";
        Connection conexao = ConexaoDB.getInstancia().getConexao();
        try {
            conexao.setAutoCommit(false);
            try (PreparedStatement psT = conexao.prepareStatement(sqlTransacoes);
                 PreparedStatement psC = conexao.prepareStatement(sqlConta)) {
                psT.setLong(1, id);
                psT.executeUpdate();
                psC.setLong(1, id);
                psC.executeUpdate();
                conexao.commit();
            } catch (SQLException e) {
                conexao.rollback();
                throw e;
            } finally {
                conexao.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir conta poupanca: " + e.getMessage(), e);
        }
    }

    private ContaPoupanca mapear(ResultSet rs) throws SQLException {
        Cliente titular = clienteDAO.buscarPorId(rs.getLong("cliente_id"));
        return new ContaPoupanca(
                rs.getLong("id"),
                rs.getString("numero_conta"),
                titular,
                rs.getDouble("saldo"),
                rs.getDouble("taxa_rendimento")
        );
    }
}