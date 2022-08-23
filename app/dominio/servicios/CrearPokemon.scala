package dominio.servicios

import dominio.modelo._
import infraestructura.basededatos.ListaPokemon

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait CrearPokemon {
  def crearPokemones(pokemon: Pokemon):Future[Pokemon] = Future{
    val existePokemon = ListaPokemon.listaPokemon.filter(poke => poke.id == pokemon.id)

    if(existePokemon.isEmpty){
      if(pokemon.tipo.toString == Fuego().toString || pokemon.tipo.toString == Electrico().toString || pokemon.tipo.toString == Agua().toString || pokemon.tipo.toString == Viento().toString || pokemon.tipo.toString == Dragon().toString){
        ListaPokemon.listaPokemon = ListaPokemon.listaPokemon.appended(pokemon) //actualizo el nuevo pokemon a la lista
        pokemon
      }else{
        null
      }
    }else{
      null
    }
  }
}

object CrearPokemon extends CrearPokemon
