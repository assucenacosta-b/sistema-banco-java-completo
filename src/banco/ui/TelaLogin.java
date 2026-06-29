package banco.ui;

import banco.model.Usuario;
import banco.service.UsuarioService;

public class TelaLogin extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaLogin.class.getName());

    private final UsuarioService usuarioService = new UsuarioService();
    private int tentativasFalhas = 0;
    private javax.swing.JLabel labelErro;
    private javax.swing.Timer timerBloqueio;

    private javax.swing.JPanel FundoTela;
    private javax.swing.JLabel IconeCadeado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel TituloLogin;
    private javax.swing.JLabel TextoLogin;
    private javax.swing.JTextField DigitarLogin;
    private javax.swing.JLabel TextoSenha;
    private javax.swing.JPasswordField DigitarSenha;
    private javax.swing.JButton BotaoEntrar;
    private javax.swing.JButton BotaoSair;

    public TelaLogin() {
        initComponents();
    }

    private void initComponents() {
        FundoTela = new javax.swing.JPanel();
        IconeCadeado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TituloLogin = new javax.swing.JLabel();
        TextoLogin = new javax.swing.JLabel();
        DigitarLogin = new javax.swing.JTextField();
        TextoSenha = new javax.swing.JLabel();
        DigitarSenha = new javax.swing.JPasswordField();
        labelErro = new javax.swing.JLabel();
        BotaoEntrar = new javax.swing.JButton();
        BotaoSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SusuBank - Login");
        setSize(new java.awt.Dimension(840, 560));
        setResizable(false);
        getContentPane().setLayout(new java.awt.BorderLayout());

        FundoTela.setBackground(new java.awt.Color(249, 228, 237));
        FundoTela.setLayout(null);

        IconeCadeado.setFont(new java.awt.Font("Segoe UI Emoji", 0, 48));
        IconeCadeado.setForeground(new java.awt.Color(150, 36, 84));
        IconeCadeado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IconeCadeado.setText("\uD83D\uDD12");
        IconeCadeado.setBounds(380, 60, 80, 70);
        FundoTela.add(IconeCadeado);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 36));
        jLabel1.setForeground(new java.awt.Color(150, 36, 84));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SusuBank");
        jLabel1.setBounds(0, 135, 840, 50);
        FundoTela.add(jLabel1);

        TituloLogin.setFont(new java.awt.Font("Segoe UI", 1, 16));
        TituloLogin.setForeground(new java.awt.Color(150, 36, 84));
        TituloLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TituloLogin.setText("Acesse sua conta");
        TituloLogin.setBounds(0, 190, 840, 25);
        FundoTela.add(TituloLogin);

        TextoLogin.setFont(new java.awt.Font("Segoe UI", 1, 13));
        TextoLogin.setForeground(new java.awt.Color(150, 36, 84));
        TextoLogin.setText("Login");
        TextoLogin.setBounds(220, 250, 400, 18);
        FundoTela.add(TextoLogin);

        DigitarLogin.setFont(new java.awt.Font("Segoe UI", 0, 14));
        DigitarLogin.setBackground(new java.awt.Color(255, 255, 255));
        DigitarLogin.addActionListener(e -> tentarLogin());
        DigitarLogin.setBounds(220, 272, 400, 34);
        FundoTela.add(DigitarLogin);

        TextoSenha.setFont(new java.awt.Font("Segoe UI", 1, 13));
        TextoSenha.setForeground(new java.awt.Color(150, 36, 84));
        TextoSenha.setText("Senha");
        TextoSenha.setBounds(220, 320, 400, 18);
        FundoTela.add(TextoSenha);

        DigitarSenha.setFont(new java.awt.Font("Segoe UI", 0, 14));
        DigitarSenha.setBackground(new java.awt.Color(255, 255, 255));
        DigitarSenha.addActionListener(e -> tentarLogin());
        DigitarSenha.setBounds(220, 342, 400, 34);
        FundoTela.add(DigitarSenha);

        labelErro.setFont(new java.awt.Font("Segoe UI", 1, 12));
        labelErro.setForeground(new java.awt.Color(120, 20, 60));
        labelErro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelErro.setText(" ");
        labelErro.setBounds(220, 382, 400, 20);
        FundoTela.add(labelErro);

        BotaoEntrar.setBackground(new java.awt.Color(150, 36, 84));
        BotaoEntrar.setFont(new java.awt.Font("Segoe UI Black", 0, 14));
        BotaoEntrar.setForeground(new java.awt.Color(255, 255, 255));
        BotaoEntrar.setText("Entrar");
        BotaoEntrar.setFocusPainted(false);
        BotaoEntrar.addActionListener(e -> tentarLogin());
        BotaoEntrar.setBounds(220, 415, 400, 40);
        FundoTela.add(BotaoEntrar);

        BotaoSair.setBackground(new java.awt.Color(249, 228, 237));
        BotaoSair.setFont(new java.awt.Font("Segoe UI", 1, 12));
        BotaoSair.setForeground(new java.awt.Color(150, 36, 84));
        BotaoSair.setBorderPainted(false);
        BotaoSair.setFocusPainted(false);
        BotaoSair.setText("Sair");
        BotaoSair.addActionListener(e -> System.exit(0));
        BotaoSair.setBounds(380, 465, 80, 25);
        FundoTela.add(BotaoSair);

        getContentPane().add(FundoTela);
        setLocationRelativeTo(null);
    }

    private void tentarLogin() {
        String login = DigitarLogin.getText().trim();
        String senha = new String(DigitarSenha.getPassword());

        if (login.isEmpty() || senha.isEmpty()) {
            labelErro.setText("Preencha login e senha.");
            return;
        }

        Usuario usuario = usuarioService.autenticar(login, senha);

        if (usuario != null) {
            tentativasFalhas = 0;
            labelErro.setText(" ");
            banco.util.SessaoUsuario.login(usuario);
            TelaMenuPrincipal menu = new TelaMenuPrincipal(usuario);
            menu.setVisible(true);
            this.dispose();
        } else {
            tentativasFalhas++;
            labelErro.setText("Login ou senha invalidos. Tentativa " + tentativasFalhas + " de 3.");
            DigitarSenha.setText("");
            if (tentativasFalhas >= 3) {
                bloquearBotaoTemporariamente();
            }
        }
    }

    private void bloquearBotaoTemporariamente() {
        BotaoEntrar.setEnabled(false);
        final int[] segundosRestantes = {30};

        timerBloqueio = new javax.swing.Timer(1000, evt -> {
            segundosRestantes[0]--;
            labelErro.setText("Muitas tentativas. Aguarde " + segundosRestantes[0] + "s.");
            if (segundosRestantes[0] <= 0) {
                timerBloqueio.stop();
                BotaoEntrar.setEnabled(true);
                tentativasFalhas = 0;
                labelErro.setText(" ");
            }
        });
        timerBloqueio.start();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}