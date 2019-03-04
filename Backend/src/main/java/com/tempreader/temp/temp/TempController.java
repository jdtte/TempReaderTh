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
        String t ="werwerwerwerwer";
        // TODO remove year if lastTempDate
        model.addAttribute("lastTemp", tempService.getLastTempEntry());
        model.addAttribute("temps",tempService.getTemps());
        model.addAttribute("t",t);
        return "tempStart";
    }
}
