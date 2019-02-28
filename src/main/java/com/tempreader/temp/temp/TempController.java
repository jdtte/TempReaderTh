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
    @ApiOperation(value = "Search and Show 1 Temp with the Id", response = Temp.class)
    public Temp getTemp(@PathVariable int id) {
        return tempservice.getTemp(id);
    }

    @PutMapping(value = "/temps/{id}", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Updates for a Temp with the Id", response = Temp.class)
    public void updateTemp(@RequestBody Temp temp) {
        tempservice.updateTemp(temp);
    }

    @PostMapping(value = "/temps/add", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Add a Temp")
    public void addTemp(@RequestBody Temp temp) {
        tempservice.addTemp(temp);
    }

    @RequestMapping(value = "/temps/month", method = RequestMethod.GET)
    public List<Temp> paramMonthTemp(@RequestParam("month") String month, @RequestParam("year") String year) {
        return tempservice.getTempsByMonthAndYear(month, year);
    }

    @RequestMapping(value = "/temps/day", method = RequestMethod.GET)
    public List<Temp> paramDayTemp(@RequestParam("day") String day, @RequestParam("month") String month, @RequestParam("year") String year) {
        return tempservice.getTempsByDayMonthAndYear(day, month, year);
    }

    @GetMapping(value = "temps/year")
    public List<Temp> paramYearTemp(@RequestParam("year") String year) {
        return tempservice.getTempsByYear(year);
    }

    @RequestMapping(value = "/temps/list", method = RequestMethod.GET)
    public List<Temp> paramListTemp(@RequestParam(value = "day", required = false) String day,
                                    @RequestParam(value = "month", required = false) String month,
                                    @RequestParam(value = "year") String year,
                                    @RequestParam(value= "hour", required = false) String hour) {
        System.out.println(day);
        System.out.println(month);
        System.out.println(year);
        System.out.println(hour);
        return tempservice.getTempsList(day, month, year, hour);
    }

}
