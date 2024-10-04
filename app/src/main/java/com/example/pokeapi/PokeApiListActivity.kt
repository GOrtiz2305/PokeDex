package com.example.pokeapi

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_ID
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi.databinding.ActivityPokeApiListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeApiListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokeApiListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: PokeApiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // View Binding
        binding = ActivityPokeApiListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI(){
        //Iniciamos el SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())

                return false
            }

            override fun onQueryTextChange(newText: String?) = false

        })

        adapter = PokeApiAdapter { pokemonId -> navigateToDetail(pokemonId) }
        binding.rvPokemon.layoutManager = LinearLayoutManager(this)
        binding.rvPokemon.adapter = adapter
    }

    private fun searchByName(query: String){
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<PokeApiDataResponse> = retrofit.create(ApiService::class.java).getPokemonByName(query)

            if(myResponse.isSuccessful){
                Log.i("Gaby", "Funciona :)")
                val response: PokeApiDataResponse? = myResponse.body()

                if(response != null){
                    Log.i("Gaby", response.toString())
                    runOnUiThread {
                        adapter.updateList(listOf(PokemonResults(response.name, response.sprites.frontDefault)))
                        binding.progressBar.isVisible = false
                    }
                }

            }else{
                Log.i("Gaby", "No funciona :(")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id:String){
        val intent = Intent(this, DetailPokeApiActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}