package com.example.repository;

import com.example.model.EntityClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EntRepository extends JpaRepository<EntityClass, Integer> {

    EntityClass findByName(String name);

    EntityClass findByAge(int age);
}
