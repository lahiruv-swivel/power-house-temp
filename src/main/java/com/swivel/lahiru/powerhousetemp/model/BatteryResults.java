package com.swivel.lahiru.powerhousetemp.model;

import java.util.List;

public class BatteryResults {
    private List<String> names;
    private float totalCapacity;
    private float averageCapacity;

    public BatteryResults(List<String> names, float totalCapacity, float averageCapacity) {
        this.names = names;
        this.totalCapacity = totalCapacity;
        this.averageCapacity = averageCapacity;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public float getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(float totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public float getAverageCapacity() {
        return averageCapacity;
    }

    public void setAverageCapacity(float averageCapacity) {
        this.averageCapacity = averageCapacity;
    }
}
