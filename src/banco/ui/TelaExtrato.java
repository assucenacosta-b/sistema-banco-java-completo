package banco.ui;

import banco.dao.ContaCorrenteDAO;
import banco.dao.ContaPoupancaDAO;
import banco.model.ContaBancaria;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;
import banco.service.BancoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.logging.Logger;

public class TelaExtrato extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(TelaExtrato.class.getName());

    private javax.swing.JPanel FundoTela;
    private javax.swing.JPanel BarraTopo;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel labelSubtitulo;
    private javax.swing.JTextField fieldBusca;
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JLabel labelMsgBusca;
    private javax.swing.JLabel labelTitular;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelSaldo;
    private javax.swing.JLabel labelInfoExtra1;
    private javax.swing.JLabel labelInfoExtra2;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tabelaTransacoes;
    private javax.swing.JButton botaoRendimento;
    private javax.swing.JButton botaoVoltar;

    private ContaBancaria contaAtual;
    private final BancoService bancoService = new BancoService();
    private banco.model.Usuario usuarioLogado;

    public TelaExtrato() {
        initComponents();
    }

    public TelaExtrato(banco.model.Usuario usuarioLogado) {
        this();
        this.usuarioLogado = usuarioLogado;
    }

    private void initComponents() {
        FundoTela = new javax.swing.JPanel();
        BarraTopo = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        labelSubtitulo = new javax.swing.JLabel();
        fieldBusca = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        labelMsgBusca = new javax.swing.JLabel();
        labelTitular = new javax.swing.JLabel();
        labelNumero = new javax.swing.JLabel();
        labelTipo = new javax.swing.JLabel();
        labelSaldo = new javax.swing.JLabel();
        labelInfoExtra1 = new javax.swing.JLabel();
        labelInfoExtra2 = new javax.swing.JLabel();
        tabelaTransacoes = new javax.swing.JTable();
        scrollPane = new javax.swing.JScrollPane(tabelaTransacoes);
        botaoRendimento = new javax.swing.JButton();
        botaoVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(840, 680));
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
        labelSubtitulo.setText("Extrato da Conta");
        BarraTopo.add(labelSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 58, 400, 20));

        FundoTela.add(BarraTopo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 100));

        JLabel lblBusca = new JLabel("Número da Conta:");
        lblBusca.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblBusca.setForeground(new java.awt.Color(150, 36, 84));
        FundoTela.add(lblBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 118, 180, 25));

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
        labelTitular.setForeground(new java.awt.Color(60, 60, 60));
        labelTitular.setText("Titular: —");
        FundoTela.add(labelTitular, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 205, 300, 20));

        labelNumero.setFont(new java.awt.Font("Segoe UI", 0, 12));
        labelNumero.setForeground(new java.awt.Color(60, 60, 60));
        labelNumero.setText("Número: —");
        FundoTela.add(labelNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 205, 200, 20));

        labelTipo.setFont(new java.awt.Font("Segoe UI", 0, 12));
        labelTipo.setForeground(new java.awt.Color(60, 60, 60));
        labelTipo.setText("Tipo: —");
        FundoTela.add(labelTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 205, 150, 20));

        labelSaldo.setFont(new java.awt.Font("Segoe UI", 1, 13));
        labelSaldo.setForeground(new java.awt.Color(150, 36, 84));
        labelSaldo.setText("Saldo: —");
        FundoTela.add(labelSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 205, 120, 20));

        labelInfoExtra1.setFont(new java.awt.Font("Segoe UI", 0, 12));
        labelInfoExtra1.setForeground(new java.awt.Color(60, 60, 60));
        labelInfoExtra1.setText("");
        FundoTela.add(labelInfoExtra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 228, 400, 20));

        labelInfoExtra2.setFont(new java.awt.Font("Segoe UI", 0, 12));
        labelInfoExtra2.setForeground(new java.awt.Color(60, 60, 60));
        labelInfoExtra2.setText("");
        FundoTela.add(labelInfoExtra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 228, 350, 20));

        tabelaTransacoes.setFont(new java.awt.Font("Segoe UI", 0, 12));
        tabelaTransacoes.setRowHeight(24);
        tabelaTransacoes.setGridColor(new java.awt.Color(230, 210, 220));
        tabelaTransacoes.setSelectionBackground(new java.awt.Color(249, 228, 237));
        tabelaTransacoes.setSelectionForeground(new java.awt.Color(150, 36, 84));
        tabelaTransacoes.getTableHeader().setFont(new java.awt.Font("Segoe UI", 1, 12));
        tabelaTransacoes.getTableHeader().setBackground(new java.awt.Color(150, 36, 84));
        tabelaTransacoes.getTableHeader().setForeground(new java.awt.Color(255, 255, 255));
        tabelaTransacoes.setDefaultEditor(Object.class, null);

        DefaultTableModel modeloVazio = new DefaultTableModel(
                new Object[]{"Data/Hora", "Tipo", "Descrição", "Valor (R$)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaTransacoes.setModel(modeloVazio);

        scrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        FundoTela.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 255, 760, 290));

        botaoRendimento.setBackground(new java.awt.Color(150, 36, 84));
        botaoRendimento.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoRendimento.setForeground(new java.awt.Color(255, 255, 255));
        botaoRendimento.setFocusPainted(false);
        botaoRendimento.setBorderPainted(false);
        botaoRendimento.setText("Aplicar Rendimento");
        botaoRendimento.setVisible(false);
        botaoRendimento.addActionListener(this::botaoRendimentoActionPerformed);
        FundoTela.add(botaoRendimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 558, 370, 45));

        botaoVoltar.setBackground(new java.awt.Color(249, 228, 237));
        botaoVoltar.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoVoltar.setForeground(new java.awt.Color(150, 36, 84));
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setText("Voltar ao Menu");
        botaoVoltar.addActionListener(this::botaoVoltarActionPerformed);
        FundoTela.add(botaoVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 558, 370, 45));

        getContentPane().add(FundoTela, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 640));

        setLocationRelativeTo(null);
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
                limparDados();
                return;
            }

            labelMsgBusca.setText("");
            labelTitular.setText("Titular: " + contaAtual.getTitular().getNome());
            labelNumero.setText("Número: " + contaAtual.getNumeroConta());
            labelSaldo.setText(String.format("Saldo: R$ %.2f", contaAtual.getSaldo()));
            botaoRendimento.setVisible(false);

            if (contaAtual instanceof ContaCorrente cc) {
                labelTipo.setText("Tipo: Conta Corrente");
                labelInfoExtra1.setText(String.format("Limite Cheque Especial: R$ %.2f", cc.getLimiteChequeEspecial()));
                labelInfoExtra2.setText(String.format("Limite Disponível: R$ %.2f", cc.getSaldo() + cc.getLimiteChequeEspecial()));
            } else if (contaAtual instanceof ContaPoupanca cp) {
                labelTipo.setText("Tipo: Conta Poupança");
                labelInfoExtra1.setText(String.format("Taxa de Rendimento: %.2f%%", cp.getTaxaRendimentoMensal()));
                labelInfoExtra2.setText(String.format("Rendimento Estimado Próximo Mês: R$ %.2f", cp.calcularRendimento()));
                botaoRendimento.setVisible(true);
            }

            carregarTransacoes();
        } catch (Exception e) {
            labelMsgBusca.setText("Erro ao buscar conta: " + e.getMessage());
        }
    }

    private void carregarTransacoes() {
        try {
            List<String[]> transacoes = contaAtual.gerarExtrato();
            DefaultTableModel modelo = new DefaultTableModel(
                    new Object[]{"Data/Hora", "Tipo", "Descrição", "Valor (R$)"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            for (String[] linha : transacoes) {
                modelo.addRow(linha);
            }
            tabelaTransacoes.setModel(modelo);
        } catch (Exception e) {
            labelMsgBusca.setForeground(new java.awt.Color(200, 0, 0));
            labelMsgBusca.setText("Erro ao carregar extrato: " + e.getMessage());
        }
    }

    private void limparDados() {
        labelTitular.setText("Titular: —");
        labelNumero.setText("Número: —");
        labelTipo.setText("Tipo: —");
        labelSaldo.setText("Saldo: —");
        labelInfoExtra1.setText("");
        labelInfoExtra2.setText("");
        botaoRendimento.setVisible(false);
        tabelaTransacoes.setModel(new DefaultTableModel(
                new Object[]{"Data/Hora", "Tipo", "Descrição", "Valor (R$)"}, 0));
    }

    private void botaoRendimentoActionPerformed(java.awt.event.ActionEvent evt) {
        if (contaAtual instanceof ContaPoupanca cp) {
            try {
                bancoService.aplicarRendimento(cp);
                labelSaldo.setText(String.format("Saldo: R$ %.2f", cp.getSaldo()));
                labelInfoExtra2.setText(String.format("Rendimento Estimado Próximo Mês: R$ %.2f", cp.calcularRendimento()));
                carregarTransacoes();
                labelMsgBusca.setForeground(new java.awt.Color(0, 130, 0));
                labelMsgBusca.setText("Rendimento aplicado com sucesso!");
            } catch (Exception e) {
                labelMsgBusca.setForeground(new java.awt.Color(200, 0, 0));
                labelMsgBusca.setText("Erro ao aplicar rendimento: " + e.getMessage());
            }
        }
    }

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaMenuPrincipal(usuarioLogado).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaExtrato().setVisible(true));
    }
}