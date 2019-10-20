package com.domain.service;

import com.domain.domain.Administrator;
import com.domain.exception.RecordNotFoundException;
import com.domain.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    AdministratorRepository repository;

    public List<Administrator> getAllAdministrators() {
        List<Administrator> result = (List<Administrator>) repository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Administrator>();
        }
    }

    public Administrator getAdministratorById(Long id) throws RecordNotFoundException {
        Optional<Administrator> administrator = repository.findById(id);

        if (administrator.isPresent()) {
            return administrator.get();
        } else {
            throw new RecordNotFoundException("No admin record exist for given id");
        }
    }

    public Administrator createOrUpdateAdministrator(Administrator entity)
    {
        if(entity.getId()  == null)
        {
            entity = repository.save(entity);

            return entity;
        }
        else
        {
            Optional<Administrator> administrator = repository.findById(entity.getId());

            if(administrator.isPresent())
            {
                Administrator newEntity = administrator.get();
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        }
    }

    public void deleteAdministratorById(Long id) throws RecordNotFoundException
    {
        Optional<Administrator> administrator = repository.findById(id);

        if(administrator.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No admin record exist for given id");
        }
    }
}
