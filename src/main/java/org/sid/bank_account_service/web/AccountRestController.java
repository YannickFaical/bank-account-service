package org.sid.bank_account_service.web;

import jakarta.persistence.Entity;
import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
//@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private BankAccountService accountService;
   public AccountRestController(BankAccountRepository bankAccountRepository, BankAccountService bankAccountService) {
       this.bankAccountRepository = bankAccountRepository;
       this.accountService = bankAccountService;
   }

   @GetMapping("/bankAccounts")
       public List<BankAccount> bankAccounts(){
           return bankAccountRepository.findAll();
       }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
       return  bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
      return accountService.addAccount(requestDTO);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@RequestBody BankAccount bankAccount ,@PathVariable String id ){

       BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getType()!=null)  account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null)  account.setCurrency(bankAccount.getCurrency());
        if(bankAccount.getCreateAt()!=null) account.setCreateAt(new Date());

        return bankAccountRepository.save(account);

    }
    @DeleteMapping("/bankAcounts/{id}")
    public void delete(@PathVariable String id){
       bankAccountRepository.deleteById(id);
    }




   }


