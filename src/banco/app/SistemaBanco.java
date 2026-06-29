package banco.app;

import banco.ui.TelaLogin;
import banco.util.MigracaoService;

import javax.swing.*;

public class SistemaBanco {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        
        }

        try {
            MigracaoService.executarMigracoes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Não foi possível executar as migrações do banco de dados.\n" +
                            "Verifique se o PostgreSQL está em execução e se db.properties está configurado corretamente.\n\n" +
                            "Detalhe: " + e.getMessage(),
                    "Erro de inicialização", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        SwingUtilities.invokeLater(() -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });
    }
}
