package negocio;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Musica {
    private int id;
    private String nome;
    private LocalTime duracao;

    private Album album;

    public Musica() {
        this.album = new Album();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getDuracao() {
        return duracao;
    }

    public void setDuracao(LocalTime duracao) {
        this.duracao = duracao;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    
    public String getDuracaoFormatada() {
        if (duracao == null) {
            return "--:--";
        }
        return duracao.format(DateTimeFormatter.ofPattern("mm:ss"));
    }

}
