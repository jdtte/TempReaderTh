package com.tempreader.temp;

import com.tempreader.temp.temp.Temp;
import com.tempreader.temp.temp.TempRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TempRepositoryTest {

    @Autowired
    private TempRepository tempRepository;

    @Test
    public void IdFinderTest() {
        Temp test= tempRepository.findById(1);
        Assert.assertEquals(test.getId(),1);

    }

    @Test
    public void allTempsDesc(){
        List<Temp> test= tempRepository.findAllByOrderByIdDesc();
        for (Temp t:test) {


            System.out.println(t.toString());

        }
    }
}