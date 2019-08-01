package com.tempreader.temp.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


import java.util.List;

import java.util.stream.Collectors;

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

    public void addTemp(Temp temp) throws TempMissesInfoException, DateMissmatchException {

        if (temp.getHumidity() == 0 || temp.getTemperature() == 0 || (temp.getDate().isEmpty() || temp.getDate() == null)) {
            throw new TempMissesInfoException();
        }
        if (!temp.getDate().matches("^\\d{2}.\\d{2}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$")) {
            throw new DateMissmatchException();
        }
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
        //TODO; @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
        // If I use retTemp and edit it it gets saved to the database and readOnly didnt work


        Temp retTemp = tempRepository.findFirstByOrderByIdDesc();
        Temp retTempTest = new Temp();
        retTempTest.setDate(retTemp.getDate());
        retTempTest.setId(retTemp.getId());
        retTempTest.setHumidity(retTemp.getHumidity());
        retTempTest.setTemperature(retTemp.getTemperature());

        String s = retTempTest.getDate();
//        Pattern pattern = Pattern.compile("\\.\\d{2}\\s");
        //TODO maybe replace with stringutils for better performance?
        s = s.replaceAll("\\.\\d{2}\\s", ". ");
        s = s.replaceAll("\\:\\d{2}$", "");
        retTempTest.setDate(s);

        return retTempTest;
    }


    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Gets Temps in Temps from last temp -hours entered.
     * ex: Temps of last Hours: hours=1 last30Days =720
     *
     * @param hours hours for offset
     * @return List of Temps
     */
    public List<Temp> getTempsByLastHours(int hours) {
        //TODO: if there is not enough hours between start and offset(hours) it will not display anything BUG
        DateTimeFormatter tempFormat = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm:ss");

        Temp lTemp = tempRepository.findFirstByOrderByIdDesc();
        LocalDateTime start = LocalDateTime.parse(lTemp.getDate(), tempFormat);
        return tempRepository.findAllByOrderByIdDesc().stream()
                .filter(t -> ChronoUnit.HOURS.between(start, LocalDateTime.parse(t.getDate(), tempFormat)) >= -hours)
                .collect(Collectors.toList());
    }

    public String getAverageTempInDurationHours(int hours) {
        List<Temp> TempsListInHour = getTempsByLastHours(hours);
        double sum = TempsListInHour.stream()
                .mapToDouble(tempVal -> tempVal.getTemperature())
                .sum();

        //TODO test
        return String.format("%.2f", sum / TempsListInHour.size());
    }

    @Cacheable(cacheNames = "averageTemps", key = "#hours")
    public String getAverageHumidityInDurationHours(int hours) {
        //TODO test
        List<Temp> TempsListInHour = getTempsByLastHours(hours);
        double sum = TempsListInHour.stream()
                .mapToDouble(tempVal -> tempVal.getHumidity())
                .sum();
        return String.format("%.2f", sum / TempsListInHour.size());
    }

    @CachePut(cacheNames = "averageTemps", key = "#hours")
    public String updateGetAverageHumidityInHours(int hours) {
        //TODO test
        List<Temp> TempsListInHour = getTempsByLastHours(hours);
        double sum = TempsListInHour.stream()
                .mapToDouble(tempVal -> tempVal.getHumidity())
                .sum();
        return String.format("%.2f", sum / TempsListInHour.size());
    }

    @Scheduled(cron = " 30 3 1/3 * * ?") //At 03:30:00am, every 3 days starting on the 1st, every month
    public void updateAverageHumidityIn720HoursScheduled() {
        updateGetAverageHumidityInHours(720);
    }

    @Scheduled(cron = "0 0/5 * * * ?") //At minute 0 past every 5th hour from 0 through 23.”
    public void updateAverageHumidityIn168HoursScheduled() {
        updateGetAverageHumidityInHours(168);
    }

    @Scheduled(cron = "1/5 * * * * ?") //At every 5th minute from 1 through 59.
    public void updateAverageHumidityIn24HoursScheduled() {
        updateGetAverageHumidityInHours(24);
    }


}