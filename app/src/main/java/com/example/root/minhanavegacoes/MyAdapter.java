package com.example.root.minhanavegacoes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public static ClickRecycler clickRecycler;
    Context contexto;
    private List<Pessoa> listaPessoas;
    public MyAdapter(Context ctx, List<Pessoa> list, ClickRecycler clickRecycler){
        this.contexto = ctx;
        this.listaPessoas = list;
        this.clickRecycler = clickRecycler;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_itens_recycler,
                viewGroup, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder,final int position){
        Pessoa pessoa = listaPessoas.get(position);
        viewHolder.viewNomeCidade.setText(pessoa.getNome() + " " + pessoa.getCidade());
        viewHolder.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                removeItem(position);
            }
        });
    }
    private void removeItem(int position) {
        listaPessoas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listaPessoas.size(
        ));
    }
    @Override
    public int getItemCount() {
        return listaPessoas.size();
    }
    protected class MyViewHolder extends RecyclerView.ViewHolder{
        protected TextView viewNomeCidade;
        protected ImageButton delete;
        public MyViewHolder(final View itemView){
            super(itemView);
            viewNomeCidade = (TextView) itemView.findViewById(R.id.line_nome_cidade);
            delete = (ImageButton) itemView.findViewById(R.id.line_delete);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    clickRecycler.onCustomClick(listaPessoas.get(getLayoutPosition()));
                }
            });
        }
    }
}