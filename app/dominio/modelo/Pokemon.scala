package dominio.modelo

trait TipoPoKemon

case class Electrico() extends TipoPoKemon
case class Fuego() extends TipoPoKemon
case class Agua() extends TipoPoKemon
case class Viento() extends TipoPoKemon
case class Dragon() extends TipoPoKemon

case class Pokemon (
  id: String,
  nombre: String,
  tipo: TipoPoKemon
)

