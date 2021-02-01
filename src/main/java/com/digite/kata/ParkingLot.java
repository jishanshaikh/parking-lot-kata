package com.digite.kata;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ParkingLot {

    private final int parkingLotCapacity;
    Map<Integer, Car> slots;
    private int currentCapacity;

    public ParkingLot(int intialCapacity) {
        parkingLotCapacity = intialCapacity;
        currentCapacity = parkingLotCapacity;
        slots = new HashMap<>();
    }

    public int getStatus() {
        return this.currentCapacity;
    }

    public void park(String registrationNumber, String colour) {
        if ( currentCapacity > 0 ) {
            this.slots.put(getNearestVacantSlot(), new Car(registrationNumber, colour));
            this.currentCapacity -= 1;
        }
    }

    public int getNearestVacantSlot() {
        Integer vacantSlot = -1;

        Set<Integer> keys = slots.keySet();
        int counter = 0;

        while ( counter < slots.size() ) {
            counter++;

            if ( !slots.containsKey(counter) ) {
                vacantSlot = counter;
                break;
            }
        }

        if ( vacantSlot == -1 )
            vacantSlot = slots.size() + 1;

        return vacantSlot;
    }

    public String getColourByRegistrationNumber(String registrationNumber) {
        return slots.entrySet().stream().filter(entry -> registrationNumber.equals(entry.getValue().getRegistrationNumber()))
                .map(val -> val.getValue().getColour())
                .collect(Collectors.joining());
    }

    public void leave(int slot) {
        slots.remove(slot);
        currentCapacity += 1;
    }

    public int getSlotNumberByRegistrationNumber(String registrationNumber) {
        int slot = -1;

        for (Map.Entry<Integer, Car> entry : slots.entrySet()) {
            if (entry.getValue().getRegistrationNumber().equals(registrationNumber)) {
                slot = entry.getKey();
            }
        }

        return slot;
    }
}
