package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.Playlist;
import negocio.Usuario;

public class PlaylistDAO {

    public ArrayList<Playlist> listar() throws SQLException {
        ArrayList<Playlist> vetPlaylist = new ArrayList<Playlist>();
        String sql = "SELECT playlist.id, playlist.nome, playlist.publica, usuario.id AS dono_id, usuario.nome AS dono_nome FROM playlist "
                + "JOIN usuario_playlist ON playlist.id = usuario_playlist.playlist_id "
                + "JOIN usuario ON usuario.id = usuario_playlist.usuario_id "
                + "WHERE usuario_playlist.dono IS TRUE ORDER BY playlist.id DESC;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        ResultSet rs = instrucaoSQL.executeQuery();
        while (rs.next()) {
            Playlist playlist = new Playlist();
            playlist.setId(rs.getInt("id"));
            playlist.setNome(rs.getString("nome"));
            playlist.setPublica(rs.getBoolean("publica"));
            Usuario dono = new Usuario();
            dono.setId(rs.getInt("dono_id"));
            dono.setNome(rs.getString("dono_nome"));
            playlist.setDono(dono);
            vetPlaylist.add(playlist);
        }
        conexao.close();
        return vetPlaylist;
    }

    public boolean salvar(Playlist playlist) throws SQLException {
        Connection conexao = new ConexaoPostgreSQL().getConexao();

        String sqlPlaylist = "INSERT INTO playlist (nome, publica) VALUES (?, ?) RETURNING id;";
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sqlPlaylist);
        instrucaoSQL.setString(1, playlist.getNome());
        instrucaoSQL.setBoolean(2, playlist.isPublica());
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            playlist.setId(rs.getInt("id"));
        }

        String sqlUsuarioPlaylist = "INSERT INTO usuario_playlist (usuario_id, playlist_id, dono) VALUES (?, ?, true);";
        PreparedStatement instrucaoVinculoSQL = conexao.prepareStatement(sqlUsuarioPlaylist);
        instrucaoVinculoSQL.setInt(1, playlist.getDono().getId());
        instrucaoVinculoSQL.setInt(2, playlist.getId());
        int nroLinhasAfetadas = instrucaoVinculoSQL.executeUpdate();

        conexao.close();
        return nroLinhasAfetadas == 1;
    }

}
