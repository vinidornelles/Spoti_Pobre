package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.javalin.config.JavalinConfig;
import negocio.Genero;
import persistencia.GeneroDAO;

public class GeneroController {

    public GeneroController(JavalinConfig config) {
         config.routes.get("/generos/", ctx -> {
            ArrayList<Genero> vet = new GeneroDAO().listar();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vetGenero", vet);
            ctx.render("/templates/generos/index.html", map);
        });

        config.routes.get("/generos/tela_adicionar", ctx -> {
            ctx.render("/templates/generos/tela_adicionar.html");
        });

        config.routes.get("/generos/tela_alterar/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Genero genero = new GeneroDAO().obter(id);
            Map<String, Object> map = new HashMap<>();
            map.put("genero", genero);
            ctx.render("/templates/generos/tela_alterar.html", map);
        });

        config.routes.post("/generos/alterar", ctx -> {
            int id = Integer.parseInt(ctx.formParam("id"));
            String nome = ctx.formParam("nome");
            Genero generoNovo = new Genero();
            generoNovo.setId(id);
            generoNovo.setNome(nome);
            boolean resultado = new GeneroDAO().atualizar(generoNovo);
            if (resultado) {
                ctx.redirect("/generos/");
            } else {
                ctx.html("deu xabum");
            }
        });

        config.routes.get("/generos/excluir/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            new GeneroDAO().deletar(id);
            ctx.redirect("/generos/");
        });

        config.routes.post("/generos/adicionar", ctx -> {
            String nome = ctx.formParam("nome");
            Genero generoNovo = new Genero();
            generoNovo.setNome(nome);
            boolean resultado = new GeneroDAO().salvar(generoNovo);
            if (resultado) {
                ctx.redirect("/generos/");
            } else {
                ctx.html("deu xabum");
            }
        });
    }

}
