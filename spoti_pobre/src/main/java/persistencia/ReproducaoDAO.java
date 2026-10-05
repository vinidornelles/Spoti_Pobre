package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.Musica;
import negocio.Reproducao;
import negocio.Usuario;

public class ReproducaoDAO {

    public boolean salvar(Reproducao reproducao) throws SQLException {
        String sql = "INSERT INTO reproducao (musica_id, usuario_id, quando) VALUES (?, ?, CURRENT_TIMESTAMP) "
                + "ON CONFLICT (musica_id, usuario_id) DO UPDATE SET quando = CURRENT_TIMESTAMP;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, reproducao.getMusica().getId());
        instrucaoSQL.setInt(2, reproducao.getUsuario().getId());
        int nroLinhasAfetadas = instrucaoSQL.executeUpdate();
        conexao.close();
        return nroLinhasAfetadas == 1;
    }

    public ArrayList<Reproducao> listarUltimas() throws SQLException {
        ArrayList<Reproducao> vetReproducao = new ArrayList<Reproducao>();
        String sql = "SELECT usuario.nome AS usuario_nome, musica.nome AS musica_nome, reproducao.quando FROM reproducao "
                + "JOIN usuario ON usuario.id = reproducao.usuario_id "
                + "JOIN musica ON musica.id = reproducao.musica_id "
                + "ORDER BY reproducao.quando DESC LIMIT 10;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        ResultSet rs = instrucaoSQL.executeQuery();
        while (rs.next()) {
            Reproducao reproducao = new Reproducao();
            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString("usuario_nome"));
            reproducao.setUsuario(usuario);
            Musica musica = new Musica();
            musica.setNome(rs.getString("musica_nome"));
            reproducao.setMusica(musica);
            reproducao.setQuando((rs.getTimestamp("quando") != null) ? rs.getTimestamp("quando").toLocalDateTime() : null);
            vetReproducao.add(reproducao);
        }
        conexao.close();
        return vetReproducao;
    }

}
