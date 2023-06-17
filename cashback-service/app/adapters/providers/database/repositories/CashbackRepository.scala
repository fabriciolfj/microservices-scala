package adapters.providers.database.repositories

import adapters.providers.database.data.CashbackData
import slick.dbio.{DBIO, Effect, NoStream}
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.SQLActionBuilder
import slick.sql.FixedSqlAction
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject.Singleton
import scala.concurrent.Future
import scala.util.{Failure, Success}

@Singleton
class CashbackRepository {

  def save(action: FixedSqlAction[Int, NoStream, Effect.Write]) : Unit = {
    val db = Database.forConfig("slick.db")
    val insertFuture = db.run {
        for {
          _ <- DBIO.seq(sqlu"START TRANSACTION")
          result <- action
          _ <- DBIO.seq(sqlu"COMMIT")
        } yield result
      }

    insertFuture.onComplete {
      case Success(_) => println("Dados do Cashback salvos com sucesso!")
      case Failure(ex) => println(s"Erro ao salvar dados do Cashback: ${ex.getMessage}")
    }
  }

  def query(sql: SQLActionBuilder) : Future[Option[CashbackData]] = {
    val db = Database.forConfig("slick.db")
    db.run(sql.as[CashbackData].headOption)
  }

}
