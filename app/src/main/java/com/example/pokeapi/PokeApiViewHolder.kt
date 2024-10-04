package com.example.pokeapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.databinding.ItemPokemonlistBinding
import com.squareup.picasso.Picasso

class PokeApiViewHolder (view: View) : RecyclerView.ViewHolder(view)  {

    private val binding = ItemPokemonlistBinding.bind(view)

    fun bind(pokemonResults: PokemonResults, onItemSelected: (String) -> Unit) {
        binding.tvPokemonName.text = pokemonResults.name
        //Picasso.get().load(pokemonResults.image.url).into(binding.ivPokemon)
        binding.root.setOnClickListener {
            onItemSelected(pokemonResults.name)
        }
    }
}