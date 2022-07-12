package com.swivel.lahiru.powerhousetemp.service;

import com.swivel.lahiru.powerhousetemp.model.Battery;
import com.swivel.lahiru.powerhousetemp.model.BatteryResults;
import com.swivel.lahiru.powerhousetemp.repository.BatteryRepository;
import com.swivel.lahiru.powerhousetemp.repository.BatteryRepositoryInMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BatteryService {
    @Autowired
    private BatteryRepositoryInMemory batteryRepositoryInMemory;

    @Autowired
    private BatteryRepository batteryRepository;

    public void addBatteryOld(Battery battery) {
        batteryRepositoryInMemory.addBattery(battery);
    }

    public BatteryResults getBatteriesOld(int from, int to) {
        AtomicReference<Float> totalCapacityRef = new AtomicReference<>((float) 0);
        List<String> names = new ArrayList<>();

        batteryRepositoryInMemory.getBatteries().stream()
                .filter( battery -> battery.getPostcode() >= from && battery.getPostcode() <= to)
                .forEach(battery -> {
                    totalCapacityRef.updateAndGet(v -> v + battery.getCapacity());
                    names.add(battery.getName());
                });

        float totalCapacity = totalCapacityRef.get();
        float averageCapacity = totalCapacity / names.size();
        if (!names.isEmpty() && totalCapacity > 0 && averageCapacity > 0) {
            return new BatteryResults(names, totalCapacityRef.get(), averageCapacity);
        }
        return null;
    }

    public void addBattery(Battery battery) {
        batteryRepository.save(battery);
    }

    public List<Battery> getAllBatteries() {
        return (List<Battery>) batteryRepository.findAll();
    }

    public void deleteAllBatteries()
    {
        batteryRepository.deleteAll();
    }
}
