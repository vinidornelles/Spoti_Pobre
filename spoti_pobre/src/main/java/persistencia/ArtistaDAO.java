package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.Artista;

public class ArtistaDAO {

    public ArrayList<Artista> listar() throws SQLException {
        ArrayList<Artista> vetArtista = new ArrayList<Artista>();
        String sql = "SELECT * FROM artista ORDER BY id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        ResultSet rs = instrucaoSQL.executeQuery();
        while (rs.next()) {
            Artista artista = new Artista();
            artista.setId(rs.getInt("id"));
            artista.setNome(rs.getString("nome"));
            vetArtista.add(artista);
        }
        conexao.close();
        return vetArtista;
    }

    public Artista obter(int id) throws SQLException {
        Artista artista = new Artista();
        String sql = "SELECT * FROM artista where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            artista.setId(rs.getInt("id"));
            artista.setNome(rs.getString("nome"));
        }
        conexao.close();
        return artista;
    }

    public boolean salvar(Artista artista) throws SQLException {
        String sql = "INSERT INTO artista (nome) VALUES (?) RETURNING id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setString(1, artista.getNome());
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            artista.setId(rs.getInt("id"));
        }
        conexao.close();
        return artista.getId() != 0;

    }

    public void deletar(int id) throws SQLException {
        String sql = "BEGIN;" +
                "DELETE FROM album_artista WHERE artista_id = ? ;" +
                "DELETE FROM artista WHERE id = ?;" +
                "COMMIT;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        instrucaoSQL.setInt(2, id);
        instrucaoSQL.execute();
        conexao.close();
    }

    public boolean atualizar(Artista artista) throws SQLException {
        String sql = "UPDATE artista SET nome = ? where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setString(1, artista.getNome());
        instrucaoSQL.setInt(2, artista.getId());

        int nroLinhasAfetadas = instrucaoSQL.executeUpdate();
        conexao.close();
        return nroLinhasAfetadas == 1;

    }

}
