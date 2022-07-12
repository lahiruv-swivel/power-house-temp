package com.swivel.lahiru.powerhousetemp.service;

import com.swivel.lahiru.powerhousetemp.model.Battery;
import com.swivel.lahiru.powerhousetemp.model.BatteryResults;
import com.swivel.lahiru.powerhousetemp.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.StreamSupport;

@Service
public class BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;

    /**
     * derive a summary of battery details within a requested postal code range
     * @param from  from postcode
     * @param to    to postcode
     * @return      battery details summary
     */
    public BatteryResults getBatteriesFromRange(int from, int to) {
        AtomicReference<Float> totalCapacityRef = new AtomicReference<>((float) 0);
        List<String> names = new ArrayList<>();

        StreamSupport.stream(batteryRepository.findAll().spliterator(), false)
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

    /**
     * save battery into database
     * @param battery   battery input
     */
    public void addBattery(Battery battery) {
        batteryRepository.save(battery);
    }

    /**
     * get all batteries from db
     * @return  list of batteries
     */
    public List<Battery> getAllBatteries() {
        return (List<Battery>) batteryRepository.findAll();
    }
}
