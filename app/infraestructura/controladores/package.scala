package infraestructura

import dominio.modelo.{Agua, Dragon, Electrico, Fuego, Pokemon, Viento}
import infraestructura.controladores.dto.PokemonDTO
import play.api.libs.json.Json

package object controladores {
  implicit val serializador = Json.format[PokemonDTO]

  //convierte de pokemon a DTO
  implicit def pokemonADto(pokemon: Pokemon): PokemonDTO = {
    PokemonDTO(pokemon.id, pokemon.nombre, pokemon.tipo.toString)
  }

  //convierte de DTO a pokemon
  implicit def DtoAPokemon(pokemonDTO: PokemonDTO): Pokemon = {
    pokemonDTO.tipo match {
      case "Electrico()" => Pokemon(pokemonDTO.id, pokemonDTO.nombre, Electrico())
      case "Fuego()" => Pokemon(pokemonDTO.id, pokemonDTO.nombre, Fuego())
      case "Agua()" => Pokemon(pokemonDTO.id, pokemonDTO.nombre, Agua())
      case "Viento()" => Pokemon(pokemonDTO.id, pokemonDTO.nombre, Viento())
      case "Dragon()" => Pokemon(pokemonDTO.id, pokemonDTO.nombre, Dragon())
    }
  }
}
