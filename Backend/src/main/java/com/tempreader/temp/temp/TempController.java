package com.tempreader.temp.temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {
    @Autowired
    TempService tempService;

    @GetMapping("/")
    public String showTemps(Model model) {
        //TODO add time to scheduled values, add into table (eg. Letzte 30 Tage (20.05.20 or time)
        String[] avgValues1hour = tempService.getAverageTempAndHumidityInDurationHours(1);
        String[] avgValues24hours = tempService.getAverageTempAndHumidityInDurationHours(24);
        String[] avgValues168hours = tempService.getAverageTempAndHumidityInDurationHours(168);
        String[] avgValues720hours = tempService.getAverageTempAndHumidityInDurationHours(720);
        model.addAttribute("lastTemp", tempService.getLastTempEntry());
        //avg 1 hour
        //model.addAttribute("avgTempsInOneHour",tempService.getAverageTempInDurationHours(1));
        model.addAttribute("avgTempsInOneHour",avgValues1hour[0]);
        model.addAttribute("avgHumidityInOneHour", avgValues1hour[1]);
        //avg24hrs = 1day
        model.addAttribute("avgTempsIn24Hour", avgValues24hours[0]);
        model.addAttribute("avgHumidityIn24Hour", avgValues24hours[1]);
        //avg168hrs = 7 days
        model.addAttribute("avgTempsIn168Hour", avgValues168hours[0]);
        model.addAttribute("avgHumidityIn168Hour", avgValues168hours[1]);
        //avg720hrs = 30 days
        model.addAttribute("avgTempsIn720Hour", avgValues720hours[0]);
        model.addAttribute("avgHumidityIn720Hour", avgValues720hours[1]);
//        //avg24hrs = 1day
//        model.addAttribute("avgTempsIn24Hour", tempService.getAverageTempInDurationHours(24));
//        model.addAttribute("avgHumidityIn24Hour", tempService.getAverageHumidityInDurationHours(24));
//        //avg168hrs = 7 days
//        model.addAttribute("avgTempsIn168Hour", tempService.getAverageTempInDurationHours(168));
//        model.addAttribute("avgHumidityIn168Hour", tempService.getAverageHumidityInDurationHours(168));
//        //avg720hrs = 30 days
//        model.addAttribute("avgTempsIn720Hour", tempService.getAverageTempInDurationHours(720));
//        model.addAttribute("avgHumidityIn720Hour", tempService.getAverageHumidityInDurationHours(720));

        return "tempStart";
    }
}
