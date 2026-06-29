import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;


public class GerarHash {
    public static void main(String[] args) throws Exception {
        String matricula = "20252021631";

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(matricula.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        System.out.println("Hash gerado: " + sb);
    }
}
