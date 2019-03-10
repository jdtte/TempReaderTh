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

        // TODO remove year if lastTempDate
        model.addAttribute("lastTemp", tempService.getLastTempEntry());
        //avg 1 hour
        model.addAttribute("avgTempsInOneHour",tempService.getAverageTempInDurationHours(1));
        model.addAttribute("avgHumidityInOneHour", tempService.getAverageHumidityInDurationHours(1));
        //avg24hrs = 1day
        model.addAttribute("avgTempsIn24Hour", tempService.getAverageTempInDurationHours(24));
        model.addAttribute("avgHumidityIn24Hour", tempService.getAverageHumidityInDurationHours(24));
        //avg168hrs = 7 Tage
        model.addAttribute("avgTempsIn168Hour", tempService.getAverageTempInDurationHours(168));
        model.addAttribute("avgHumidityIn168Hour", tempService.getAverageHumidityInDurationHours(168));
        //avg720hrs = 30 Tage
        model.addAttribute("avgTempsIn720Hour", tempService.getAverageTempInDurationHours(720));
        model.addAttribute("avgHumidityIn720Hour", tempService.getAverageHumidityInDurationHours(720));

        return "tempStart";
    }
}
