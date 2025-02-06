package org.sid.bank_account_service.service;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO) {

        // mapping des dto vers l'entité
        BankAccount bankAccount=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createAt(new Date())
                .balance(bankAccountRequestDTO.getBalance())
                .type(bankAccountRequestDTO.getType())
                .currency(bankAccountRequestDTO.getCurrency())
                .build();
      BankAccount savedBankAccount=  bankAccountRepository.save(bankAccount);


      // transferer les données (operation inverse)
      BankAccountResponseDTO bankAccountResponseDTO=BankAccountResponseDTO.builder()
              .id(savedBankAccount.getId())
              .createAt(savedBankAccount.getCreateAt())
              .balance(savedBankAccount.getBalance())
              .type(savedBankAccount.getType())
              .currency(savedBankAccount.getCurrency())
              .build();

      return bankAccountResponseDTO;

    }
}
