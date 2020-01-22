package com.svapt;

public class Cancion {

    public String titulo;
    public String artista;
    public String duracionTrans;
    public int duracion;

    public Cancion(String titulo, String artista, String duracionTrans, int duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracionTrans = duracionTrans;
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", duracion=" + duracion +
                '}';
    }
}
