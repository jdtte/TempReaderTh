package com.tempreader.temp.temp;

import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TempServiceScheduleManager {

    @Autowired
    TempService tempService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "30 3 1/3 * * ?") //At 03:30:00am, every 3 days starting on the 1st, every month
    public void updateAverageHumidityAndTempsIn720HoursScheduled() {
        log.info("pdateAverageHumidityAndTempsIn720HoursScheduled: cronjob run, At 03:30:00am, every 3 days starting on the 1st, every month");
        tempService.updateGetAverageTempAndHumidityInDurationHours(720);
    }

    @Scheduled(fixedDelayString = "${tempService.fixedDelayUpdateAverageHumidityIn168HoursScheduled}",initialDelay=50000) //5 hrs after last is done (value is in ms)
    public void updateAverageHumidityAndTempsIn168HoursScheduled() {
        log.info("updateAverageHumidityAndTempsIn168HoursScheduled: cronjob run, every 5 hrs");
        tempService.updateGetAverageTempAndHumidityInDurationHours(168);
    }

    @Scheduled(fixedDelay = 300000,initialDelay=5000) // 5 Min after last is done (value is in ms)
    public void updateAverageHumidityAndTempsIn24HoursScheduled() {
        log.info("updateAverageHumidityAndTempsIn24HoursScheduled: cronjob run, every 5 min");
        tempService.updateGetAverageTempAndHumidityInDurationHours(24);
    }


}
