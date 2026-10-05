package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.Album;
import negocio.Musica;

public class MusicaDAO {

    public ArrayList<Musica> listarComAlbum() throws SQLException {
        ArrayList<Musica> vetMusica = new ArrayList<Musica>();
        String sql = "SELECT album.id AS album_id, album.titulo AS album_titulo, musica.id, musica.nome, musica.duracao FROM album "
                + "JOIN album_musica ON album.id = album_musica.album_id "
                + "JOIN musica ON musica.id = album_musica.musica_id "
                + "ORDER BY album.titulo, musica.nome;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        ResultSet rs = instrucaoSQL.executeQuery();
        while (rs.next()) {
            Musica musica = new Musica();
            musica.setId(rs.getInt("id"));
            musica.setNome(rs.getString("nome"));
            musica.setDuracao((rs.getTime("duracao") != null) ? rs.getTime("duracao").toLocalTime() : null);
            Album album = new Album();
            album.setId(rs.getInt("album_id"));
            album.setTitulo(rs.getString("album_titulo"));
            musica.setAlbum(album);
            vetMusica.add(musica);
        }
        conexao.close();
        return vetMusica;
    }

}
