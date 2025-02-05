package org.sid.bank_account_service.web;

import jakarta.persistence.Entity;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
   public AccountRestController(BankAccountRepository bankAccountRepository) {
       this.bankAccountRepository = bankAccountRepository;
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
    public BankAccount save(@RequestBody BankAccount bankAccount){
      if (bankAccount.getId()==null)bankAccount.setId((UUID.randomUUID().toString()));
      if (bankAccount.getCreateAt()==null) bankAccount.setCreateAt(new Date());

      return bankAccountRepository.save(bankAccount);

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


