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

    public void addTemp(Temp temp) {
        tempRepository.save(temp);
    }
}