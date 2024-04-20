package com.diginamic.hellodigi.services;

import com.diginamic.hellodigi.businessmodel.UserRole;
import com.diginamic.hellodigi.entities.UserAccount;
import com.diginamic.hellodigi.repositories.CityRepository;
import com.diginamic.hellodigi.repositories.DepartmentRepository;
import com.diginamic.hellodigi.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeederService implements CommandLineRunner {

  private final CityRepository cityRepository;
  private final DepartmentRepository departmentRepository;
  private final UserAccountRepository accountRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public SeederService(CityRepository cityRepository, DepartmentRepository departmentRepository, UserAccountRepository accountRepository) {
    this.cityRepository = cityRepository;
    this.departmentRepository = departmentRepository;
    this.accountRepository = accountRepository;
  }

  @Override
  public void run(String... args) throws Exception {
//    var cities = Cities.create();
//    cities.forEach(city -> {
//      var currentDepartment = departmentRepository.findByName(city.departmentName());
//
//      DepartmentEntity departmentEntity = currentDepartment.orElseGet(() -> departmentRepository.save(
//          new DepartmentEntity()
//              .setName(city.departmentName())
//              .setCode(city.departmentCode())
//      ));
//
//      var entity = new CityEntity()
//          .setName(city.cityName())
//          .setPopulation(0)
//          .setDepartment(departmentEntity);
//
//      cityRepository.save(entity);
//    });

//    System.out.println("Finish");


    try {
      seedUserAccount();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void seedUserAccount() {
    var admin = new UserAccount();
    admin.setUsername("admin");
    admin.setPassword(passwordEncoder.encode("admin"));
    admin.setRole(UserRole.ADMIN);


    var user = new UserAccount();
    user.setUsername("user");
    user.setPassword(passwordEncoder.encode("user"));
    user.setRole(UserRole.USER);

    accountRepository.saveAll(List.of(admin, user));
  }


}
