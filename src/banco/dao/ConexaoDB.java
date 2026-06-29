package banco.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoDB {

    private static ConexaoDB instancia;
    private Connection conexao;

    private static final String CAMINHO_PROPERTIES = "db.properties";

    private ConexaoDB() {
        try {
            Properties props = new Properties();
            try (InputStream input = new FileInputStream(CAMINHO_PROPERTIES)) {
                props.load(input);
            }

            String url = props.getProperty("db.url");
            String usuario = props.getProperty("db.usuario");
            String senha = props.getProperty("db.senha");

            this.conexao = DriverManager.getConnection(url, usuario, senha);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler db.properties.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados PostgreSQL.", e);
        }
    }

    public static synchronized ConexaoDB getInstancia() {
        if (instancia == null) {
            instancia = new ConexaoDB();
        }
        return instancia;
    }

    public Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                instancia = new ConexaoDB();
                return instancia.conexao;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar status da conexao.", e);
        }
        return conexao;
    }
}