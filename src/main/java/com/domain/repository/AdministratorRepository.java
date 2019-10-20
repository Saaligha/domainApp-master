package com.domain.repository;

import com.domain.domain.Administrator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository 	extends CrudRepository<Administrator, Long> {
}
