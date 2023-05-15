package config

import org.flywaydb.core.Flyway

object FlywayConfig {
  def main(args: Array[String]): Unit = {
    val flyway = Flyway.configure()
      .dataSource("jdbc:postgresql://localhost:5432/postgres", "root", "root")
      .locations("classpath:db/migration")
      .load()

    flyway.migrate()

    println("Flyway migration completed.")
  }
}
