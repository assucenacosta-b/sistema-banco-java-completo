package banco.ui;

import banco.model.Usuario;
import banco.service.UsuarioService;

public class TelaCadastroUsuario extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaCadastroUsuario.class.getName());

    private final UsuarioService usuarioService = new UsuarioService();
    private Usuario usuarioEmEdicao;
    private javax.swing.JLabel jLabelMensagem;

    public TelaCadastroUsuario() {
        initComponents();
    }

    public TelaCadastroUsuario(Usuario usuario) {
        this();
        this.usuarioEmEdicao = usuario;
        if (usuario != null) {
            preencherCampos(usuario);
        }
    }

    private void initComponents() {

        FundoTela = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabelMensagem = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(840, 560));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FundoTela.setBackground(new java.awt.Color(249, 228, 237));
        FundoTela.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 28));
        jLabel6.setForeground(new java.awt.Color(150, 36, 84));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cadastro de Usuario");
        FundoTela.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 840, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jLabel1.setForeground(new java.awt.Color(150, 36, 84));
        jLabel1.setText("Nome completo");
        FundoTela.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 115, 400, 18));

        jTextField1.setBackground(new java.awt.Color(249, 228, 237));
        jTextField1.setForeground(new java.awt.Color(150, 36, 84));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(150, 36, 84)));
        FundoTela.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 135, 400, 32));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jLabel2.setForeground(new java.awt.Color(150, 36, 84));
        jLabel2.setText("Login");
        FundoTela.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 400, 18));

        jTextField2.setBackground(new java.awt.Color(249, 228, 237));
        jTextField2.setForeground(new java.awt.Color(150, 36, 84));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(150, 36, 84)));
        FundoTela.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 400, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jLabel3.setForeground(new java.awt.Color(150, 36, 84));
        jLabel3.setText("Senha");
        FundoTela.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 245, 400, 18));

        jPasswordField1.setBackground(new java.awt.Color(249, 228, 237));
        jPasswordField1.setForeground(new java.awt.Color(150, 36, 84));
        jPasswordField1.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jPasswordField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(150, 36, 84)));
        FundoTela.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 265, 400, 32));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jLabel4.setForeground(new java.awt.Color(150, 36, 84));
        jLabel4.setText("Confirmar Senha");
        FundoTela.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 400, 18));

        jPasswordField2.setEditable(true);
        jPasswordField2.setBackground(new java.awt.Color(249, 228, 237));
        jPasswordField2.setForeground(new java.awt.Color(150, 36, 84));
        jPasswordField2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jPasswordField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(150, 36, 84)));
        FundoTela.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 400, 32));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jLabel5.setForeground(new java.awt.Color(150, 36, 84));
        jLabel5.setText("Perfil");
        FundoTela.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 375, 400, 18));

        jComboBox1.setBackground(new java.awt.Color(249, 228, 237));
        jComboBox1.setForeground(new java.awt.Color(150, 36, 84));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "OPERADOR" }));
        FundoTela.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 395, 400, 32));

        jLabelMensagem.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabelMensagem.setForeground(new java.awt.Color(90, 10, 40));
        jLabelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMensagem.setText(" ");
        FundoTela.add(jLabelMensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 437, 400, 20));

        jButton1.setBackground(new java.awt.Color(249, 228, 237));
        jButton1.setForeground(new java.awt.Color(150, 36, 84));
        jButton1.setFont(new java.awt.Font("Segoe UI Black", 0, 14));
        jButton1.setFocusPainted(false);
        jButton1.setBorderPainted(false);
        jButton1.setText("Salvar");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        FundoTela.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 465, 190, 40));

        jButton3.setBackground(new java.awt.Color(249, 228, 237));
        jButton3.setForeground(new java.awt.Color(150, 36, 84));
        jButton3.setFont(new java.awt.Font("Segoe UI Black", 0, 14));
        jButton3.setFocusPainted(false);
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 36, 84), 2));
        jButton3.setText("Cancelar");
        jButton3.addActionListener(this::jButton3ActionPerformed);
        FundoTela.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 465, 190, 40));

        getContentPane().add(FundoTela, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 560));

        setLocationRelativeTo(null);
    }

    private void preencherCampos(Usuario usuario) {
        jTextField1.setText(usuario.getNome());
        jTextField2.setText(usuario.getLogin());
        jComboBox1.setSelectedItem(usuario.getPerfil());
    }

    private String gerarHashSHA256(String texto) {
        return usuarioService.gerarHashSHA256(texto);
    }

    private void salvar() {
        String nome = jTextField1.getText().trim();
        String login = jTextField2.getText().trim();
        String senha = new String(jPasswordField1.getPassword());
        String confirmar = new String(jPasswordField2.getPassword());
        String perfil = (String) jComboBox1.getSelectedItem();

        if (nome.isEmpty() || login.isEmpty()) {
            jLabelMensagem.setText("Preencha todos os campos obrigatorios.");
            return;
        }

        boolean isEdicao = (usuarioEmEdicao != null);
        boolean senhaInformada = !senha.isEmpty();

        if (!isEdicao && !senhaInformada) {
            jLabelMensagem.setText("A senha e obrigatoria para novo usuario.");
            return;
        }

        if (senhaInformada && !senha.equals(confirmar)) {
            jLabelMensagem.setText("As senhas nao coincidem.");
            return;
        }

        Usuario existente = usuarioService.buscarPorLogin(login);
        if (existente != null && (!isEdicao || !existente.getId().equals(usuarioEmEdicao.getId()))) {
            jLabelMensagem.setText("Este login ja esta em uso.");
            return;
        }

        try {
            if (isEdicao) {
                usuarioEmEdicao.setNome(nome);
                usuarioEmEdicao.setLogin(login);
                usuarioEmEdicao.setPerfil(perfil);
                if (senhaInformada) {
                    usuarioEmEdicao.setSenha(gerarHashSHA256(senha));
                }
                usuarioService.atualizar(usuarioEmEdicao);
            } else {
                Usuario novo = new Usuario(null, login, gerarHashSHA256(senha), nome, perfil);
                usuarioService.cadastrar(novo);
            }

            jLabelMensagem.setForeground(new java.awt.Color(40, 110, 40));
            jLabelMensagem.setText("Usuario salvo com sucesso!");
            this.dispose();
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
            jLabelMensagem.setForeground(new java.awt.Color(150, 0, 0));
            jLabelMensagem.setText("Erro ao salvar usuario: " + ex.getMessage());
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        salvar();
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new TelaCadastroUsuario().setVisible(true));
    }

    private javax.swing.JPanel FundoTela;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
}