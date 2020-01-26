package com.svapt.Controller;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.svapt.Model.Favs;

import java.util.ArrayList;
import java.util.List;

public class FavsViewModel extends AndroidViewModel {

    private final Application application;
    public MutableLiveData<List<Favs>> listaFavs = new MutableLiveData<>();
    MutableLiveData<Favs> elementoSeleccionado = new MutableLiveData<>();


    public FavsViewModel(@NonNull Application application) {
        super(application);
        rellenarListaPlaylist();
        this.application = application;
    }



    public void rellenarListaPlaylist(){
        List<Favs> elementos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Favs favs = new Favs("Favorito " + i);
            elementos.add(favs);
        }
        listaFavs.setValue(elementos);
    }

    public void establecerElementoSeleccionado(Favs elemento){
        elementoSeleccionado.setValue(elemento);
    }
}
