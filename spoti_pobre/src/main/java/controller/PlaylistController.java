package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.javalin.config.JavalinConfig;
import negocio.Playlist;
import negocio.Usuario;
import persistencia.PlaylistDAO;
import persistencia.UsuarioDAO;

public class PlaylistController {

    public PlaylistController(JavalinConfig config) {
        config.routes.get("/playlists/", ctx -> {
            ArrayList<Playlist> vet = new PlaylistDAO().listar();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vetPlaylist", vet);
            ctx.render("/templates/playlists/index.html", map);
        });

        config.routes.get("/playlists/nova", ctx -> {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vetUsuario", new UsuarioDAO().listar());
            ctx.render("/templates/playlists/nova.html", map);
        });

        config.routes.post("/playlists/nova", ctx -> {
            String nome = ctx.formParam("nome");
            boolean publica = ctx.formParam("publica") != null;
            int usuarioId = Integer.parseInt(ctx.formParam("usuario_id"));

            Usuario dono = new Usuario();
            dono.setId(usuarioId);
            Playlist playlist = new Playlist();
            playlist.setNome(nome);
            playlist.setPublica(publica);
            playlist.setDono(dono);

            boolean resultado = new PlaylistDAO().salvar(playlist);
            if (resultado) {
                ctx.redirect("/playlists/");
            } else {
                ctx.html("deu xabum");
            }
        });
    }

}
