package com.example.pokeapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PokeApiAdapter( var pokemonlist: List<PokemonResults> = emptyList(),
                        private val onItemSelected: (String) -> Unit):
    RecyclerView.Adapter<PokeApiViewHolder>(){

    fun updateList(pokemonlist: List<PokemonResults>){
        this.pokemonlist = pokemonlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeApiViewHolder {
        return(PokeApiViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pokemonlist, parent, false)))
    }

    override fun getItemCount(): Int {
        return pokemonlist.size
    }

    override fun onBindViewHolder(holder: PokeApiViewHolder, position: Int) {
        holder.bind(pokemonlist[position], onItemSelected)
    }
}
