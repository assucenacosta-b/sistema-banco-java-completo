package banco.ui;

import banco.dao.ClienteDAO;
import banco.model.Cliente;

public class TelaCadastroCliente extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName());

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private Cliente clienteEmEdicao;
    private javax.swing.JLabel jLabelMensagem;

    public TelaCadastroCliente() {
        initComponents();
    }

    public TelaCadastroCliente(Cliente cliente) {
        this();
        this.clienteEmEdicao = cliente;
        if (cliente != null) {
            preencherCampos(cliente);
        }
    }

    private void initComponents() {

        FundoTela = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextFieldCpf = criarCampoCpf();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldTelefone = criarCampoTelefone();
        jLabelMensagem = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(840, 560));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FundoTela.setBackground(new java.awt.Color(249, 228, 237));
        FundoTela.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI Black", 1, 28));
        jLabelTitulo.setForeground(new java.awt.Color(150, 36, 84));
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Cadastro de Cliente");
        FundoTela.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 840, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jLabel1.setForeground(new java.awt.Color(150, 36, 84));
        jLabel1.setText("Nome");
        FundoTela.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 400, 18));

        jTextFieldNome.setBackground(new java.awt.Color(249, 228, 237));
        jTextFieldNome.setForeground(new java.awt.Color(150, 36, 84));
        jTextFieldNome.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jTextFieldNome.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(150, 36, 84)));
        FundoTela.add(jTextFieldNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 400, 32));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jLabel2.setForeground(new java.awt.Color(150, 36, 84));
        jLabel2.setText("CPF");
        FundoTela.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 225, 400, 18));

        jFormattedTextFieldCpf.setBackground(new java.awt.Color(249, 228, 237));
        jFormattedTextFieldCpf.setForeground(new java.awt.Color(150, 36, 84));
        jFormattedTextFieldCpf.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jFormattedTextFieldCpf.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(150, 36, 84)));
        FundoTela.add(jFormattedTextFieldCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 245, 400, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jLabel3.setForeground(new java.awt.Color(150, 36, 84));
        jLabel3.setText("Telefone");
        FundoTela.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 400, 18));

        jFormattedTextFieldTelefone.setBackground(new java.awt.Color(249, 228, 237));
        jFormattedTextFieldTelefone.setForeground(new java.awt.Color(150, 36, 84));
        jFormattedTextFieldTelefone.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jFormattedTextFieldTelefone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(150, 36, 84)));
        FundoTela.add(jFormattedTextFieldTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 400, 32));

        jLabelMensagem.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabelMensagem.setForeground(new java.awt.Color(90, 10, 40));
        jLabelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMensagem.setText(" ");
        FundoTela.add(jLabelMensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 365, 400, 20));

        jButton1.setBackground(new java.awt.Color(249, 228, 237));
        jButton1.setForeground(new java.awt.Color(150, 36, 84));
        jButton1.setFont(new java.awt.Font("Segoe UI Black", 0, 14));
        jButton1.setFocusPainted(false);
        jButton1.setBorderPainted(false);
        jButton1.setText("Salvar");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        FundoTela.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, 190, 40));

        jButton3.setBackground(new java.awt.Color(249, 228, 237));
        jButton3.setForeground(new java.awt.Color(150, 36, 84));
        jButton3.setFont(new java.awt.Font("Segoe UI Black", 0, 14));
        jButton3.setFocusPainted(false);
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 36, 84), 2));
        jButton3.setText("Cancelar");
        jButton3.addActionListener(this::jButton3ActionPerformed);
        FundoTela.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, 190, 40));

        getContentPane().add(FundoTela, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 560));

        setLocationRelativeTo(null);
    }

    private javax.swing.JFormattedTextField criarCampoCpf() {
        javax.swing.text.MaskFormatter mascara = null;
        try {
            mascara = new javax.swing.text.MaskFormatter("###.###.###-##");
            mascara.setPlaceholderCharacter('_');
        } catch (java.text.ParseException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        return new javax.swing.JFormattedTextField(mascara);
    }

    private javax.swing.JFormattedTextField criarCampoTelefone() {
        javax.swing.text.MaskFormatter mascara = null;
        try {
            mascara = new javax.swing.text.MaskFormatter("(##) #####-####");
            mascara.setPlaceholderCharacter('_');
        } catch (java.text.ParseException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        return new javax.swing.JFormattedTextField(mascara);
    }

    private void preencherCampos(Cliente cliente) {
        jTextFieldNome.setText(cliente.getNome());
        jFormattedTextFieldCpf.setText(cliente.getCpf());
        jFormattedTextFieldTelefone.setText(cliente.getTelefone());
    }

    private void salvar() {
        String nome = jTextFieldNome.getText().trim();
        String cpf = jFormattedTextFieldCpf.getText().trim();
        String telefone = jFormattedTextFieldTelefone.getText().trim();

        if (nome.isEmpty() || cpf.contains("_") || telefone.contains("_")) {
            jLabelMensagem.setText("Preencha todos os campos corretamente.");
            return;
        }

        boolean isEdicao = (clienteEmEdicao != null);

        Cliente existente = clienteDAO.buscarPorCpf(cpf);
        if (existente != null && (!isEdicao || !existente.getId().equals(clienteEmEdicao.getId()))) {
            jLabelMensagem.setText("Este CPF ja esta cadastrado.");
            return;
        }

        try {
            if (isEdicao) {
                clienteEmEdicao.setNome(nome);
                clienteEmEdicao.setCpf(cpf);
                clienteEmEdicao.setTelefone(telefone);
                clienteDAO.atualizar(clienteEmEdicao);
            } else {
                Cliente novo = new Cliente(null, nome, cpf, telefone);
                clienteDAO.salvar(novo);
            }

            jLabelMensagem.setForeground(new java.awt.Color(40, 110, 40));
            jLabelMensagem.setText("Cliente salvo com sucesso!");
            new TelaMenuPrincipal().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
            jLabelMensagem.setForeground(new java.awt.Color(150, 0, 0));
            jLabelMensagem.setText("Erro ao salvar cliente: " + ex.getMessage());
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaMenuPrincipal().setVisible(true);
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

        java.awt.EventQueue.invokeLater(() -> new TelaCadastroCliente().setVisible(true));
    }

    private javax.swing.JPanel FundoTela;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JTextField jTextFieldNome;
}
