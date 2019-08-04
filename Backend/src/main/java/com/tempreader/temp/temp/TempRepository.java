package com.tempreader.temp.temp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; //needed?

@Repository
public interface TempRepository extends JpaRepository<Temp, Long> {

    List<Temp> findAllByDateContainingIgnoreCase(String searchDate);


    Temp findById(long id);


    List<Temp> findAll();


    Temp findFirstByOrderByIdDesc();

    Temp findFirstByOrderByIdAsc();

    List<Temp> findAllByOrderByIdDesc();
}
