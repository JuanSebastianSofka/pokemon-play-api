package dominio.servicios

import infraestructura.basededatos.ListaPokemon

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait EliminarPokemon {
  def eliminarPokemons(id: String): Future[String] = Future {
    val pokemonExiste = ListaPokemon.listaPokemon.filter(pokemon => pokemon.id == id)

    if(pokemonExiste.isEmpty){
      throw new Exception("Hubo un error, el pokemon no existe")
    }else{
      ListaPokemon.listaPokemon = ListaPokemon.listaPokemon.filter(pokemon => pokemon.id != id)
      id
    }
  }
}

object EliminarPokemon extends EliminarPokemon
