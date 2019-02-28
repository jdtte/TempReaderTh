package com.tempreader.temp.temp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "temps", description = "Operations pertaining Temps")
public class TempController {
    @Autowired
    TempService tempservice;

    @GetMapping("/temps")
    @ApiOperation(value = "Show all Temps", response = Temp.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Server reachable")})
    public List<Temp> getTemps() {
        return tempservice.getTemps();
    }

    @GetMapping("/temps/{id}")
    @ApiOperation(value = "Search and Show 1 Temp with the Id", response = List.class)
    public Temp getTemp(@PathVariable int id) {
        return tempservice.getTemp(id);
    }

    @PutMapping(value = "/temps/{id}", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Updates for a Temp with the Id", response = Temp.class)
    public void updateTemp(@RequestBody Temp temp) {
        tempservice.updateTemp(temp);
    }

    @PostMapping(value = "/temps/add", consumes = "application/json", produces = "application/json")
    @ApiOperation(value= "Add a Temp")
    public void addTemp(@RequestBody Temp temp) {
        tempservice.addTemp(temp);
    }

}
