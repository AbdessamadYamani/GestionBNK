package ma.BankService.service;


import lombok.AllArgsConstructor;
import ma.BankService.dao.BankAccountRepository;
import ma.BankService.dao.CustomerRepository;
import ma.BankService.dtos.bankaccount.AddBankAccountRequest;
import ma.BankService.dtos.bankaccount.AddBankAccountResponse;
import ma.BankService.dtos.bankaccount.BankAccountDto;
import ma.BankService.enums.AccountStatus;
import ma.BankService.service.exception.BusinessException;
import ma.BankService.service.model.BankAccount;
import ma.BankService.service.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements IBankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private ModelMapper modelMapper;


    @Override
    public AddBankAccountResponse saveBankAccount(AddBankAccountRequest dto) {
       // BankAccount bankAccount = modelMapper.map(dto, BankAccount.class);
        BankAccount bankAccount = convertDto(dto);
        Customer customerP = customerRepository.findByIdentityRef(bankAccount.getCustomer().getIdentityRef()).orElseThrow(
                () -> new BusinessException(String.format("No customer with the identity: %s exist", dto.getCustomerIdentityRef())));
        bankAccount.setAccountStatus(AccountStatus.OPENED);
        bankAccount.setCustomer(customerP);
        bankAccount.setCreatedAt(new Date());
        AddBankAccountResponse response = modelMapper.map(bankAccountRepository.save(bankAccount), AddBankAccountResponse.class);
        response.setMessage(String.format("RIB number [%s] for the customer [%s] has been successfully created", dto.getRib(), dto.getCustomerIdentityRef()));
        return response;
    }

    private BankAccount convertDto(AddBankAccountRequest dto) {
        return BankAccount.builder().
                rib(dto.getRib()).amount(dto.getAmount()).customer(Customer.builder().identityRef(dto.getCustomerIdentityRef()).build()).
                build();
    }

    @Override
    public List<BankAccountDto> getAllBankAccounts() {
        return bankAccountRepository.findAll().stream().
                map(bankAccount -> modelMapper.map(bankAccount, BankAccountDto.class)).
                collect(Collectors.toList());
    }

    @Override
    public BankAccountDto getBankAccountByRib(String rib) {
        return modelMapper.map(bankAccountRepository.findByRib(rib).orElseThrow(
                () -> new BusinessException(String.format("No Bank Account with rib [%s] exist", rib))), BankAccountDto.class);
    }
}
