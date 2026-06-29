package banco.ui;

import banco.dao.ContaCorrenteDAO;
import banco.dao.ContaPoupancaDAO;
import banco.model.ContaBancaria;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;
import banco.service.BancoService;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.logging.Logger;

public class TelaOperacoes extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(TelaOperacoes.class.getName());

    private javax.swing.JPanel FundoTela;
    private javax.swing.JPanel BarraTopo;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel labelSubtitulo;
    private javax.swing.JTextField fieldBusca;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JLabel labelTitular;
    private javax.swing.JLabel labelTipoConta;
    private javax.swing.JLabel labelSaldoAtual;
    private javax.swing.JLabel labelLimiteInfo;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JPanel panelDeposito;
    private javax.swing.JPanel panelSaque;
    private javax.swing.JPanel panelTransferencia;
    private javax.swing.JFormattedTextField fieldValorDeposito;
    private javax.swing.JButton botaoDepositar;
    private javax.swing.JLabel labelMsgDeposito;
    private javax.swing.JFormattedTextField fieldValorSaque;
    private javax.swing.JButton botaoSacar;
    private javax.swing.JLabel labelMsgSaque;
    private javax.swing.JLabel labelLimiteDisponivel;
    private javax.swing.JTextField fieldContaDestino;
    private javax.swing.JFormattedTextField fieldValorTransferencia;
    private javax.swing.JButton botaoTransferir;
    private javax.swing.JLabel labelMsgTransferencia;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JLabel labelMsgBusca;

    private ContaBancaria contaAtual;
    private final BancoService bancoService = new BancoService();

    public TelaOperacoes() {
        initComponents();
    }

    private void initComponents() {
        FundoTela = new javax.swing.JPanel();
        BarraTopo = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        labelSubtitulo = new javax.swing.JLabel();
        fieldBusca = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        labelTitular = new javax.swing.JLabel();
        labelTipoConta = new javax.swing.JLabel();
        labelSaldoAtual = new javax.swing.JLabel();
        labelLimiteInfo = new javax.swing.JLabel();
        labelMsgBusca = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
        botaoVoltar = new javax.swing.JButton();

        NumberFormat fmt = NumberFormat.getNumberInstance();
        fmt.setMinimumFractionDigits(2);
        fmt.setMaximumFractionDigits(2);

        panelDeposito = new javax.swing.JPanel(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelDeposito.setBackground(new java.awt.Color(255, 255, 255));
        fieldValorDeposito = new javax.swing.JFormattedTextField(fmt);
        fieldValorDeposito.setValue(0.00);
        botaoDepositar = new javax.swing.JButton();
        labelMsgDeposito = new javax.swing.JLabel();

        panelSaque = new javax.swing.JPanel(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelSaque.setBackground(new java.awt.Color(255, 255, 255));
        fieldValorSaque = new javax.swing.JFormattedTextField(fmt);
        fieldValorSaque.setValue(0.00);
        botaoSacar = new javax.swing.JButton();
        labelMsgSaque = new javax.swing.JLabel();
        labelLimiteDisponivel = new javax.swing.JLabel();

        panelTransferencia = new javax.swing.JPanel(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelTransferencia.setBackground(new java.awt.Color(255, 255, 255));
        fieldContaDestino = new javax.swing.JTextField();
        fieldValorTransferencia = new javax.swing.JFormattedTextField(fmt);
        fieldValorTransferencia.setValue(0.00);
        botaoTransferir = new javax.swing.JButton();
        labelMsgTransferencia = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(840, 620));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FundoTela.setBackground(new java.awt.Color(255, 255, 255));
        FundoTela.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BarraTopo.setBackground(new java.awt.Color(150, 36, 84));
        BarraTopo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Segoe UI Black", 1, 26));
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setText("SusuBank");
        BarraTopo.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 200, 35));

        labelSubtitulo.setFont(new java.awt.Font("Segoe UI", 1, 14));
        labelSubtitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelSubtitulo.setText("Operações Financeiras");
        BarraTopo.add(labelSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 58, 400, 20));

        FundoTela.add(BarraTopo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 100));

        JLabel labelBusca = new JLabel("Número da Conta:");
        labelBusca.setFont(new java.awt.Font("Segoe UI", 1, 13));
        labelBusca.setForeground(new java.awt.Color(150, 36, 84));
        FundoTela.add(labelBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 118, 180, 25));

        fieldBusca.setFont(new java.awt.Font("Segoe UI", 0, 13));
        fieldBusca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        FundoTela.add(fieldBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 146, 560, 32));

        botaoBuscar.setBackground(new java.awt.Color(150, 36, 84));
        botaoBuscar.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoBuscar.setForeground(new java.awt.Color(255, 255, 255));
        botaoBuscar.setFocusPainted(false);
        botaoBuscar.setBorderPainted(false);
        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(this::botaoBuscarActionPerformed);
        FundoTela.add(botaoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 146, 165, 32));

        labelMsgBusca.setFont(new java.awt.Font("Segoe UI", 1, 11));
        labelMsgBusca.setForeground(new java.awt.Color(200, 0, 0));
        labelMsgBusca.setText("");
        FundoTela.add(labelMsgBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 182, 760, 20));

        labelTitular.setFont(new java.awt.Font("Segoe UI", 0, 12));
        labelTitular.setForeground(new java.awt.Color(80, 80, 80));
        labelTitular.setText("Titular: —");
        FundoTela.add(labelTitular, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 205, 370, 20));

        labelTipoConta.setFont(new java.awt.Font("Segoe UI", 0, 12));
        labelTipoConta.setForeground(new java.awt.Color(80, 80, 80));
        labelTipoConta.setText("Tipo: —");
        FundoTela.add(labelTipoConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 205, 200, 20));

        labelSaldoAtual.setFont(new java.awt.Font("Segoe UI", 1, 13));
        labelSaldoAtual.setForeground(new java.awt.Color(150, 36, 84));
        labelSaldoAtual.setText("Saldo: —");
        FundoTela.add(labelSaldoAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 205, 180, 20));

        labelLimiteInfo.setFont(new java.awt.Font("Segoe UI", 0, 12));
        labelLimiteInfo.setForeground(new java.awt.Color(80, 80, 80));
        labelLimiteInfo.setText("");
        FundoTela.add(labelLimiteInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 228, 760, 20));

        configurarPanelDeposito();
        configurarPanelSaque();
        configurarPanelTransferencia();

        tabbedPane.setFont(new java.awt.Font("Segoe UI", 1, 13));
        tabbedPane.addTab("Depósito", panelDeposito);
        tabbedPane.addTab("Saque", panelSaque);
        tabbedPane.addTab("Transferência", panelTransferencia);
        FundoTela.add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 255, 760, 230));

        botaoVoltar.setBackground(new java.awt.Color(249, 228, 237));
        botaoVoltar.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoVoltar.setForeground(new java.awt.Color(150, 36, 84));
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setText("Voltar ao Menu");
        botaoVoltar.addActionListener(this::botaoVoltarActionPerformed);
        FundoTela.add(botaoVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 760, 45));

        getContentPane().add(FundoTela, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 580));

        setLocationRelativeTo(null);
    }

    private void configurarPanelDeposito() {
        JLabel lbl = new JLabel("Valor do Depósito (R$):");
        lbl.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lbl.setForeground(new java.awt.Color(150, 36, 84));
        panelDeposito.add(lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 25));

        fieldValorDeposito.setFont(new java.awt.Font("Segoe UI", 0, 13));
        fieldValorDeposito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        panelDeposito.add(fieldValorDeposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 48, 370, 35));

        botaoDepositar.setBackground(new java.awt.Color(150, 36, 84));
        botaoDepositar.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoDepositar.setForeground(new java.awt.Color(255, 255, 255));
        botaoDepositar.setFocusPainted(false);
        botaoDepositar.setBorderPainted(false);
        botaoDepositar.setText("Depositar");
        botaoDepositar.addActionListener(this::botaoDepositarActionPerformed);
        panelDeposito.add(botaoDepositar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 48, 340, 35));

        labelMsgDeposito.setFont(new java.awt.Font("Segoe UI", 1, 12));
        labelMsgDeposito.setForeground(new java.awt.Color(200, 0, 0));
        labelMsgDeposito.setText("");
        panelDeposito.add(labelMsgDeposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 95, 720, 22));
    }

    private void configurarPanelSaque() {
        JLabel lbl = new JLabel("Valor do Saque (R$):");
        lbl.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lbl.setForeground(new java.awt.Color(150, 36, 84));
        panelSaque.add(lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 250, 25));

        fieldValorSaque.setFont(new java.awt.Font("Segoe UI", 0, 13));
        fieldValorSaque.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        panelSaque.add(fieldValorSaque, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 48, 370, 35));

        botaoSacar.setBackground(new java.awt.Color(150, 36, 84));
        botaoSacar.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoSacar.setForeground(new java.awt.Color(255, 255, 255));
        botaoSacar.setFocusPainted(false);
        botaoSacar.setBorderPainted(false);
        botaoSacar.setText("Sacar");
        botaoSacar.addActionListener(this::botaoSacarActionPerformed);
        panelSaque.add(botaoSacar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 48, 340, 35));

        labelLimiteDisponivel.setFont(new java.awt.Font("Segoe UI", 0, 12));
        labelLimiteDisponivel.setForeground(new java.awt.Color(80, 80, 80));
        labelLimiteDisponivel.setText("");
        panelSaque.add(labelLimiteDisponivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 92, 500, 20));

        labelMsgSaque.setFont(new java.awt.Font("Segoe UI", 1, 12));
        labelMsgSaque.setForeground(new java.awt.Color(200, 0, 0));
        labelMsgSaque.setText("");
        panelSaque.add(labelMsgSaque, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 115, 720, 22));
    }

    private void configurarPanelTransferencia() {
        JLabel lblDestino = new JLabel("Conta de Destino:");
        lblDestino.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblDestino.setForeground(new java.awt.Color(150, 36, 84));
        panelTransferencia.add(lblDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 200, 25));

        fieldContaDestino.setFont(new java.awt.Font("Segoe UI", 0, 13));
        fieldContaDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        panelTransferencia.add(fieldContaDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 48, 370, 35));

        JLabel lblValor = new JLabel("Valor da Transferência (R$):");
        lblValor.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblValor.setForeground(new java.awt.Color(150, 36, 84));
        panelTransferencia.add(lblValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 250, 25));

        fieldValorTransferencia.setFont(new java.awt.Font("Segoe UI", 0, 13));
        fieldValorTransferencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        panelTransferencia.add(fieldValorTransferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 48, 340, 35));

        botaoTransferir.setBackground(new java.awt.Color(150, 36, 84));
        botaoTransferir.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoTransferir.setForeground(new java.awt.Color(255, 255, 255));
        botaoTransferir.setFocusPainted(false);
        botaoTransferir.setBorderPainted(false);
        botaoTransferir.setText("Transferir");
        botaoTransferir.addActionListener(this::botaoTransferirActionPerformed);
        panelTransferencia.add(botaoTransferir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 720, 35));

        labelMsgTransferencia.setFont(new java.awt.Font("Segoe UI", 1, 12));
        labelMsgTransferencia.setForeground(new java.awt.Color(200, 0, 0));
        labelMsgTransferencia.setText("");
        panelTransferencia.add(labelMsgTransferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 145, 720, 22));
    }

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        String numero = fieldBusca.getText().trim();
        labelMsgBusca.setForeground(new java.awt.Color(200, 0, 0));

        if (numero.isEmpty()) {
            labelMsgBusca.setText("Informe o número da conta.");
            return;
        }

        try {
            contaAtual = bancoService.buscarConta(numero);
            if (contaAtual == null) {
                labelMsgBusca.setText("Conta não encontrada.");
                limparInfoConta();
                return;
            }

            labelMsgBusca.setText("");
            labelTitular.setText("Titular: " + contaAtual.getTitular().getNome());
            labelSaldoAtual.setText(String.format("Saldo: R$ %.2f", contaAtual.getSaldo()));

            if (contaAtual instanceof ContaCorrente cc) {
                labelTipoConta.setText("Tipo: Conta Corrente");
                labelLimiteInfo.setText(String.format("Limite Cheque Especial: R$ %.2f", cc.getLimiteChequEspecial()));
            } else if (contaAtual instanceof ContaPoupanca cp) {
                labelTipoConta.setText("Tipo: Conta Poupança");
                labelLimiteInfo.setText(String.format("Taxa de Rendimento: %.2f%%", cp.getTaxaRendimentoMensal()));
            }
        } catch (Exception e) {
            labelMsgBusca.setText("Erro ao buscar conta: " + e.getMessage());
        }
    }

    private void limparInfoConta() {
        labelTitular.setText("Titular: —");
        labelTipoConta.setText("Tipo: —");
        labelSaldoAtual.setText("Saldo: —");
        labelLimiteInfo.setText("");
    }

    private void atualizarSaldoExibido() {
        if (contaAtual != null) {
            labelSaldoAtual.setText(String.format("Saldo: R$ %.2f", contaAtual.getSaldo()));
            if (contaAtual instanceof ContaCorrente cc) {
                labelLimiteInfo.setText(String.format("Limite Cheque Especial: R$ %.2f", cc.getLimiteChequEspecial()));
            }
        }
    }

    private void botaoDepositarActionPerformed(java.awt.event.ActionEvent evt) {
        labelMsgDeposito.setForeground(new java.awt.Color(200, 0, 0));

        if (contaAtual == null) {
            labelMsgDeposito.setText("Busque uma conta primeiro.");
            return;
        }
        try {
            double valor = ((Number) fieldValorDeposito.getValue()).doubleValue();
            if (valor <= 0) {
                labelMsgDeposito.setText("O valor do depósito deve ser maior que zero.");
                return;
            }
            contaAtual.depositar(valor);
            atualizarSaldoExibido();
            labelMsgDeposito.setForeground(new java.awt.Color(0, 130, 0));
            labelMsgDeposito.setText(String.format("Depósito de R$ %.2f realizado com sucesso!", valor));
            fieldValorDeposito.setValue(0.00);
        } catch (Exception e) {
            labelMsgDeposito.setText("Erro ao depositar: " + e.getMessage());
        }
    }

    private void botaoSacarActionPerformed(java.awt.event.ActionEvent evt) {
        labelMsgSaque.setForeground(new java.awt.Color(200, 0, 0));

        if (contaAtual == null) {
            labelMsgSaque.setText("Busque uma conta primeiro.");
            return;
        }
        try {
            double valor = ((Number) fieldValorSaque.getValue()).doubleValue();
            if (valor <= 0) {
                labelMsgSaque.setText("O valor do saque deve ser maior que zero.");
                return;
            }

            boolean sucesso = contaAtual.sacar(valor);
            if (sucesso) {
                atualizarSaldoExibido();
                labelMsgSaque.setForeground(new java.awt.Color(0, 130, 0));
                labelMsgSaque.setText(String.format("Saque de R$ %.2f realizado com sucesso!", valor));
                fieldValorSaque.setValue(0.00);
                if (contaAtual instanceof ContaCorrente cc) {
                    labelLimiteDisponivel.setText(String.format("Limite disponível: R$ %.2f", cc.getSaldo() + cc.getLimiteChequEspecial()));
                }
            } else {
                labelMsgSaque.setText("Saldo insuficiente. Operação bloqueada.");
            }
        } catch (Exception e) {
            labelMsgSaque.setText("Erro ao sacar: " + e.getMessage());
        }
    }

    private void botaoTransferirActionPerformed(java.awt.event.ActionEvent evt) {
        labelMsgTransferencia.setForeground(new java.awt.Color(200, 0, 0));

        if (contaAtual == null) {
            labelMsgTransferencia.setText("Busque uma conta de origem primeiro.");
            return;
        }
        String destino = fieldContaDestino.getText().trim();
        if (destino.isEmpty()) {
            labelMsgTransferencia.setText("Informe a conta de destino.");
            return;
        }
        try {
            double valor = ((Number) fieldValorTransferencia.getValue()).doubleValue();
            if (valor <= 0) {
                labelMsgTransferencia.setText("O valor da transferência deve ser maior que zero.");
                return;
            }
            ContaBancaria contaDestino = bancoService.buscarConta(destino);
            if (contaDestino == null) {
                labelMsgTransferencia.setText("Conta de destino não encontrada.");
                return;
            }
            if (contaDestino.getNumeroConta().equals(contaAtual.getNumeroConta())) {
                labelMsgTransferencia.setText("Conta de destino não pode ser a mesma conta de origem.");
                return;
            }

            boolean sucesso = contaAtual.sacar(valor);
            if (sucesso) {
                contaDestino.depositar(valor);
                atualizarSaldoExibido();
                labelMsgTransferencia.setForeground(new java.awt.Color(0, 130, 0));
                labelMsgTransferencia.setText(String.format("Transferência de R$ %.2f para conta %s realizada com sucesso!", valor, destino));
                fieldValorTransferencia.setValue(0.00);
                fieldContaDestino.setText("");
            } else {
                labelMsgTransferencia.setText("Saldo insuficiente para realizar a transferência.");
            }
        } catch (Exception e) {
            labelMsgTransferencia.setText("Erro na transferência: " + e.getMessage());
        }
    }

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaMenuPrincipal().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaOperacoes().setVisible(true));
    }
}