package banco.ui;

import banco.model.Usuario;
import banco.service.UsuarioService;
import java.util.List;

public class TelaGerenciarUsuarios extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaGerenciarUsuarios.class.getName());

    private final UsuarioService usuarioService = new UsuarioService();

    public TelaGerenciarUsuarios() {
        initComponents();
        carregarTabela();
    }

    private void initComponents() {

        FundoTela = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jScrollPaneTabela = new javax.swing.JScrollPane();
        jTableUsuarios = new javax.swing.JTable();
        jButtonNovo = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jLabelMensagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(840, 560));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FundoTela.setBackground(new java.awt.Color(249, 228, 237));
        FundoTela.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI Black", 1, 28));
        jLabelTitulo.setForeground(new java.awt.Color(150, 36, 84));
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Gerenciar Usuarios");
        FundoTela.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 840, 40));

        jTableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Login", "Nome", "Perfil"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        jTableUsuarios.setBackground(new java.awt.Color(249, 228, 237));
        jTableUsuarios.setForeground(new java.awt.Color(150, 36, 84));
        jTableUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 13));
        jTableUsuarios.setRowHeight(28);
        jTableUsuarios.setSelectionBackground(new java.awt.Color(150, 36, 84));
        jTableUsuarios.setSelectionForeground(new java.awt.Color(249, 228, 237));
        jTableUsuarios.getTableHeader().setFont(new java.awt.Font("Segoe UI Black", 1, 13));
        jTableUsuarios.getTableHeader().setBackground(new java.awt.Color(150, 36, 84));
        jTableUsuarios.getTableHeader().setForeground(new java.awt.Color(249, 228, 237));
        jScrollPaneTabela.setViewportView(jTableUsuarios);
        jScrollPaneTabela.getViewport().setBackground(new java.awt.Color(249, 228, 237));
        FundoTela.add(jScrollPaneTabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 720, 300));

        jLabelMensagem.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabelMensagem.setForeground(new java.awt.Color(90, 10, 40));
        jLabelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMensagem.setText(" ");
        FundoTela.add(jLabelMensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 720, 20));

        jButtonNovo.setBackground(new java.awt.Color(150, 36, 84));
        jButtonNovo.setForeground(new java.awt.Color(249, 228, 237));
        jButtonNovo.setFont(new java.awt.Font("Segoe UI Black", 0, 13));
        jButtonNovo.setFocusPainted(false);
        jButtonNovo.setBorderPainted(false);
        jButtonNovo.setText("Novo Usuario");
        jButtonNovo.addActionListener(this::jButtonNovoActionPerformed);
        FundoTela.add(jButtonNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 445, 170, 40));

        jButtonEditar.setBackground(new java.awt.Color(150, 36, 84));
        jButtonEditar.setForeground(new java.awt.Color(249, 228, 237));
        jButtonEditar.setFont(new java.awt.Font("Segoe UI Black", 0, 13));
        jButtonEditar.setFocusPainted(false);
        jButtonEditar.setBorderPainted(false);
        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(this::jButtonEditarActionPerformed);
        FundoTela.add(jButtonEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 445, 170, 40));

        jButtonExcluir.setBackground(new java.awt.Color(249, 228, 237));
        jButtonExcluir.setForeground(new java.awt.Color(150, 36, 84));
        jButtonExcluir.setFont(new java.awt.Font("Segoe UI Black", 0, 13));
        jButtonExcluir.setFocusPainted(false);
        jButtonExcluir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 36, 84), 2));
        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(this::jButtonExcluirActionPerformed);
        FundoTela.add(jButtonExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 445, 170, 40));

        jButtonVoltar.setBackground(new java.awt.Color(249, 228, 237));
        jButtonVoltar.setForeground(new java.awt.Color(150, 36, 84));
        jButtonVoltar.setFont(new java.awt.Font("Segoe UI Black", 0, 13));
        jButtonVoltar.setFocusPainted(false);
        jButtonVoltar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 36, 84), 2));
        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(this::jButtonVoltarActionPerformed);
        FundoTela.add(jButtonVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 445, 150, 40));

        getContentPane().add(FundoTela, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 560));

        setLocationRelativeTo(null);
    }

    private void carregarTabela() {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) jTableUsuarios.getModel();
        modelo.setRowCount(0);

        List<Usuario> usuarios = usuarioService.listarTodos();
        for (Usuario u : usuarios) {
            modelo.addRow(new Object[]{u.getId(), u.getLogin(), u.getNome(), u.getPerfil()});
        }
    }

    private Long obterIdSelecionado() {
        int linha = jTableUsuarios.getSelectedRow();
        if (linha == -1) {
            return null;
        }
        Object valor = jTableUsuarios.getValueAt(linha, 0);
        return ((Number) valor).longValue();
    }

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {
        TelaCadastroUsuario tela = new TelaCadastroUsuario();
        tela.setVisible(true);
        tela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                carregarTabela();
            }
        });
    }

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {
        Long id = obterIdSelecionado();
        if (id == null) {
            jLabelMensagem.setText("Selecione um usuario na tabela para editar.");
            return;
        }

        Usuario usuario = usuarioService.buscarPorLogin(
                (String) jTableUsuarios.getValueAt(jTableUsuarios.getSelectedRow(), 1)
        );

        TelaCadastroUsuario tela = new TelaCadastroUsuario(usuario);
        tela.setVisible(true);
        tela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                carregarTabela();
            }
        });
    }

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        Long id = obterIdSelecionado();
        if (id == null) {
            jLabelMensagem.setText("Selecione um usuario na tabela para excluir.");
            return;
        }

        int confirmacao = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir este usuario?",
                "Confirmar exclusao",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirmacao != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        try {
            usuarioService.excluir(id);
            jLabelMensagem.setForeground(new java.awt.Color(40, 110, 40));
            jLabelMensagem.setText("Usuario excluido com sucesso!");
            carregarTabela();
        } catch (IllegalStateException ex) {
            jLabelMensagem.setForeground(new java.awt.Color(150, 0, 0));
            jLabelMensagem.setText(ex.getMessage());
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
            jLabelMensagem.setForeground(new java.awt.Color(150, 0, 0));
            jLabelMensagem.setText("Erro ao excluir usuario: " + ex.getMessage());
        }
    }

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaMenuPrincipal().setVisible(true);
        this.dispose();
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

        java.awt.EventQueue.invokeLater(() -> new TelaGerenciarUsuarios().setVisible(true));
    }

    private javax.swing.JPanel FundoTela;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelMensagem;
    private javax.swing.JScrollPane jScrollPaneTabela;
    private javax.swing.JTable jTableUsuarios;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonVoltar;
}