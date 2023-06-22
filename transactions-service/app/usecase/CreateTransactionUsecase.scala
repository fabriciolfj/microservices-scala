package usecase

import entities.Transaction

import javax.inject.{Inject, Singleton}

@Singleton
class CreateTransactionUsecase @Inject()(saveTransactionProvider: SaveTransactionProvider) {

  def execute(transaction: Transaction) : Unit = {
    saveTransactionProvider.process(transaction)
  }

}
