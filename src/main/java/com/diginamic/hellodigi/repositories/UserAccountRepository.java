package com.diginamic.hellodigi.repositories;

import com.diginamic.hellodigi.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
  UserAccount findByUsername(String username);
}
