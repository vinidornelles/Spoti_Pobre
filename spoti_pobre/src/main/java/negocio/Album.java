package negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Album {
    private int id;
    private String titulo;
    private LocalDate dataLancamento;
    private Genero genero;
    private List<Artista> artistas;

    public Album() {
        this.genero = new Genero();
        this.artistas = new ArrayList<Artista>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public String artistasToString() {
        String resultado = "";
        for (Artista artista : artistas) {
            if (!resultado.isBlank()) {
                resultado += ", ";
            }
            resultado += artista.getNome();
        }
        return resultado.isBlank() ? "sem artista" : resultado;
    }

}
