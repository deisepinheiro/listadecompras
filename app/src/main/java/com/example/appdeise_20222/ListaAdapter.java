package com.example.appdeise_20222;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.appdeise_20222.dados.AppDatabase;
import com.example.appdeise_20222.dados.ItemLista;
import com.example.appdeise_20222.databinding.LineItem0Binding;

import java.util.ArrayList;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ListaViewHolder> {

    private Context context;
    private ArrayList<ItemLista> itens;
    private  AppDatabase db;
    private ReturnTotal returnTotal;

    public ListaAdapter(Context context, ArrayList<ItemLista> itens, ReturnTotal returnTotal) {
        this.context = context;
        this.itens = itens;
        this.returnTotal = returnTotal;
        this.db =
                Room.databaseBuilder(context,
                                AppDatabase.class, "lista_compras")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.line_item0, parent, false);
        ListaViewHolder viewHolder = new ListaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaViewHolder holder, int position) {
        ItemLista item = itens.get(position);
        holder.binding.setItem(item);
        holder.binding.etSubtotal.setText(item.getSubtotalFormatado());
        holder.binding.executePendingBindings();


        //aumentar quantidade
        holder.binding.btMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.addQuantidade();
                holder.binding.etQt.setText(item.getQuantidade().toString());
                holder.binding.etSubtotal.setText(item.getSubtotalFormatado());
                db.itemListaDao().update(item);
                returnTotal.atualiza();
            }
        });

        holder.binding.btLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.diminuiQuantidade();
                holder.binding.etQt.setText(item.getQuantidade().toString());
                holder.binding.etSubtotal.setText(item.getSubtotalFormatado());
                db.itemListaDao().update(item);
                returnTotal.atualiza();
            }
        });

        holder.binding.checkBoxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    holder.binding.tvDescription.setPaintFlags(holder.binding.tvDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.binding.tvDescription.setTypeface(null, Typeface.ITALIC);
                }else{
                    holder.binding.tvDescription.setPaintFlags(0);
                    holder.binding.tvDescription.setTypeface(null, Typeface.NORMAL);
                }
                item.setCarrinho(isChecked);
                returnTotal.atualiza();

            }
        });

        holder.binding.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itens.remove(item);
                db.itemListaDao().delete(item);
                notifyDataSetChanged();
                returnTotal.atualiza();
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
                    db.itemListaDao().update(item);
                    holder.binding.etSubtotal.setText(item.getSubtotalFormatado());
                    returnTotal.atualiza();
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

        final LineItem0Binding binding;

        public ListaViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = LineItem0Binding.bind(itemView);
        }
    }

}
