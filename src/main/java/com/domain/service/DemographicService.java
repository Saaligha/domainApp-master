package com.domain.service;

import com.domain.domain.Demographic;
import com.domain.exception.RecordNotFoundException;
import com.domain.repository.DemographicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DemographicService {

    @Autowired
    DemographicRepository repository;

    public List<Demographic> getAllDemographics(){
        List<Demographic> result = (List<Demographic>) repository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Demographic>();
        }
    }
    public Demographic createOrUpdateEmployee(Demographic entity)
    {
        if(entity.getRace()  == null)
        {
            entity = repository.save(entity);

            return entity;
        }
        else
        {
            Optional<Demographic> demographic = repository.findById(entity.getRace());

            if(demographic.isPresent())
            {
                Demographic newEntity = demographic.get();
                newEntity.setRace(entity.getRace());
                newEntity.setGender(entity.getGender());


                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        }
    }

    public void deleteEmployeeById(String id) throws RecordNotFoundException
    {
        Optional<Demographic> demographic = repository.findById(id);

        if(demographic.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No demographic record exist for given id");
        }
    }
}
