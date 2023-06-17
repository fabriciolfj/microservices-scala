package usecase.createcashback

import com.google.inject.{Inject, Singleton}
import entities.Cashback
import usecase.findcashback.GetLastRegistryCashbackProvider

@Singleton
class FindLastCashbackUsecase @Inject() (private val find: GetLastRegistryCashbackProvider) {

  def execute(code: String): Cashback = {
    val result = find.process(code) match {
      case Some(it) => it
      case None => throw new RuntimeException("customer not found")
    }

    result
  }

}
