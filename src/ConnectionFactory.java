import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private static String usuario = "postgres";
    private static String senha = "postgres";
    private static String host = "localhost";
    private static String port = "5432";
    private static String db = "20261_fatec_ipi_poo_p2";

    public static Connection obterConexao() {
        try {
            var s = String.format(
                "jdbc:postgresql://%s:%s/%s",
                host, port, db
            );
            Connection conexao = DriverManager.getConnection(
                s, usuario, senha
            );
            return conexao;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println(obterConexao());
    }
}