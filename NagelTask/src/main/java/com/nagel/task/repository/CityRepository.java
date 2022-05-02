package com.nagel.task.repository;

import com.nagel.task.model.City;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    @Query("from City a where a.id between :range1 and :range2")
    public List<City> citiesByRange(@Param("range1") final Long range1, @Param("range2") final Long range2);

    @Query("from City a where a.name = :name")
    public List<City> retrieveCity(@Param("name") final String name);

}
