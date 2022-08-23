package dominio.servicios

import dominio.modelo._
import org.scalatest.funsuite.AsyncFunSuite
import org.scalatest.matchers.must.{Matchers => MatchMust}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class CrearPokemonTest extends AsyncFunSuite with MatchMust  {
  test("Test para crear un pokemon"){
    Await.result(CrearPokemon.crearPokemones(Pokemon("12","Nuevo pokemon", Electrico())), 500 millis)

    val listOfPokemonWithNewPokemon = Await.result(ObtenerPokemon.mostrarTodosLosPokemon(), 500 millis)

    listOfPokemonWithNewPokemon mustBe Some(List(
      Pokemon("1", "Pikachu", Electrico()),
      Pokemon("2", "Cyndaquil", Fuego()),
      Pokemon("3", "Squirtle", Agua()),
      Pokemon("4", "Pidgeot", Viento()),
      Pokemon("5", "Charizard", Dragon()),
      Pokemon("12","Nuevo pokemon",Electrico()
    )))
  }
}
