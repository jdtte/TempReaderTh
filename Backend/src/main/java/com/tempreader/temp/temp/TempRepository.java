package com.tempreader.temp.temp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List; //needed?

@Repository
public interface TempRepository extends JpaRepository<Temp, Long> {

    List<Temp> findAllByDateContainingIgnoreCase(String searchDate);


    Temp findById(long id);


    List<Temp> findAll();


    Temp findFirstByOrderByIdDesc();

    Temp findFirstByOrderByIdAsc();

    // List<Temp> findAllByOrderByIdDesc(); //dont use

    List<Temp> findTop43202ByOrderByIdDesc();

    List<Temp> findTop50ByOrderByIdDesc();

    @Query(value = "SELECT * FROM TEMP ORDER BY ID desc LIMIT :amount", nativeQuery = true)
        //works with h2 Db, may not work with other datasource
    List<Temp> findTempsByAmount(@Param("amount") int amount);
}
