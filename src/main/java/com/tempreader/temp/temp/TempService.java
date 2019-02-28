package com.tempreader.temp.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TempService {
//    private Temp testTemp = new Temp(1, 30.12f, 22.2f, "27.02.19 15.11.44");
//    private List<Temp> temps = new ArrayList<>(Arrays.asList(
//            new Temp(1, 30.12f, 22.2f, "27.02.19 15.11.44"),
//            new Temp(2, 32.12f, 23.2f, "27.02.19 15.15.41"),
//            new Temp(3, 31.12f, 21.2f, "27.02.19 15.18.01")
//    ));

    @Autowired
    private TempRepository tempRepository;

    public List<Temp> getTemps() {
        return tempRepository.findAll();
    }

    public Temp getTemp(long searchId) {
        return tempRepository.findById(searchId);
    }

    public void updateTemp(Temp temp) {
        tempRepository.save(temp);
    }

    public void addTemp(Temp temp) {
        temp.setId(0); //if id is sent, with post, remove it and use incremented id
        tempRepository.save(temp);
    }

    public List<Temp> getTempsByMonthAndYear(String Month, String Year) {
        String searchTerm = String.format(".%s.%s", Month, Year);
        return tempRepository.findAllByDateContainingIgnoreCase(searchTerm);


    }

    public List<Temp> getTempsByDayMonthAndYear(String day, String Month, String Year) {
        String searchTerm = String.format("%s.%s.%s", day, Month, Year);
        return tempRepository.findAllByDateContainingIgnoreCase(searchTerm);


    }

    public List<Temp> getTempsByYear(String year) {
        String searchTerm = String.format(".%s ", year);
        return tempRepository.findAllByDateContainingIgnoreCase(searchTerm);
    }

    public List<Temp> getTempsList(String day, String month, String year, String hour) {
        //TODO improve
        if (isNullOrEmpty(day) && isNullOrEmpty(month)) {

            return tempRepository.findAllByDateContainingIgnoreCase(String.format(".%s ", year));
        } else if (isNullOrEmpty(day)) {

            return tempRepository.findAllByDateContainingIgnoreCase(String.format(".%s.%s", month, year));
        } else if (!isNullOrEmpty(day) && !isNullOrEmpty(month) && !isNullOrEmpty(year) && !isNullOrEmpty(hour)) {

            return tempRepository.findAllByDateContainingIgnoreCase(String.format("%s.%s.%s %s:", day, month, year, hour));

        } else return tempRepository.findAllByDateContainingIgnoreCase(String.format("%s.%s.%s ", day, month, year));
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}