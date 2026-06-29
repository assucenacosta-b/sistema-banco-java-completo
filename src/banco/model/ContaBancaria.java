package banco.model;

import banco.interfaces.Operavel;
import java.util.List;

public abstract class ContaBancaria implements Operavel {

    protected Long id;
    protected String numeroConta;
    protected Cliente titular;
    protected double saldo;
    protected String ultimaDescricaoTransacao;
    protected double ultimoValorTransacao;

    public ContaBancaria() {
    }

    public ContaBancaria(Long id, String numeroConta, Cliente titular, double saldo) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getUltimaDescricaoTransacao() {
        return ultimaDescricaoTransacao;
    }

    public double getUltimoValorTransacao() {
        return ultimoValorTransacao;
    }

    @Override
    public void depositar(double valor) {
        this.saldo += valor;
        registrarTransacao("DEPOSITO", valor);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0 || valor > saldo) {
            return false;
        }
        this.saldo -= valor;
        registrarTransacao("SAQUE", valor);
        return true;
    }

    protected void registrarTransacao(String tipo, double valor) {
        this.ultimaDescricaoTransacao = tipo;
        this.ultimoValorTransacao = valor;
    }

    @Override
    public void exibirSaldo() {
        }

   
    public abstract List<String[]> gerarExtrato();
}