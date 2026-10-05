package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.Genero;

public class GeneroDAO {

    public ArrayList<Genero> listar() throws SQLException {
        ArrayList<Genero> vetGenero = new ArrayList<Genero>();
        String sql = "SELECT * FROM genero ORDER BY id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        ResultSet rs = instrucaoSQL.executeQuery();
        while (rs.next()) {
            Genero genero = new Genero();
            genero.setId(rs.getInt("id"));
            genero.setNome(rs.getString("nome"));
            vetGenero.add(genero);
        }
        conexao.close();
        return vetGenero;
    }

    public Genero obter(int id) throws SQLException {
        Genero genero = new Genero();
        String sql = "SELECT * FROM genero where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            genero.setId(rs.getInt("id"));
            genero.setNome(rs.getString("nome"));
        }
        conexao.close();
        return genero;
    }

    public boolean salvar(Genero genero) throws SQLException {
        String sql = "INSERT INTO genero (nome) VALUES (?) RETURNING id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setString(1, genero.getNome());
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            genero.setId(rs.getInt("id"));
        }
        conexao.close();
        return genero.getId() != 0;

    }

    public void deletar(int id) throws SQLException {
        String sql = "BEGIN;" +
                "UPDATE album SET genero_id = NULL WHERE genero_id = ? ;" +
                "DELETE FROM genero WHERE id = ?;" +
                "COMMIT;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setInt(1, id);
        instrucaoSQL.setInt(2, id);
        instrucaoSQL.execute();
        conexao.close();
    }

    public boolean atualizar(Genero genero) throws SQLException {
        String sql = "UPDATE genero SET nome = ? where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
        instrucaoSQL.setString(1, genero.getNome());
        instrucaoSQL.setInt(2, genero.getId());

        int nroLinhasAfetadas = instrucaoSQL.executeUpdate();
        conexao.close();
        return nroLinhasAfetadas == 1;

    }

  

}
