package banco.model;
import banco.dao.TransacaoDAO;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class ContaCorrente extends ContaBancaria {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final TransacaoDAO transacaoDAO = new TransacaoDAO();
    private double limiteChequeEspecial;
    public ContaCorrente() {
        super();
    }
    public ContaCorrente(Long id, String numeroConta, Cliente titular, double saldo, double limiteChequeEspecial) {
        super(id, numeroConta, titular, saldo);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }
    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }
    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }
    public double getLimiteDisponivel() {
        if (saldo < 0) {
            return limiteChequeEspecial + saldo;
        }
        return limiteChequeEspecial;
    }
    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            return false;
        }
        if (valor > saldo + limiteChequeEspecial) {
            return false;
        }
        this.saldo -= valor;
        registrarTransacao(saldo < 0 ? "SAQUE_CHEQUE_ESPECIAL" : "SAQUE", valor);
        return true;
    }
    @Override
    public void depositar(double valor) {
        this.saldo += valor;
        registrarTransacao("DEPOSITO", valor);
    }
    @Override
    public List<String[]> gerarExtrato() {
        List<String[]> linhas = new ArrayList<>();
        if (id == null) {
            return linhas;
        }
        for (banco.model.Transacao t : transacaoDAO.listarPorConta(id, "CORRENTE")) {
            linhas.add(new String[]{
                    t.getDataHora().format(FORMATO_DATA),
                    t.getDescricao(),
                    "Conta Corrente " + numeroConta,
                    String.format("R$ %.2f", t.getValor())
            });
        }
        return linhas;
    }


}