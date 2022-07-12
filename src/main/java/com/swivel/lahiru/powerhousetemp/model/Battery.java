package com.swivel.lahiru.powerhousetemp.model;


import javax.persistence.*;

@Entity
@Table(name = "battery")
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int postcode;
    private float capacity;

    public Battery() {
    }

    public Battery(String name, int postcode, float capacity) {
        this.name = name;
        this.postcode = postcode;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }
}
