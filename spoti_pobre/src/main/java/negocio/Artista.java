package negocio;

import java.util.ArrayList;

public class Artista {
    private int id;
    private String nome; 
    private ArrayList<Album> albuns;    

    public Artista(){
        this.albuns = new ArrayList<Album>();
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
    public ArrayList<Album> getAlbuns() {
        return albuns;
    }
    public void setAlbuns(ArrayList<Album> albuns) {
        this.albuns = albuns;
    }
  
    

}
