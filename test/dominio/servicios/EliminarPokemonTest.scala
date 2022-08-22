package dominio.servicios

import dominio.modelo.{Agua, Dragon, Electrico, Fuego, Pokemon, Viento}
import org.scalatest.funsuite.AsyncFunSuite
import org.scalatest.matchers.must.{Matchers => MatchMust}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class EliminarPokemonTest extends AsyncFunSuite with MatchMust {
  test("Eliminar pokemon de lista"){
    Await.result(EliminarPokemon.eliminarPokemons("1"), 500 millis)

    val listWithPokemonDeleted = Await.result(ObtenerPokemon.mostrarTodosLosPokemon(), 500 millis)

    listWithPokemonDeleted mustBe Some(List(
      Pokemon("2", "Cyndaquil", Fuego()),
      Pokemon("3", "Squirtle", Agua()),
      Pokemon("4", "Pidgeot", Viento()),
      Pokemon("5", "Charizard", Dragon()),
    ))
  }

  test("Eliminar pokemon error"){
    val exception = intercept[java.lang.Exception] (Await.result(EliminarPokemon.eliminarPokemons("6"), 500 millis))

    val listWithPokemonDeleted = Await.result(ObtenerPokemon.mostrarTodosLosPokemon(), 500 millis)

    listWithPokemonDeleted mustBe Some(List(
      Pokemon("1", "Pikachu", Electrico()),
      Pokemon("2", "Cyndaquil", Fuego()),
      Pokemon("3", "Squirtle", Agua()),
      Pokemon("4", "Pidgeot", Viento()),
      Pokemon("5", "Charizard", Dragon()),
    ))

     exception.getMessage mustBe "Hubo un error, el pokemon no existe"
  }
}
