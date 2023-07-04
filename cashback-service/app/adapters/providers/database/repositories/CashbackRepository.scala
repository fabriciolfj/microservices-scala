package adapters.providers.database.repositories

import adapters.providers.database.converters.CashbackDocumentMapper
import adapters.providers.database.data.CashbackData
import com.google.inject.Inject
import config.MongoConnection
import entities.Cashback
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.Sorts.descending
import org.slf4j.LoggerFactory
import play.api.libs.json.{JsError, JsSuccess, Json}

import javax.inject.Singleton
import scala.concurrent.Future
import scala.concurrent.ExecutionContext

@Singleton
class CashbackRepository @Inject()(connection: MongoConnection) {

  val logger = LoggerFactory.getLogger(getClass)
  val COLLECTION = "cashback"

  def save(entity: Cashback, customer: String)(implicit ec: ExecutionContext) : Unit = {
    val collection = connection.getConnection(COLLECTION)

    val doc = CashbackDocumentMapper.toDocumentCashback(entity, customer)
    collection.insertOne(doc)
      .toFuture()
      .map { _ =>
        logger.info("cashback save success " + entity.code)
      }
  }

  def query(customer: String)(implicit ec: ExecutionContext) : Future[Option[CashbackData]] = {
    val collection = connection.getConnection(COLLECTION)
    val query = equal("customer", customer)

    collection.find(query)
      .sort(descending("date"))
      .limit(1)
      .headOption()
      .flatMap {
        case Some(document) =>
          val json = Json.parse(document.toJson())
          Json.fromJson[CashbackData](json) match {
            case JsSuccess(data, _) => Future.successful(Some(data))
            case JsError(errors) =>
              println(s"Erro ao converter JSON: $errors")
              Future.successful(None)
          }
        case None => Future.successful(None)
      }

  }

}
