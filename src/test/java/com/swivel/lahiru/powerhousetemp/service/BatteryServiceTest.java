package com.swivel.lahiru.powerhousetemp.service;

import com.swivel.lahiru.powerhousetemp.model.Battery;
import com.swivel.lahiru.powerhousetemp.model.BatteryResults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class BatteryServiceTest {
    @Autowired
    private BatteryService batteryService;

    @Test
    void addBatteryTest_validInput_savedInDatabase() {
        Battery battery = new Battery("test_battery_1", 10350, 120.50f);
        batteryService.addBattery(battery);

        List<Battery> allBatteries = batteryService.getAllBatteries();

        Assertions.assertEquals(1, allBatteries.size());

        Battery batteryResult = allBatteries.get(0);
        Assertions.assertEquals(battery.getName(), batteryResult.getName());
        Assertions.assertEquals(battery.getPostcode(), batteryResult.getPostcode());
        Assertions.assertEquals(battery.getCapacity(), batteryResult.getCapacity());
    }

    @Test
    void getBatteriesFromRange_validInputPreviousData_returnSavedBatteryDetails() {
        batteryService.addBattery(new Battery("c_battery", 90, 120.50f));
        batteryService.addBattery(new Battery("b_battery", 100, 100.0f));
        batteryService.addBattery(new Battery("a_battery", 50, 80.40f));
        batteryService.addBattery(new Battery("ab_battery", 66, 450));
        batteryService.addBattery(new Battery("b_battery_1", 73, 120.50f));

        BatteryResults summary = batteryService.getBatteriesFromRange(60, 100);

        Assertions.assertNotNull(summary);
        Assertions.assertEquals(4, summary.getNames().size());

        String[] namesInOrder = new String[]{"ab_battery", "b_battery", "b_battery_1", "c_battery"};
        String[] actualArray = summary.getNames().toArray(new String[0]);
        Assertions.assertArrayEquals(namesInOrder, actualArray);

        float actualTotal = 120.50f + 100.0f + 450 + 120.50f;
        float actualAverage = actualTotal / 4;
        Assertions.assertEquals(actualTotal, summary.getTotalCapacity());
        Assertions.assertEquals(actualAverage, summary.getAverageCapacity());
    }
}
