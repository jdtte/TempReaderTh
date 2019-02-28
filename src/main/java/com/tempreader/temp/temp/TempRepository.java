package com.tempreader.temp.temp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List; //needed?

public interface TempRepository extends JpaRepository<Temp, Long> {
    List<Temp> findByDateContainingIgnoreCase(String searchDate);

}
