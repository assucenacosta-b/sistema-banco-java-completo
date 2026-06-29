package banco.model;

import banco.dao.TransacaoDAO;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContaPoupanca extends ContaBancaria {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final TransacaoDAO transacaoDAO = new TransacaoDAO();

    private double taxaRendimentoMensal;

    public ContaPoupanca() {
        super();
    }

    public ContaPoupanca(Long id, String numeroConta, Cliente titular, double saldo, double taxaRendimentoMensal) {
        super(id, numeroConta, titular, saldo);
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }

    public double getTaxaRendimentoMensal() {
        return taxaRendimentoMensal;
    }

    public void setTaxaRendimentoMensal(double taxaRendimentoMensal) {
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }

    public double calcularRendimento() {
        return saldo * (taxaRendimentoMensal / 100.0);
    }

    public void aplicarRendimento() {
        double rendimento = calcularRendimento();
        this.saldo += rendimento;
        registrarTransacao("RENDIMENTO", rendimento);
    }

    @Override
    public List<String[]> gerarExtrato() {
        List<String[]> linhas = new ArrayList<>();
        if (id == null) {
            return linhas;
        }
        for (banco.model.Transacao t : transacaoDAO.listarPorConta(id, "POUPANCA")) {
            linhas.add(new String[]{
                    t.getDataHora().format(FORMATO_DATA),
                    t.getDescricao(),
                    "Conta Poupanca " + numeroConta,
                    String.format("R$ %.2f", t.getValor())
            });
        }
        return linhas;
    }
}