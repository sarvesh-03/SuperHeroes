package com.example.superheroes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private ArrayList<superHero> SuperHeroes =new ArrayList<>();
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new CardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  CardViewHolder holder, int position) {
        superHero current=SuperHeroes.get(position);
        holder.name.setText(current.getName());
        if(current.getImages()!=null)
            if(current.getImages().get("sm")!=null)
        Picasso.get().load(current.getImages().get("sm").getAsString()).resize(200,200).centerCrop()
               .into(holder.image);
        else Log.v("ta",""+position);


    }
    public superHero getItem(int position){
        return SuperHeroes.get(position);
    }

    @Override
    public int getItemCount() {
        return SuperHeroes==null?0:SuperHeroes.size();
    }
    public void add(superHero a){
        SuperHeroes.add(a);
        notifyItemInserted(SuperHeroes.size()-1);
    }

    public void addAll(List<superHero> a){
        for (superHero hero :a){
            add(hero);
        }
    }

    public void remove(superHero hero){
        int position=SuperHeroes.indexOf(hero);
        if(position>-1){
            SuperHeroes.remove(position);
            notifyItemRemoved(position);
        }
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        private final TextView name;
        private ImageView image;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.Name);
            image=itemView.findViewById(R.id.HeroImage);
        }
    }
    public void clear(){
        while (getItemCount()>0){
            remove(getItem(0));
        }
    }

}
