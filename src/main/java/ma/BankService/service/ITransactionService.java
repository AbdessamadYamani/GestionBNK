package ma.BankService.service;

import ma.BankService.dtos.transaction.AddWirerTransferRequest;
import ma.BankService.dtos.transaction.AddWirerTransferResponse;
import ma.BankService.dtos.transaction.GetTransactionListRequest;
import ma.BankService.dtos.transaction.TransactionDto;

import java.util.List;

public interface ITransactionService {
    AddWirerTransferResponse wiredTransfer(AddWirerTransferRequest dto);

    List<TransactionDto> getTransactions(GetTransactionListRequest dto);
}
