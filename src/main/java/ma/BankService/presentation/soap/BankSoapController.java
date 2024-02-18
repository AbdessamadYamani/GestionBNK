package ma.BankService.presentation.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import lombok.AllArgsConstructor;
import ma.BankService.common.CommonTools;
import ma.BankService.dtos.bankaccount.AddBankAccountRequest;
import ma.BankService.dtos.bankaccount.AddBankAccountResponse;
import ma.BankService.dtos.bankaccount.BankAccountDto;
import ma.BankService.dtos.customer.*;
import ma.BankService.dtos.transaction.AddWirerTransferRequest;
import ma.BankService.dtos.transaction.AddWirerTransferResponse;
import ma.BankService.dtos.transaction.GetTransactionListRequest;
import ma.BankService.dtos.transaction.TransactionDto;
import ma.BankService.service.IBankAccountService;
import ma.BankService.service.ICustomerService;
import ma.BankService.service.ITransactionService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@WebService(serviceName = "BankWS")
@SOAPBinding
@AllArgsConstructor
public class BankSoapController {

    private final IBankAccountService bankAccountService;
    private final ICustomerService customerService;
    private ITransactionService transactionService;
    private CommonTools commonTools;

    @WebMethod

    @WebResult(name = "Customer")
    public List<CustomerDto> customers() {
        return customerService.getAllCustomers();
    }


    @WebMethod

    @WebResult(name = "Customer")
    public CustomerDto customerByIdentity(@WebParam(name = "identity") String identity) {
        return customerService.getCustomByIdentity(identity);
    }

    @WebMethod
    @WebResult(name = "Customer")
    public AddCustomerResponse createCustomer(@WebParam(name = "Customer") AddCustomerRequest dto) {
        return customerService.createCustomer(dto);
    }

    @WebResult(name = "BankAccount")
    @WebMethod
    public List<BankAccountDto> bankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @WebMethod

    @WebResult(name = "BankAccount")
    public BankAccountDto bankAccountByRib(@WebParam(name = "rib") String rib) {
        return bankAccountService.getBankAccountByRib(rib);
    }

    @WebResult(name = "BankAccount")
    @WebMethod
    public AddBankAccountResponse createBankAccount(@WebParam(name = "bankAccountRequest") AddBankAccountRequest dto) {
        return bankAccountService.saveBankAccount(dto);
    }

    @WebResult(name = "Transaction")
    @WebMethod
    public AddWirerTransferResponse createWirerTransfer(@WebParam(name = "wirerTransferRequest") AddWirerTransferRequest dto) {
        return transactionService.wiredTransfer(dto);
    }

    @WebResult(name = "Transaction")
    @WebMethod
    public List<TransactionDto> getTransactions(@WebParam(name = "dto") GetTransactionListRequest dto) {
        return transactionService.getTransactions(dto);
    }

    @WebResult(name = "Customer")
    @WebMethod
    public UpdateCustomerResponse changeCustomer(@WebParam(name = "identityRef") String identityRef, @WebParam(name = "dto") UpdateCustomerRequest dto) {
        return customerService.updateCustomer(identityRef, dto);
    }

    @WebMethod
    public String deleteCustomer(@WebParam(name = "identityRef") String identityRef) {
        return customerService.deleteCustomerByIdentityRef(identityRef);
    }
}
