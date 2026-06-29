package banco.service;

import banco.dao.ContaCorrenteDAO;
import banco.dao.ContaPoupancaDAO;
import banco.dao.ConexaoDB;
import banco.dao.TransacaoDAO;
import banco.model.ContaBancaria;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;
import banco.model.Transacao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BancoService {

    private static final Logger logger = Logger.getLogger(BancoService.class.getName());

    private final ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
    private final ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
    private final TransacaoDAO transacaoDAO = new TransacaoDAO();


    public void cadastrarContaCorrente(ContaCorrente conta) {
        if (buscarConta(conta.getNumeroConta()) != null) {
            throw new IllegalArgumentException("Ja existe uma conta com este numero.");
        }
        contaCorrenteDAO.salvar(conta);
    }

    public void cadastrarContaPoupanca(ContaPoupanca conta) {
        if (buscarConta(conta.getNumeroConta()) != null) {
            throw new IllegalArgumentException("Ja existe uma conta com este numero.");
        }
        contaPoupancaDAO.salvar(conta);
    }


    public ContaBancaria buscarConta(String numeroConta) {
        ContaCorrente cc = contaCorrenteDAO.buscarPorNumero(numeroConta);
        if (cc != null) {
            return cc;
        }
        return contaPoupancaDAO.buscarPorNumero(numeroConta);
    }


    public void depositar(ContaBancaria conta, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do deposito deve ser maior que zero.");
        }
        conta.depositar(valor);
        atualizarSaldoConta(conta);
        registrarTransacao(conta, "DEPOSITO", valor);
    }


    public boolean sacar(ContaBancaria conta, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }
        boolean sucesso = conta.sacar(valor);
        if (sucesso) {
            atualizarSaldoConta(conta);
            String tipo = (conta instanceof ContaCorrente cc && cc.getSaldo() < 0) ? "SAQUE_CHEQUE_ESPECIAL" : "SAQUE";
            registrarTransacao(conta, tipo, valor);
        }
        return sucesso;
    }

    public boolean transferir(ContaBancaria origem, ContaBancaria destino, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da transferencia deve ser maior que zero.");
        }
        if (origem.getNumeroConta().equals(destino.getNumeroConta())) {
            throw new IllegalArgumentException("A conta de destino nao pode ser igual a conta de origem.");
        }

        if (!origem.sacar(valor)) {
            return false;
        }
        destino.depositar(valor);

        Connection conexao = ConexaoDB.getInstancia().getConexao();
        try {
            conexao.setAutoCommit(false);
            try {
                atualizarSaldoConta(origem);
                atualizarSaldoConta(destino);

                transacaoDAO.salvar(new Transacao(null, origem.getId(), tipoConta(origem),
                        "TRANSFERENCIA_ENVIADA para " + destino.getNumeroConta(), valor, LocalDateTime.now()), conexao);
                transacaoDAO.salvar(new Transacao(null, destino.getId(), tipoConta(destino),
                        "TRANSFERENCIA_RECEBIDA de " + origem.getNumeroConta(), valor, LocalDateTime.now()), conexao);

                conexao.commit();
                return true;
            } catch (SQLException e) {
                conexao.rollback();
                throw e;
            } finally {
                conexao.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao transferir entre contas", e);
            throw new RuntimeException("Erro ao transferir entre contas.", e);
        }
    }

    public void aplicarRendimento(ContaPoupanca conta) {
        conta.aplicarRendimento();
        atualizarSaldoConta(conta);
        registrarTransacao(conta, "RENDIMENTO", conta.getUltimoValorTransacao());
    }


    public double calcularPatrimonioTotal() {
        double total = 0;
        for (ContaBancaria conta : exibirRelatorioGeral()) {
            total += conta.getSaldo();
        }
        return total;
    }

    public List<ContaBancaria> exibirRelatorioGeral() {
        List<ContaBancaria> todas = new ArrayList<>();
        todas.addAll(contaCorrenteDAO.listarTodas());
        todas.addAll(contaPoupancaDAO.listarTodas());
        return todas;
    }


    private void atualizarSaldoConta(ContaBancaria conta) {
        if (conta instanceof ContaCorrente cc) {
            contaCorrenteDAO.atualizar(cc);
        } else if (conta instanceof ContaPoupanca cp) {
            contaPoupancaDAO.atualizar(cp);
        }
    }

    private void registrarTransacao(ContaBancaria conta, String tipo, double valor) {
        Transacao transacao = new Transacao(null, conta.getId(), tipoConta(conta), tipo, valor, LocalDateTime.now());
        transacaoDAO.salvar(transacao);
    }

    private String tipoConta(ContaBancaria conta) {
        return (conta instanceof ContaCorrente) ? "CORRENTE" : "POUPANCA";
    }
}
