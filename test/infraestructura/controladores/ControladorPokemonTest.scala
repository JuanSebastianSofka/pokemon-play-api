package infraestructura.controladores

import akka.util.ByteString
import infraestructura.controladores.dto.PokemonDTO
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.{JsString, JsValue, Json}
import play.api.libs.streams.Accumulator
import play.api.mvc.{Result, Results}
import play.api.test._
import play.api.test.Helpers._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class ControladorPokemonTest extends PlaySpec with Results {
  "Test de todos los pokemones" should {
    "should be valid" in {
      val controller = new ControladorPokemon(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.obtenerTodosLosPokemon().apply(FakeRequest())

      val body: String = contentAsString(result)

      status(result) mustBe (OK)
      body mustBe "[{\"id\":\"1\",\"nombre\":\"Pikachu\",\"tipo\":\"Electrico()\"},{\"id\":\"2\",\"nombre\":\"Cyndaquil\",\"tipo\":\"Fuego()\"},{\"id\":\"3\",\"nombre\":\"Squirtle\",\"tipo\":\"Agua()\"},{\"id\":\"4\",\"nombre\":\"Pidgeot\",\"tipo\":\"Viento()\"},{\"id\":\"5\",\"nombre\":\"Charizard\",\"tipo\":\"Dragon()\"}]"
    }
  }

  "Test de un solo pokemon" should {
    "should be valid" in {
      val controller = new ControladorPokemon(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.obtenerPokemon("1").apply(FakeRequest())

      val body: JsValue = contentAsJson(result)

      status(result) mustBe OK

      body mustBe Json.toJson(PokemonDTO("1","Pikachu","Electrico()"))
    }
  }

  "Test de eliminar fallido" should {
    "should be valid" in {
      val controller = new ControladorPokemon(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.eliminarPoke("6").apply(FakeRequest())
      status(result) mustBe INTERNAL_SERVER_ERROR
    }
  }

  "Test de eliminar exitoso" should {
    "should be valid" in {
      val controller = new ControladorPokemon(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.eliminarPoke("1").apply(FakeRequest())
      status(result) mustBe OK
    }
  }

  "Test de crear un pokemon exitoso" should {
    "should be valid" in {
      val controller = new ControladorPokemon(Helpers.stubControllerComponents())

      val request = FakeRequest().withBody(Json.obj("id"-> JsString("6"), "nombre" -> JsString("Otro pokemon"), "tipo" -> "Electrico()"))

      val result: Future[Result] = controller.crearPokemon().apply(request)

      val json: JsValue = contentAsJson(result)

      json mustBe Json.toJson(PokemonDTO("6","Otro pokemon","Electrico()"))
    }
  }

  "Test de crear un pokemon fallido" should {
    "should be valid" in {
      val controller = new ControladorPokemon(Helpers.stubControllerComponents())

      val request = FakeRequest().withBody(Json.obj("id"-> JsString("6"), "nombre" -> JsString("Otro pokemon"), "tipo" -> "Electrico"))

      val result: Future[Result] = controller.crearPokemon().apply(request)

      val response = status(result)

      response mustBe INTERNAL_SERVER_ERROR
    }
  }

  "Test actualizar un pokemon exitoso" should {
    "should be valid" in {
      val controller = new ControladorPokemon(Helpers.stubControllerComponents())

      val request = FakeRequest().withBody(Json.obj("id"-> JsString("1"), "nombre" -> JsString("Otro pokemon"), "tipo" -> "Electrico()"))

      val result: Future[Result] = controller.actualizarPoke().apply(request)

      val json: JsValue = contentAsJson(result)

      json mustBe Json.toJson(PokemonDTO("1","Otro pokemon","Electrico()"))
    }
  }

  "Test actualizar un pokemon id erroneo" should {
    "should be valid" in {
      val controller = new ControladorPokemon(Helpers.stubControllerComponents())

      val request = FakeRequest().withBody(Json.obj("id"-> JsString("6"), "nombre" -> JsString("Otro pokemon"), "tipo" -> "Electrico()"))

      val result: Future[Result] = controller.actualizarPoke().apply(request)

      val error = status(result)

      error mustBe INTERNAL_SERVER_ERROR
    }
  }

  "Test actualizar un pokemon id correcto, tipo errado" should {
    "should be valid" in {
      val controller = new ControladorPokemon(Helpers.stubControllerComponents())

      val request = FakeRequest().withBody(Json.obj("id"-> JsString("1"), "nombre" -> JsString("Otro pokemon"), "tipo" -> "Electrico"))

      val result: Future[Result] = controller.actualizarPoke().apply(request)

      val error = status(result)

      error mustBe INTERNAL_SERVER_ERROR
    }
  }
}
