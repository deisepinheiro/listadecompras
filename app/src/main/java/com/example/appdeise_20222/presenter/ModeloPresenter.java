package com.example.appdeise_20222.presenter;



import com.example.appdeise_20222.model.ListaComItens;
import com.example.appdeise_20222.room.AppDatabase;

import java.util.List;

public class ModeloPresenter  implements ModeloPresenterContract.presenter {

    private ModeloPresenterContract.view activity;
    private  AppDatabase db;
    public ModeloPresenter(ModeloPresenterContract.view activity) {
        this.activity = activity;
        db = AppDatabase.getInstance(activity.getActivity());
    }

    @Override
    public void setDataInRecicleView() {

        List<ListaComItens> modelos = db.listaDao().getListaComItensByStatus(false);
        if(modelos.isEmpty())
            activity.showMessage("Nenhum modelo criado!");

        activity.setRecicleView(modelos);
    }

    @Override
    public void getListaComItens(boolean status, String search) {
        List<ListaComItens> modelos = db.listaDao().getListaComItensbyStatusAndDescription(status,search);
        activity.setRecicleView(modelos);
    }
}
