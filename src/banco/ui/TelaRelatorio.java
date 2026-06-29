package banco.ui;

import banco.model.ContaBancaria;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;
import banco.service.BancoService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.logging.Logger;

public class TelaRelatorio extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(TelaRelatorio.class.getName());

    private javax.swing.JPanel FundoTela;
    private javax.swing.JPanel BarraTopo;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel labelSubtitulo;
    private javax.swing.JLabel labelTotalCorrente;
    private javax.swing.JLabel labelTotalPoupanca;
    private javax.swing.JLabel labelPatrimonio;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tabelaContas;
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JLabel labelMensagem;

    private final BancoService bancoService = new BancoService();

    public TelaRelatorio() {
        initComponents();
        carregarDados();
    }

    private void initComponents() {
        FundoTela = new javax.swing.JPanel();
        BarraTopo = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        labelSubtitulo = new javax.swing.JLabel();
        labelTotalCorrente = new javax.swing.JLabel();
        labelTotalPoupanca = new javax.swing.JLabel();
        labelPatrimonio = new javax.swing.JLabel();
        tabelaContas = new javax.swing.JTable();
        scrollPane = new javax.swing.JScrollPane(tabelaContas);
        botaoAtualizar = new javax.swing.JButton();
        botaoVoltar = new javax.swing.JButton();
        labelMensagem = new javax.swing.JLabel();

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
        labelSubtitulo.setText("Relatório Geral do Banco");
        BarraTopo.add(labelSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 58, 400, 20));

        FundoTela.add(BarraTopo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 100));

        javax.swing.JPanel painelResumo = new javax.swing.JPanel();
        painelResumo.setBackground(new java.awt.Color(249, 228, 237));
        painelResumo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTotalCorrente.setFont(new java.awt.Font("Segoe UI", 1, 13));
        labelTotalCorrente.setForeground(new java.awt.Color(150, 36, 84));
        labelTotalCorrente.setText("Contas Correntes: —");
        painelResumo.add(labelTotalCorrente, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, 240, 25));

        labelTotalPoupanca.setFont(new java.awt.Font("Segoe UI", 1, 13));
        labelTotalPoupanca.setForeground(new java.awt.Color(150, 36, 84));
        labelTotalPoupanca.setText("Contas Poupança: —");
        painelResumo.add(labelTotalPoupanca, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 12, 240, 25));

        labelPatrimonio.setFont(new java.awt.Font("Segoe UI Black", 1, 14));
        labelPatrimonio.setForeground(new java.awt.Color(150, 36, 84));
        labelPatrimonio.setText("Patrimônio Total: —");
        painelResumo.add(labelPatrimonio, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 12, 280, 25));

        FundoTela.add(painelResumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 115, 760, 50));

        tabelaContas.setFont(new java.awt.Font("Segoe UI", 0, 12));
        tabelaContas.setRowHeight(24);
        tabelaContas.setGridColor(new java.awt.Color(230, 210, 220));
        tabelaContas.setSelectionBackground(new java.awt.Color(249, 228, 237));
        tabelaContas.setSelectionForeground(new java.awt.Color(150, 36, 84));
        tabelaContas.getTableHeader().setFont(new java.awt.Font("Segoe UI", 1, 12));
        tabelaContas.getTableHeader().setBackground(new java.awt.Color(150, 36, 84));
        tabelaContas.getTableHeader().setForeground(new java.awt.Color(255, 255, 255));
        tabelaContas.setDefaultEditor(Object.class, null);

        DefaultTableModel modeloVazio = new DefaultTableModel(
                new Object[]{"Número", "Titular", "Tipo", "Saldo (R$)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaContas.setModel(modeloVazio);

        scrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        FundoTela.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 178, 760, 360));

        labelMensagem.setFont(new java.awt.Font("Segoe UI", 0, 11));
        labelMensagem.setForeground(new java.awt.Color(120, 36, 84));
        labelMensagem.setText("★ Maior saldo destacado em verde | ▼ Menor saldo destacado em vermelho");
        FundoTela.add(labelMensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 545, 760, 20));

        botaoAtualizar.setBackground(new java.awt.Color(150, 36, 84));
        botaoAtualizar.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        botaoAtualizar.setFocusPainted(false);
        botaoAtualizar.setBorderPainted(false);
        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.addActionListener(this::botaoAtualizarActionPerformed);
        FundoTela.add(botaoAtualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 572, 370, 48));

        botaoVoltar.setBackground(new java.awt.Color(249, 228, 237));
        botaoVoltar.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoVoltar.setForeground(new java.awt.Color(150, 36, 84));
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setText("Voltar ao Menu");
        botaoVoltar.addActionListener(this::botaoVoltarActionPerformed);
        FundoTela.add(botaoVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 572, 370, 48));

        getContentPane().add(FundoTela, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 640));

        setLocationRelativeTo(null);
    }

    private void carregarDados() {
        try {
            List<ContaBancaria> todasContas = bancoService.exibirRelatorioGeral();

            long totalCorrente = todasContas.stream().filter(c -> c instanceof ContaCorrente).count();
            long totalPoupanca = todasContas.stream().filter(c -> c instanceof ContaPoupanca).count();
            double patrimonio = bancoService.calcularPatrimonioTotal();

            labelTotalCorrente.setText("Contas Correntes: " + totalCorrente);
            labelTotalPoupanca.setText("Contas Poupança: " + totalPoupanca);
            labelPatrimonio.setText(String.format("Patrimônio Total: R$ %.2f", patrimonio));

            DefaultTableModel modelo = new DefaultTableModel(
                    new Object[]{"Número", "Titular", "Tipo", "Saldo (R$)"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            double maiorSaldo = Double.MIN_VALUE;
            double menorSaldo = Double.MAX_VALUE;

            for (ContaBancaria c : todasContas) {
                if (c.getSaldo() > maiorSaldo) maiorSaldo = c.getSaldo();
                if (c.getSaldo() < menorSaldo) menorSaldo = c.getSaldo();
            }

            final double saldoMaior = maiorSaldo;
            final double saldoMenor = menorSaldo;

            for (ContaBancaria c : todasContas) {
                String tipo = (c instanceof ContaCorrente) ? "Corrente" : "Poupança";
                modelo.addRow(new Object[]{
                        c.getNumeroConta(),
                        c.getTitular().getNome(),
                        tipo,
                        String.format("%.2f", c.getSaldo())
                });
            }

            tabelaContas.setModel(modelo);

            tabelaContas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    String saldoStr = (String) table.getModel().getValueAt(row, 3);
                    double saldoLinha;
                    try {
                        saldoLinha = Double.parseDouble(saldoStr.replace(",", "."));
                    } catch (NumberFormatException ex) {
                        saldoLinha = -1;
                    }

                    if (!isSelected) {
                        if (todasContas.size() > 1 && saldoLinha == saldoMaior) {
                            comp.setBackground(new Color(200, 255, 200));
                            comp.setForeground(new Color(0, 100, 0));
                            ((JLabel) comp).setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
                        } else if (todasContas.size() > 1 && saldoLinha == saldoMenor) {
                            comp.setBackground(new Color(255, 210, 210));
                            comp.setForeground(new Color(160, 0, 0));
                            ((JLabel) comp).setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
                        } else {
                            comp.setBackground(Color.WHITE);
                            comp.setForeground(Color.BLACK);
                            ((JLabel) comp).setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
                        }
                    }
                    return comp;
                }
            });

        } catch (Exception e) {
            labelPatrimonio.setText("Erro ao carregar relatório.");
        }
    }

    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {
        carregarDados();
    }

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaMenuPrincipal().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaRelatorio().setVisible(true));
    }
}