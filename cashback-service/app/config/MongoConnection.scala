package config

import com.google.inject.Inject
import org.mongodb.scala.MongoClient
import play.api.Configuration

import javax.inject.Singleton

@Singleton
class MongoConnection @Inject()(mongoClient: MongoClient, config: Configuration) {

  def getConnection(collection: String) = mongoClient
    .getDatabase(config.get[String]("mongo.database"))
    .getCollection(collection)

}
