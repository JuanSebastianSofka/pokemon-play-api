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
      pokemonOpt => pokemonOpt.map(f = pokemon => {
        val pokemonDto: PokemonDTO = pokemon //implicitamente hace la conversion
        val json = Json.toJson(pokemonDto)
        Ok(json)
      }).getOrElse(NotFound(s"No existe pokemon con el id $id"))
    ).recover{
      case ex => InternalServerError(s"Ocurrio un error interno: ${ex.getMessage}")
    }
  }

}
