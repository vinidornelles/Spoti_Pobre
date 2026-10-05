package negocio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Reproducao {
    private Usuario usuario;
    private Musica musica;
    private LocalDateTime quando;

    public Reproducao() {
        this.usuario = new Usuario();
        this.musica = new Musica();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public LocalDateTime getQuando() {
        return quando;
    }

    public void setQuando(LocalDateTime quando) {
        this.quando = quando;
    }

    public String getQuandoFormatado() {
        if (quando == null) {
            return "";
        }
        return quando.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

}
