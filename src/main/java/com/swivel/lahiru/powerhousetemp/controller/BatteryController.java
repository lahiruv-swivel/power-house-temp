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
@RequestMapping("/battery")
public class BatteryController {

    @Autowired
    private BatteryService batteryService;

    @PostMapping
    public ResponseEntity<Battery> addBattery(@RequestBody Battery battery) {
        if (isValidBatteryDto(battery)) {
            batteryService.addBattery(battery);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private boolean isValidBatteryDto(Battery battery) {
        return battery != null && battery.getName() != null && !battery.getName().isEmpty()
                && battery.getCapacity() > 0 && battery.getPostcode() > 0;
    }

//    @GetMapping("/range")
//    public ResponseEntity<BatteryResults> getBatteries(@RequestParam int from, @RequestParam int to) {
//        if (from > 0 && to > from) {
//            BatteryResults results = batteryService.getBatteriesOld(from, to);
//            if (results != null) {
//                return new ResponseEntity<>(results, HttpStatus.ACCEPTED);
//            }
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

    @GetMapping("")
    public ResponseEntity<List<Battery>> getBatteries() {
        List<Battery> allBatteries = batteryService.getAllBatteries();
        if (allBatteries != null && !allBatteries.isEmpty()) {
            return new ResponseEntity<>(allBatteries, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
