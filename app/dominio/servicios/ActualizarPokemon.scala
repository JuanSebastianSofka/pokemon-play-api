package dominio.servicios

import dominio.modelo.Pokemon
import infraestructura.basededatos.ListaPokemon

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ActualizarPokemon {
  def actualizarPokemon(pokemon: Pokemon): Future[Pokemon] = Future{
    val existePokemon = ListaPokemon.listaPokemon.filter(poke => poke.id == pokemon.id)

   /* if(existePokemon.isEmpty){
      if(pokemon.tipo.toString == Fuego().toString || pokemon.tipo.toString == Electrico().toString || pokemon.tipo.toString == Agua().toString || pokemon.tipo.toString == Viento().toString || pokemon.tipo.toString == Dragon().toString){
        ListaPokemon.listaPokemon = ListaPokemon.listaPokemon.appended(pokemon) //actualizo el nuevo pokemon a la lista
        pokemon
      }else{
        null
      }
    }else{
      ListaPokemon.listaPokemon = ListaPokemon.listaPokemon.map(poke => if(poke.id == pokemon.id) pokemon else poke )
      pokemon
    }*/

    //otra opción considerando que solo puedo usar los pokemones dentro de la lista y no añadir nuevos a partir de un put
    if(existePokemon.isEmpty){
      null
    }else{
      ListaPokemon.listaPokemon = ListaPokemon.listaPokemon.map(poke => if(poke.id == pokemon.id) pokemon else poke )
      pokemon
    }
  }
}

object ActualizarPokemon extends ActualizarPokemon
