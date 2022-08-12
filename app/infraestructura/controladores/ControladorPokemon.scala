package infraestructura.controladores

import dominio.servicios.ObtenerPokemon
import infraestructura.controladores.dto.PokemonDTO
import play.api.libs.json.Json
import play.api.mvc.{BaseController, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ControladorPokemon @Inject()(val controllerComponents: ControllerComponents) extends BaseController{
  
  def obtenerPokemon(id:String) = Action.async{
    ObtenerPokemon.obtenerPokemon(id).map(
      pokemonOpt => pokemonOpt.map(pokemon => {
        //solo muestro el DTO
        val pokemonDto: PokemonDTO = pokemon //implicitamente hace la conversion
        val json = Json.toJson(pokemonDto)
        Ok(json)
      }).getOrElse(NotFound(s"No existe pokemon con el id $id"))
    ).recover{
      case ex => InternalServerError(s"Ocurrio un error interno: ${ex.getMessage}")
    }
  }

  def obtenerTodosLosPokemon() = Action.async {
    ObtenerPokemon.mostrarTodosLosPokemon().map(
      pokemonOpt => pokemonOpt.map(pokemon => {
        //muestro solo DTOS
        val listaPpokemonDTO: List[PokemonDTO] = pokemon
        val json = Json.toJson(listaPpokemonDTO)
        Ok(json)
      }).getOrElse(NotFound("No existe lista de pokemones"))
    ).recover {
      case ex => InternalServerError(s"Ocurrio un error interno: ${ex.getMessage}")
    }
  }

}
