package controller;

import java.util.HashMap;
import java.util.Map;

import io.javalin.config.JavalinConfig;
import persistencia.MusicaDAO;
import persistencia.ReproducaoDAO;
import persistencia.UsuarioDAO;

public class DashboardController {

    public DashboardController(JavalinConfig config) {
        config.routes.get("/dashboard", ctx -> {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vetMusica", new MusicaDAO().listarComAlbum());
            map.put("vetUsuario", new UsuarioDAO().listar());
            map.put("vetReproducao", new ReproducaoDAO().listarUltimas());
            ctx.render("/templates/dashboard/index.html", map);
        });
    }

}
