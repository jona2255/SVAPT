package com.svapt.Controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.svapt.Model.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistViewModel extends AndroidViewModel {

    private final Application application;
    public MutableLiveData<List<Playlist>> listaPlaylist = new MutableLiveData<>();
    MutableLiveData<Playlist> elementoSeleccionado = new MutableLiveData<>();


    public PlaylistViewModel(@NonNull Application application) {
        super(application);
        rellenarListaPlaylist();
        this.application = application;
    }



    public void rellenarListaPlaylist(){
        List<Playlist> elementos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Playlist playlist = new Playlist("Playlist " + i);
            elementos.add(playlist);
        }
        listaPlaylist.setValue(elementos);
    }

    public void establecerElementoSeleccionado(Playlist elemento){
        elementoSeleccionado.setValue(elemento);
    }
}
