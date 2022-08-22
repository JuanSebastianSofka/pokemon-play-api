package dominio.servicios

import dominio.modelo._
import org.scalatest.funsuite.AsyncFunSuite
import org.scalatest.matchers.must.{Matchers => MatchMust}
import org.scalatest.matchers.should.{Matchers => MatchShould}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps


class ObtenerPokemonTest extends AsyncFunSuite with MatchMust {
  test("Test para obtener todos los pokemones") {
    val result = Await.result(ObtenerPokemon.mostrarTodosLosPokemon(), 500 millis)
    result mustBe Some(List(
      Pokemon("1", "Pikachu", Electrico()),
      Pokemon("2", "Cyndaquil", Fuego()),
      Pokemon("3", "Squirtle", Agua()),
      Pokemon("4", "Pidgeot", Viento()),
      Pokemon("5", "Charizard", Dragon()),
    ))
  }

  test("Test para obtener un solo pokemon") {
    val result = Await.result(ObtenerPokemon.obtenerPokemon("1"), 500 millis)
    result mustBe Some(Pokemon("1", "Pikachu", Electrico()))
  }

  test("Test de error si no hay pokemon en la base de datos") {
    val result = Await.result(ObtenerPokemon.obtenerPokemon("6"), 500 millis)
    result mustBe None
  }
}

/*class ObtenerPokemonTest2 extends PlaySpec with MockitoSugar {

  "my test" should {
    "return a pokemon " in {
      val pokemonMock = mock[ObtenerPokemon]
      when(pokemonMock.mostrarTodosLosPokemon()).thenReturn(Future(Option(List(
        Pokemon("1", "Pikachu", Electrico()),
        Pokemon("2", "Cyndaquil", Fuego()),
        Pokemon("3", "Squirtle", Agua()),
        Pokemon("4", "Pidgeot", Viento()),
        Pokemon("5", "Charizard", Dragon()),
      ))))

      val myService = new ObtenerPokemon {
        override def mostrarTodosLosPokemon(): Future[Option[List[Pokemon]]] = pokemonMock.mostrarTodosLosPokemon()
      }

      val actual = myService.mostrarTodosLosPokemon()

      actual mustBe Future(Some(List(
        Pokemon("1", "Pikachu", Electrico()),
        Pokemon("2", "Cyndaquil", Fuego()),
        Pokemon("3", "Squirtle", Agua()),
        Pokemon("4", "Pidgeot", Viento()),
        Pokemon("5", "Charizard", Dragon()),
      )))

    }
  }
}*/
