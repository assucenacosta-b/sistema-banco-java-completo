package banco.util;

import banco.dao.ConexaoDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MigracaoService {

    private static final String PASTA_MIGRACOES = "/db/migration";

    public static void executarMigracoes() {
        Connection conexao = ConexaoDB.getInstancia().getConexao();
        for (String arquivo : listarArquivosOrdenados()) {
            String sql = lerConteudo(arquivo);
            try (Statement stmt = conexao.createStatement()) {
                stmt.execute(sql);
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao executar migracao " + arquivo + ": " + e.getMessage(), e);
            }
        }
    }

    private static List<String> listarArquivosOrdenados() {
        List<String> arquivos = new ArrayList<>(List.of(
                "V1__criar_tabelas.sql",
                "V2__inserir_usuario_inicial.sql"
        ));
        arquivos.sort(Comparator.naturalOrder());
        return arquivos;
    }

    private static String lerConteudo(String nomeArquivo) {
        String caminho = PASTA_MIGRACOES + "/" + nomeArquivo;
        try (InputStream input = MigracaoService.class.getResourceAsStream(caminho)) {
            if (input == null) {
                throw new IOException("Arquivo de migracao nao encontrado no classpath: " + caminho);
            }
            try (BufferedReader leitor = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
                return leitor.lines().collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo de migracao " + nomeArquivo, e);
        }
    }
}

