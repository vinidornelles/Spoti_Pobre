package controller;

import io.javalin.config.JavalinConfig;
import negocio.Musica;
import negocio.Reproducao;
import negocio.Usuario;
import persistencia.ReproducaoDAO;

public class ReproducaoController {

    public ReproducaoController(JavalinConfig config) {
        config.routes.post("/reproduzir", ctx -> {
            int musicaId = Integer.parseInt(ctx.formParam("musica_id"));
            int usuarioId = Integer.parseInt(ctx.formParam("usuario_id"));

            Musica musica = new Musica();
            musica.setId(musicaId);
            Usuario usuario = new Usuario();
            usuario.setId(usuarioId);
            Reproducao reproducao = new Reproducao();
            reproducao.setMusica(musica);
            reproducao.setUsuario(usuario);

            boolean resultado = new ReproducaoDAO().salvar(reproducao);
            if (resultado) {
                ctx.redirect("/dashboard");
            } else {
                ctx.html("deu xabum");
            }
        });
    }

}
