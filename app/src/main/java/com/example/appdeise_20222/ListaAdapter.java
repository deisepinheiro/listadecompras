package com.example.appdeise_20222;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdeise_20222.dados.ItemLista;
import com.example.appdeise_20222.databinding.LineItemBinding;

import java.util.ArrayList;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ListaViewHolder> {

    private Context context;
    private ArrayList<ItemLista> itens;

    public ListaAdapter(Context context, ArrayList<ItemLista> itens) {
        this.context = context;
        this.itens = itens;
    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.line_item, parent, false);
        ListaViewHolder viewHolder = new ListaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaViewHolder holder, int position) {
        ItemLista item = itens.get(position);
        holder.binding.setItem(item);
        holder.binding.executePendingBindings();


        //aumentar quantidade
        holder.binding.btMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.addQuantidade();
                holder.binding.etQt.setText(item.getQuantidade().toString());
                holder.binding.etSubtotal.setText(item.getSubtotal().toString());
            }
        });

        holder.binding.btLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.diminuiQuantidade();
                holder.binding.etQt.setText(item.getQuantidade().toString());
                holder.binding.etSubtotal.setText(item.getSubtotal().toString());
            }
        });

        holder.binding.edtPreco.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    item.setPreco(Double.parseDouble(s.toString()));
                    holder.binding.etSubtotal.setText(item.getSubtotal().toString());
                }catch (Exception ex){
                    Log.e("ERRO","ERRO");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder {

        final LineItemBinding binding;

        public ListaViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = LineItemBinding.bind(itemView);
        }
    }

}
