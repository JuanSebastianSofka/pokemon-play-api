package dominio.servicios

import dominio.modelo._
import org.scalatest.funsuite.AsyncFunSuite
import org.scalatest.matchers.must.Matchers

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class ActualizarPokemonTest extends AsyncFunSuite with Matchers {
  test("Test para actualizar un pokemon"){
    Await.result(ActualizarPokemon.actualizarPokemon(Pokemon("1","Charmeeleon", Fuego())), Duration.Inf)

    val listOfPokemonWithPokemonUpdated = Await.result(ObtenerPokemon.mostrarTodosLosPokemon(), Duration.Inf)

    listOfPokemonWithPokemonUpdated mustBe Some(List(
      Pokemon("1","Charmeeleon", Fuego()),
      Pokemon("2", "Cyndaquil", Fuego()),
      Pokemon("3", "Squirtle", Agua()),
      Pokemon("4", "Pidgeot", Viento()),
      Pokemon("5", "Charizard", Dragon())
      ))
  }
}
