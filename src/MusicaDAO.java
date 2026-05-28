import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MusicaDAO {
    
    public static List<Musica> listar() throws Exception {
        List<Musica> musicas =new ArrayList<>();
        var sql ="SELECT * FROM musica";
        try (
            var conexao = ConnectionFactory.obterConexao();
            var ps = conexao.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                Musica musica = new Musica(titulo);
                musicas.add(musica);
            }
        }
        return musicas;
    }
}