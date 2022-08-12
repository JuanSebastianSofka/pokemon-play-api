package dominio.servicios

import dominio.modelo.Pokemon
import infraestructura.basededatos.ListaPokemon

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ObtenerPokemon {

  def obtenerPokemon(id: String) = Future {
    ListaPokemon.listaPokemon.find(pokemon => pokemon.id == id)
  }
}

object ObtenerPokemon extends ObtenerPokemon
