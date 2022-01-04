package models

import play.api.libs.json.{Json, OWrites, Reads}

case class DenseVector (vectorDimensions: Seq[String])
object DenseVector {
  implicit val DenseVectorReads: Reads[DenseVector] = Json.reads
  implicit val DenseVectorWrite: OWrites[DenseVector] = Json.writes
}