package com.swivel.lahiru.powerhousetemp.service;

import com.swivel.lahiru.powerhousetemp.model.Battery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
//@ContextConfiguration(classes = {BatteryService.class, BatteryRepository.class})
public class BatteryServiceTest {
    @Autowired
    private BatteryService batteryService;

    @Test
    public void addBatteryTest_validInput_savedInDatabase() {
        Battery battery = new Battery("test_battery_1", 10350, 120.50f);
        batteryService.addBattery(battery);

        List<Battery> allBatteries = batteryService.getAllBatteries();

        Assertions.assertEquals(1, allBatteries.size());

        Battery batteryResult = allBatteries.get(0);
        Assertions.assertEquals(battery.getName(), batteryResult.getName());
        Assertions.assertEquals(battery.getPostcode(), batteryResult.getPostcode());
        Assertions.assertEquals(battery.getCapacity(), batteryResult.getCapacity());
    }
}
