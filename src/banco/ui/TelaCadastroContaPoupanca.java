package banco.ui;

import banco.dao.ClienteDAO;
import banco.dao.ContaPoupancaDAO;
import banco.model.Cliente;
import banco.model.ContaPoupanca;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.logging.Logger;

public class TelaCadastroContaPoupanca extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(TelaCadastroContaPoupanca.class.getName());

    private javax.swing.JPanel FundoTela;
    private javax.swing.JPanel BarraTopo;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel labelSubtitulo;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JComboBox<String> comboCliente;
    private javax.swing.JLabel labelNumeroConta;
    private javax.swing.JTextField fieldNumeroConta;
    private javax.swing.JLabel labelSaldo;
    private javax.swing.JFormattedTextField fieldSaldo;
    private javax.swing.JLabel labelTaxa;
    private javax.swing.JFormattedTextField fieldTaxa;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JLabel labelMensagem;

    private List<Cliente> clientes;
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
    private final banco.service.BancoService bancoService = new banco.service.BancoService();

    public TelaCadastroContaPoupanca() {
        initComponents();
        carregarClientes();
    }

    private void initComponents() {
        FundoTela = new javax.swing.JPanel();
        BarraTopo = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        labelSubtitulo = new javax.swing.JLabel();
        labelCliente = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox<>();
        labelNumeroConta = new javax.swing.JLabel();
        fieldNumeroConta = new javax.swing.JTextField();
        NumberFormat formatoMonetario = NumberFormat.getNumberInstance();
        formatoMonetario.setMinimumFractionDigits(2);
        formatoMonetario.setMaximumFractionDigits(2);
        fieldSaldo = new javax.swing.JFormattedTextField(formatoMonetario);
        labelSaldo = new javax.swing.JLabel();
        NumberFormat formatoTaxa = NumberFormat.getNumberInstance();
        formatoTaxa.setMinimumFractionDigits(2);
        formatoTaxa.setMaximumFractionDigits(4);
        fieldTaxa = new javax.swing.JFormattedTextField(formatoTaxa);
        labelTaxa = new javax.swing.JLabel();
        botaoCadastrar = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        labelMensagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(840, 560));
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
        labelSubtitulo.setText("Cadastrar Conta Poupança");
        BarraTopo.add(labelSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 58, 400, 20));

        FundoTela.add(BarraTopo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 100));

        labelCliente.setFont(new java.awt.Font("Segoe UI", 1, 13));
        labelCliente.setForeground(new java.awt.Color(150, 36, 84));
        labelCliente.setText("Cliente Titular:");
        FundoTela.add(labelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 200, 25));

        comboCliente.setFont(new java.awt.Font("Segoe UI", 0, 13));
        FundoTela.add(comboCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 158, 760, 35));

        labelNumeroConta.setFont(new java.awt.Font("Segoe UI", 1, 13));
        labelNumeroConta.setForeground(new java.awt.Color(150, 36, 84));
        labelNumeroConta.setText("Número da Conta:");
        FundoTela.add(labelNumeroConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 200, 25));

        fieldNumeroConta.setFont(new java.awt.Font("Segoe UI", 0, 13));
        fieldNumeroConta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        FundoTela.add(fieldNumeroConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 238, 370, 35));

        labelSaldo.setFont(new java.awt.Font("Segoe UI", 1, 13));
        labelSaldo.setForeground(new java.awt.Color(150, 36, 84));
        labelSaldo.setText("Saldo Inicial (R$):");
        FundoTela.add(labelSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 200, 25));

        fieldSaldo.setFont(new java.awt.Font("Segoe UI", 0, 13));
        fieldSaldo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        fieldSaldo.setValue(0.00);
        FundoTela.add(fieldSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 238, 370, 35));

        labelTaxa.setFont(new java.awt.Font("Segoe UI", 1, 13));
        labelTaxa.setForeground(new java.awt.Color(150, 36, 84));
        labelTaxa.setText("Taxa de Rendimento Mensal (ex: 0.5 para 0,5%):");
        FundoTela.add(labelTaxa, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 420, 25));

        fieldTaxa.setFont(new java.awt.Font("Segoe UI", 0, 13));
        fieldTaxa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        fieldTaxa.setValue(0.50);
        FundoTela.add(fieldTaxa, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 318, 370, 35));

        labelMensagem.setFont(new java.awt.Font("Segoe UI", 1, 12));
        labelMensagem.setForeground(new java.awt.Color(200, 0, 0));
        labelMensagem.setText("");
        FundoTela.add(labelMensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 365, 760, 25));

        botaoCadastrar.setBackground(new java.awt.Color(150, 36, 84));
        botaoCadastrar.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        botaoCadastrar.setFocusPainted(false);
        botaoCadastrar.setBorderPainted(false);
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(this::botaoCadastrarActionPerformed);
        FundoTela.add(botaoCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 370, 55));

        botaoCancelar.setBackground(new java.awt.Color(249, 228, 237));
        botaoCancelar.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoCancelar.setForeground(new java.awt.Color(150, 36, 84));
        botaoCancelar.setFocusPainted(false);
        botaoCancelar.setBorderPainted(false);
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(this::botaoCancelarActionPerformed);
        FundoTela.add(botaoCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, 370, 55));

        getContentPane().add(FundoTela, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 520));

        setLocationRelativeTo(null);
    }

    private void carregarClientes() {
        try {
            clientes = clienteDAO.buscarTodos();
            comboCliente.removeAllItems();
            for (Cliente c : clientes) {
                comboCliente.addItem(c.getNome() + " - CPF: " + c.getCpf());
            }
        } catch (Exception e) {
            labelMensagem.setText("Erro ao carregar clientes.");
        }
    }

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        labelMensagem.setForeground(new java.awt.Color(200, 0, 0));

        if (comboCliente.getSelectedIndex() < 0) {
            labelMensagem.setText("Selecione um cliente titular.");
            return;
        }
        String numero = fieldNumeroConta.getText().trim();
        if (numero.isEmpty()) {
            labelMensagem.setText("Informe o número da conta.");
            return;
        }

        try {
            if (bancoService.buscarConta(numero) != null) {
                labelMensagem.setText("Já existe uma conta com este número.");
                return;
            }

            double saldo = ((Number) fieldSaldo.getValue()).doubleValue();
            double taxa = ((Number) fieldTaxa.getValue()).doubleValue();

            if (saldo < 0) {
                labelMensagem.setText("Saldo não pode ser negativo.");
                return;
            }
            if (taxa <= 0) {
                labelMensagem.setText("Taxa de rendimento deve ser maior que zero.");
                return;
            }

            Cliente clienteSelecionado = clientes.get(comboCliente.getSelectedIndex());
            ContaPoupanca conta = new ContaPoupanca(null, numero, clienteSelecionado, saldo, taxa);
            contaPoupancaDAO.salvar(conta);

            labelMensagem.setForeground(new java.awt.Color(0, 130, 0));
            labelMensagem.setText("Conta Poupança cadastrada com sucesso!");
            fieldNumeroConta.setText("");
            fieldSaldo.setValue(0.00);
            fieldTaxa.setValue(0.50);
            comboCliente.setSelectedIndex(0);
        } catch (Exception e) {
            labelMensagem.setText("Erro ao cadastrar conta: " + e.getMessage());
        }
    }

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaMenuPrincipal().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaCadastroContaPoupanca().setVisible(true));
    }
}