package configuration

import com.google.inject.{AbstractModule, Provides}
import org.mongodb.scala.{MongoClient, MongoDatabase}
import play.api.Configuration

import javax.inject.Singleton

class MongoConfiguration(config: Configuration) extends AbstractModule {

  @Provides
  def mongoClient: MongoClient = {
    val mongoUri = config.get[String]("mongo.uri")
    MongoClient(mongoUri)
  }

  @Provides
  def mongoDatabase(mongoClient: MongoClient) : MongoDatabase = {
    val databaseName = config.get[String]("mongo.database")
    mongoClient.getDatabase(databaseName)
  }

}
