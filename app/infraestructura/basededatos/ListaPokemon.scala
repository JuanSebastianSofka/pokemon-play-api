package infraestructura.basededatos

import dominio.modelo._

object ListaPokemon {
  var listaPokemon: List[Pokemon] = List(
    Pokemon("1", "Pikachu", Electrico()),
    Pokemon("2", "Cyndaquil", Fuego()),
    Pokemon("3", "Squirtle", Agua()),
    Pokemon("4", "Pidgeot", Viento()),
    Pokemon("5", "Charizard", Dragon()),
  )
}
