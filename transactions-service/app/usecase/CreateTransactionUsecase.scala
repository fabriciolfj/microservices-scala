package usecase

import entities.Transaction

import javax.inject.{Inject, Singleton}

@Singleton
class CreateTransactionUsecase @Inject()(saveTransactionProvider: SaveTransactionProvider, createCashbackProvider: CreateCashbackProvider) {

  def execute(transaction: Transaction) : Transaction = {
    saveTransactionProvider.process(transaction)
    createCashbackProvider.process(transaction)
    transaction
  }

}
