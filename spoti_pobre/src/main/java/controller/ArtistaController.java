package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.javalin.config.JavalinConfig;
import negocio.Artista;
import persistencia.ArtistaDAO;

public class ArtistaController {

    public ArtistaController(JavalinConfig config) {
         config.routes.get("/artistas/", ctx -> {
            ArrayList<Artista> vet = new ArtistaDAO().listar();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vetArtista", vet);
            ctx.render("/templates/artistas/index.html", map);
        });

        config.routes.get("/artistas/tela_adicionar", ctx -> {
            ctx.render("/templates/artistas/tela_adicionar.html");
        });

        config.routes.get("/artistas/tela_alterar/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Artista artista = new ArtistaDAO().obter(id);
            Map<String, Object> map = new HashMap<>();
            map.put("artista", artista);
            ctx.render("/templates/artistas/tela_alterar.html", map);
        });

        config.routes.post("/artistas/alterar", ctx -> {
            int id = Integer.parseInt(ctx.formParam("id"));
            String nome = ctx.formParam("nome");
            Artista artistaNovo = new Artista();
            artistaNovo.setId(id);
            artistaNovo.setNome(nome);
            boolean resultado = new ArtistaDAO().atualizar(artistaNovo);
            if (resultado) {
                ctx.redirect("/artistas/");
            } else {
                ctx.html("deu xabum");
            }
        });

        config.routes.get("/artistas/excluir/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            new ArtistaDAO().deletar(id);
            ctx.redirect("/artistas/");
        });

        config.routes.post("/artistas/adicionar", ctx -> {
            String nome = ctx.formParam("nome");
            Artista artistaNovo = new Artista();
            artistaNovo.setNome(nome);
            boolean resultado = new ArtistaDAO().salvar(artistaNovo);
            if (resultado) {
                ctx.redirect("/artistas/");
            } else {
                ctx.html("deu xabum");
            }
        });
    }

}
