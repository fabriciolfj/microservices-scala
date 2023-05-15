package usecase.createcashback

import entities.{Customer}
import usecase.findcashback.GetLastRegistryCashbackProvider

import javax.inject.{Inject, Singleton}

@Singleton
class CreateCashbackUsecase @Inject()(private val saveCashbackProvider: SaveCashbackProvider,
                                      private val find: GetLastRegistryCashbackProvider) {

  def execute(customer: Customer): Unit = {

  }

}
