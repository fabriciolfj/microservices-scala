package providers.repository

import entities.Transaction
import org.mongodb.scala.MongoClient
import org.slf4j.LoggerFactory
import play.api.Configuration
import providers.repository.mapper.DocumentMapper

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class TransactionRepository @Inject()(mongoClient: MongoClient, config: Configuration) {

  val logger = LoggerFactory.getLogger(getClass)

  def save(transaction: Transaction)(implicit ec: ExecutionContext): Unit = {
    val collection = mongoClient
        .getDatabase(config.get[String]("mongo.database"))
        .getCollection("transactions")

    val doc = DocumentMapper.toDocument(transaction)
    collection.insertOne(doc)
      .toFuture()
      .map{_ =>
        logger.info("transaction save success " + transaction.code)
      }
  }
}
