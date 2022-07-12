package com.swivel.lahiru.powerhousetemp.repository;

import com.swivel.lahiru.powerhousetemp.model.Battery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BatteryRepositoryInMemory {
    private final List<Battery> batteries;

    public BatteryRepositoryInMemory() {
        this.batteries = new ArrayList<>();
    }

    public void addBattery(Battery battery) {
        this.batteries.add(battery);
    }

    public List<Battery> getBatteries() {
        return this.batteries;
    }
}
