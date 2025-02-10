package org.sid.bank_account_service.repositories;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


//@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {

//    @RestResource(path="byType")
//    List<BankAccount> findByType(@PathVariable("y") AccountType type);

}
