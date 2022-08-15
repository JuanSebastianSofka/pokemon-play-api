package dominio.servicios

import dominio.modelo.Pokemon
import infraestructura.basededatos.ListaPokemon
import play.api.mvc.Results.InternalServerError

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait EliminarPokemon {
  def eliminarPokemons(id: String): Future[String] = Future {
    val pokemonExiste = ListaPokemon.listaPokemon.filter(pokemon => pokemon.id == id)

    if(pokemonExiste.isEmpty){
      throw new Exception
    }else{
      ListaPokemon.listaPokemon = ListaPokemon.listaPokemon.filter(pokemon => pokemon.id != id)
      id
    }
  }
}

object EliminarPokemon extends EliminarPokemon
