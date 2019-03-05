package com.tempreader.temp;

import com.tempreader.temp.temp.Temp;
import com.tempreader.temp.temp.TempRepository;
import com.tempreader.temp.temp.TempService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TempRepositoryTest {

    @Autowired
    private TempRepository tempRepository;

    @Autowired
    private TempService tempService;

    @Test
    public void IdFinderTest() {
        Temp test = tempRepository.findById(1);
        Assert.assertEquals(test.getId(), 1);

    }


    @Test
    public void allTempsDesc() {
        //ex 05.03.15 13:40:25
        DateTimeFormatter tempFormat = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm:ss");
        Temp lastTemp = tempRepository.findFirstByOrderByIdDesc();
        List<Temp> test = tempRepository.findAllByOrderByIdDesc();
        LocalDateTime start = LocalDateTime.parse(lastTemp.getDate(), tempFormat);
        int differenceHours =-1;
        List<Temp> result = test.stream()
                .filter(t -> ChronoUnit.HOURS.between(start, LocalDateTime.parse(t.getDate(),tempFormat)) <= differenceHours)
                .collect(Collectors.toList());

        result.forEach(System.out::println);
//        for (Temp t : test) {
//
//
//            System.out.println(t.toString());
//
//        }
    }
}