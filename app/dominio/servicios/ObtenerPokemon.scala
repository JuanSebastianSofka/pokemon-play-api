package dominio.servicios

import dominio.modelo.Pokemon
import infraestructura.basededatos.ListaPokemon

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ObtenerPokemon {

  def obtenerPokemon(id: String): Future[Option[Pokemon]] = Future {
    ListaPokemon.listaPokemon.find(pokemon => pokemon.id == id)
  }

  def mostrarTodosLosPokemon(): Future[Option[List[Pokemon]]] = Future {
    if(ListaPokemon.listaPokemon.isEmpty){
      None
    }else{
      Some(ListaPokemon.listaPokemon)
    }
  }
}

object ObtenerPokemon extends ObtenerPokemon
