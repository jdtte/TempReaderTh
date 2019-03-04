package com.tempreader.temp.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class TempService {

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

    public List<Temp> getTempsList(String day, String month, String year) {
        //TODO improve
        if (isNullOrEmpty(day) && isNullOrEmpty(month)) {
            return tempRepository.findAllByDateContainingIgnoreCase(String.format(".%s ", year));

        } else if (isNullOrEmpty(day) && !isNullOrEmpty(month)) {
            return tempRepository.findAllByDateContainingIgnoreCase(String.format(".%s.%s", month, year));

        } else return tempRepository.findAllByDateContainingIgnoreCase(String.format("%s.%s.%s ", day, month, year));
    }


    /**
     * Creates Temp in Database, used for Test. Data does not get checked.
     *
     * @param temp
     */
    public void createTemp(Temp temp) {
        tempRepository.save(temp);
    }

    public Temp getLastTempEntry() {
        Temp retTemp = tempRepository.findFirstByOrderByIdDesc();
        String s = retTemp.getDate();
        Pattern pattern = Pattern.compile("\\.\\d{2}\\s");

        s = s.replaceAll("\\.\\d{2}\\s", ". ");
        s = s.replaceAll("\\:\\d{2}$", "");
        retTemp.setDate(s);

//        s = Pattern.matches( "\\.\\d{2}\\s", "Hallo Welt");
        return retTemp;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}