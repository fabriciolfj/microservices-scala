package config

import com.google.inject.{AbstractModule, Provides}
import org.mongodb.scala.{MongoClient, MongoDatabase}
import play.api.Configuration


class MongoConfiguration extends AbstractModule {

  @Provides
  def mongoClient(config: Configuration): MongoClient = {
    val mongoUri = config.get[String]("mongo.uri")
    MongoClient(mongoUri)
  }

  @Provides
  def mongoDatabase(mongoClient: MongoClient, config: Configuration): MongoDatabase = {
    val databaseName = config.get[String]("mongo.database")
    mongoClient.getDatabase(databaseName)
  }
}
