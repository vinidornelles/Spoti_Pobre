package apresentacao;

import java.sql.SQLException;
import controller.*;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinMustache;

public class Main {
    public static void main(String[] args) throws SQLException {
        Javalin.create(config -> {
            config.fileRenderer(new JavalinMustache());
       
            config.routes.get("/css/estilo.css", ctx -> {
                ctx.contentType("text/css");
                ctx.result(Main.class.getResourceAsStream("/static/css/estilo.css"));
            });

            new UsuarioController(config);
            new ArtistaController(config);
            new GeneroController(config);
            new AlbumController(config);
            new PlaylistController(config);
            new DashboardController(config);
            new ReproducaoController(config);
        }).start(7070);
    }
}