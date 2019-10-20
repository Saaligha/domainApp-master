package com.domain.repository;

import com.domain.domain.Demographic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemographicRepository extends CrudRepository<Demographic, String> {
}
