package com.example.appdeise_20222;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.appdeise_20222.dados.AppDatabase;
import com.example.appdeise_20222.dados.Lista;
import com.example.appdeise_20222.dados.ListaComItens;
import com.example.appdeise_20222.databinding.ModelItemBinding;

import java.util.List;

public class ModeloAdapter extends RecyclerView.Adapter<ModeloAdapter.ModeloViewHolder> {

    private Context context;
    private List<ListaComItens> modelos;
    private AppDatabase db;

    public ModeloAdapter(Context context, List<ListaComItens> modelos) {
        this.context = context;
        this.modelos = modelos;
        this.db =
                Room.databaseBuilder(context,
                                AppDatabase.class, "lista_compras")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
    }

    @NonNull
    @Override
    public ModeloAdapter.ModeloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_item, parent, false);
        ModeloViewHolder viewHolder = new ModeloAdapter.ModeloViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ModeloAdapter.ModeloViewHolder holder, int position) {
        ListaComItens minhalista = modelos.get(position);
        holder.binding.setModelo(minhalista);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListaActivity.class);
                intent.putExtra("id_modelo",minhalista.lista.getId());
                context.startActivity(intent);
            }
        });

        holder.binding.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.confirma_exclusão);
                builder.setPositiveButton(R.string.confirma, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.listaDao().deleteLista(minhalista.lista);
                        modelos.remove(minhalista);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelos.size();
    }

    public class ModeloViewHolder extends RecyclerView.ViewHolder {

        final ModelItemBinding binding;

        public ModeloViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ModelItemBinding.bind(itemView);
        }
    }
}