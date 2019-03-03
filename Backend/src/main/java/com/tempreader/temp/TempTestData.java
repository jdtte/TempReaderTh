package com.tempreader.temp;

import com.tempreader.temp.temp.Temp;
import com.tempreader.temp.temp.TempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("test") // start nur wenn man profile "test" startet, sonst wird ignorier
public class TempTestData {

    @Autowired
    TempService tempService;

    //'Time': 03.03.19 15:30:25, 'Humidity': 34.6, 'temp': 23.9
    @PostConstruct
    public void createTestData() {
        tempService.createTemp(new Temp(1, 32.2, 22.9, "03.03.19 11:40:25"));
        tempService.createTemp(new Temp(2, 33.6, 23.9, "02.03.19 20:40:25"));
        tempService.createTemp(new Temp(3, 34.5, 21.9, "01.03.19 19:40:25"));
        tempService.createTemp(new Temp(4, 35.6, 23.9, "03.03.19 18:40:25"));
        tempService.createTemp(new Temp(5, 36.6, 26.9, "03.02.19 18:40:25"));
        tempService.createTemp(new Temp(6, 37.6, 23.9, "03.01.19 11:40:25"));
        tempService.createTemp(new Temp(7, 31.3, 30.9, "03.03.18 16:40:25"));
        tempService.createTemp(new Temp(8, 31.6, 23.9, "03.03.17 11:40:25"));
        tempService.createTemp(new Temp(9, 37.6, 23.9, "03.03.16 11:40:25"));
        tempService.createTemp(new Temp(10, 38.6, 13.9, "03.03.15 12:40:25"));
        tempService.createTemp(new Temp(11, 39.3, 23.9, "03.03.13 12:40:25"));
        tempService.createTemp(new Temp(12, 30.6, 23.9, "03.03.13 14:40:25"));
        tempService.createTemp(new Temp(13, 37.5, 23.9, "03.03.15 13:40:25"));

    }


}
