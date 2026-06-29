package banco.ui;

import banco.model.Usuario;

public class TelaMenuPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName());

    public TelaMenuPrincipal() {
        initComponents();
        Usuario usuario = banco.util.SessaoUsuario.getUsuarioLogado();
        if (usuario != null) {
            TituloBemVindo.setText("Bem-vindo, " + usuario.getNome());
            botaoGerenciar.setVisible(usuario.isAdmin());
        }
    }

    TelaMenuPrincipal(Usuario usuario) {
        initComponents();
        if (usuario != null) {
            banco.util.SessaoUsuario.login(usuario);
            TituloBemVindo.setText("Bem-vindo, " + usuario.getNome());
            botaoGerenciar.setVisible(usuario.isAdmin());
        }
    }

    private void initComponents() {

        FundoTela = new javax.swing.JPanel();
        BarraTopo = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        TituloBemVindo = new javax.swing.JLabel();
        botaoSair = new javax.swing.JButton();
        botaoGerenciar = new javax.swing.JButton();
        botaoCliente = new javax.swing.JButton();
        BotaoCorrente = new javax.swing.JButton();
        botaoPoupanca = new javax.swing.JButton();
        botaoOperacoes = new javax.swing.JButton();
        botaoExtrato = new javax.swing.JButton();
        botaoRelatorio = new javax.swing.JButton();

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

        TituloBemVindo.setFont(new java.awt.Font("Segoe UI", 1, 14));
        TituloBemVindo.setForeground(new java.awt.Color(255, 255, 255));
        TituloBemVindo.setText("Bem-vindo, [Nome do Usuario]");
        BarraTopo.add(TituloBemVindo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 58, 400, 20));

        botaoSair.setBackground(new java.awt.Color(249, 228, 237));
        botaoSair.setFont(new java.awt.Font("Segoe UI Black", 0, 13));
        botaoSair.setForeground(new java.awt.Color(150, 36, 84));
        botaoSair.setFocusPainted(false);
        botaoSair.setBorderPainted(false);
        botaoSair.setText("Sair");
        botaoSair.addActionListener(this::botaoSairActionPerformed);
        BarraTopo.add(botaoSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 32, 80, 32));

        FundoTela.add(BarraTopo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 100));

        botaoGerenciar.setBackground(new java.awt.Color(249, 228, 237));
        botaoGerenciar.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoGerenciar.setForeground(new java.awt.Color(150, 36, 84));
        botaoGerenciar.setFocusPainted(false);
        botaoGerenciar.setText("Gerenciar Usuarios");
        botaoGerenciar.addActionListener(this::botaoGerenciarActionPerformed);
        FundoTela.add(botaoGerenciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 370, 70));

        botaoCliente.setBackground(new java.awt.Color(249, 228, 237));
        botaoCliente.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoCliente.setForeground(new java.awt.Color(150, 36, 84));
        botaoCliente.setFocusPainted(false);
        botaoCliente.setText("Cadastrar Cliente");
        botaoCliente.addActionListener(this::botaoClienteActionPerformed);
        FundoTela.add(botaoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 370, 70));

        BotaoCorrente.setBackground(new java.awt.Color(249, 228, 237));
        BotaoCorrente.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        BotaoCorrente.setForeground(new java.awt.Color(150, 36, 84));
        BotaoCorrente.setFocusPainted(false);
        BotaoCorrente.setText("Cadastrar Conta Corrente");
        BotaoCorrente.addActionListener(this::BotaoCorrenteActionPerformed);
        FundoTela.add(BotaoCorrente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 225, 370, 70));

        botaoPoupanca.setBackground(new java.awt.Color(249, 228, 237));
        botaoPoupanca.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoPoupanca.setForeground(new java.awt.Color(150, 36, 84));
        botaoPoupanca.setFocusPainted(false);
        botaoPoupanca.setText("Cadastrar Conta Poupanca");
        botaoPoupanca.addActionListener(this::botaoPoupancaActionPerformed);
        FundoTela.add(botaoPoupanca, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 225, 370, 70));

        botaoOperacoes.setBackground(new java.awt.Color(249, 228, 237));
        botaoOperacoes.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoOperacoes.setForeground(new java.awt.Color(150, 36, 84));
        botaoOperacoes.setFocusPainted(false);
        botaoOperacoes.setText("Operacoes");
        botaoOperacoes.addActionListener(this::botaoOperacoesActionPerformed);
        FundoTela.add(botaoOperacoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 370, 70));

        botaoExtrato.setBackground(new java.awt.Color(249, 228, 237));
        botaoExtrato.setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        botaoExtrato.setForeground(new java.awt.Color(150, 36, 84));
        botaoExtrato.setFocusPainted(false);
        botaoExtrato.setText("Extrato da Conta");
        botaoExtrato.addActionListener(this::botaoExtratoActionPerformed);
        FundoTela.add(botaoExtrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 310, 370, 70));

        botaoRelatorio.setBackground(new java.awt.Color(249, 228, 237));
        botaoRelatorio.setFont(new java.awt.Font("Segoe UI Black", 1, 14));
        botaoRelatorio.setForeground(new java.awt.Color(150, 36, 84));
        botaoRelatorio.setFocusPainted(false);
        botaoRelatorio.setText("Relatorio Geral do Banco");
        botaoRelatorio.addActionListener(this::botaoRelatorioActionPerformed);
        FundoTela.add(botaoRelatorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 760, 70));

        getContentPane().add(FundoTela, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 520));

        setLocationRelativeTo(null);
    }

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaLogin().setVisible(true);
        dispose();
    }

    private void botaoGerenciarActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaGerenciarUsuarios().setVisible(true);
        dispose();
    }

    private void botaoClienteActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaCadastroCliente().setVisible(true);
        dispose();
    }

    private void BotaoCorrenteActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaCadastroContaCorrente().setVisible(true);
        dispose();
    }

    private void botaoPoupancaActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaCadastroContaPoupanca().setVisible(true);
        dispose();
    }

    private void botaoOperacoesActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaOperacoes().setVisible(true);
        dispose();
    }

    private void botaoExtratoActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaExtrato().setVisible(true);
        dispose();
    }

    private void botaoRelatorioActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaRelatorio().setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaMenuPrincipal().setVisible(true));
    }

    private javax.swing.JPanel FundoTela;
    private javax.swing.JPanel BarraTopo;
    private javax.swing.JButton BotaoCorrente;
    private javax.swing.JLabel TituloBemVindo;
    private javax.swing.JButton botaoCliente;
    private javax.swing.JButton botaoExtrato;
    private javax.swing.JButton botaoGerenciar;
    private javax.swing.JButton botaoOperacoes;
    private javax.swing.JButton botaoPoupanca;
    private javax.swing.JButton botaoRelatorio;
    private javax.swing.JButton botaoSair;
    private javax.swing.JLabel titulo;
}