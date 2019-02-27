package com.tempreader.temp.temp;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TempController {
    @Autowired
    TempService tempservice;

    @RequestMapping("/api/temps")
    @ApiOperation(value = "Show 1 Temp", response = Temp.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Server reachable")})
    public List<Temp> getTemps(){
        return tempservice.getTemps();
    }
    @RequestMapping("api/temps/{SearchId}")
    public Temp getTemp(@PathVariable int SearchId ){
        return tempservice.getTemp(SearchId);
    }

}
