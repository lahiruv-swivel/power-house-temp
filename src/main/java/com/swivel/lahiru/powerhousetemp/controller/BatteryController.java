package com.swivel.lahiru.powerhousetemp.controller;

import com.swivel.lahiru.powerhousetemp.model.Battery;
import com.swivel.lahiru.powerhousetemp.model.BatteryResults;
import com.swivel.lahiru.powerhousetemp.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batteries")
public class BatteryController {
    @Autowired
    private BatteryService batteryService;

    /**
     * add a new battery details to database
     * @param battery   battery object / request body
     * @return          status
     */
    @PostMapping
    public ResponseEntity<Battery> addBattery(@RequestBody Battery battery) {
        if (isValidBatteryDto(battery)) {
            batteryService.addBattery(battery);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * check whether provided request body is a valid battery object
     * @param battery   battery object provided in request body
     * @return  is valid
     */
    private boolean isValidBatteryDto(Battery battery) {
        return battery != null && battery.getName() != null && !battery.getName().isEmpty()
                && battery.getCapacity() > 0 && battery.getPostcode() > 0;
    }

    /**
     * get a summary of batteries within a postcode range
     * @param from  from postcode number
     * @param to    to postcode number
     * @return      battery details object
     */
    @GetMapping("/range")
    public ResponseEntity<BatteryResults> getBatteries(@RequestParam int from, @RequestParam int to) {
        if (from > 0 && to > from) {
            BatteryResults results = batteryService.getBatteriesFromRange(from, to);
            if (results != null) {
                return new ResponseEntity<>(results, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * get all batteries in database
     * @return  list of all batteries
     */
    @GetMapping
    public ResponseEntity<List<Battery>> getBatteries() {
        List<Battery> allBatteries = batteryService.getAllBatteries();
        if (allBatteries != null && !allBatteries.isEmpty()) {
            return new ResponseEntity<>(allBatteries, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
