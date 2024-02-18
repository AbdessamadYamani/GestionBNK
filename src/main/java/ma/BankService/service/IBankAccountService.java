package ma.BankService.service;

import ma.BankService.dtos.bankaccount.AddBankAccountRequest;
import ma.BankService.dtos.bankaccount.AddBankAccountResponse;
import ma.BankService.dtos.bankaccount.BankAccountDto;

import java.util.List;

public interface IBankAccountService {
    AddBankAccountResponse saveBankAccount(AddBankAccountRequest dto);

    List<BankAccountDto> getAllBankAccounts();

    BankAccountDto getBankAccountByRib(String rib);
}
