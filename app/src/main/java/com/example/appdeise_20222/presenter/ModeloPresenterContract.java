package com.example.appdeise_20222.presenter;

import android.app.Activity;

import com.example.appdeise_20222.model.ListaComItens;

import java.util.List;

public class ModeloPresenterContract {

    public interface view {
        Activity getActivity();
        void showMessage(String msg);
        void setRecicleView(List<ListaComItens> modelos);

    }
    public interface presenter {
        void startListaActivity();
        void setDataInRecicleView();
        void getListaComItens(boolean b, String toString);
    }

}
