package com.kuali.dto;

import lombok.Data;

/**
 * Created by cyndeeshank on 4/24/17.
 */
@Data
public class Elevator {

    public enum DOOR_STATUS {
        OPEN, CLOSED
    }
    public enum MODE {
        ONLINE, MAINTENANCE
    }
    public Elevator (int floor, DOOR_STATUS status) {
        currentFloor = floor;
        doorStatus = status;
        numFloorsPassed = 0;
        numTrips = 0;
        currentMode = MODE.ONLINE;
        isOccupied = false;
    }

    Integer currentFloor;
    DOOR_STATUS doorStatus;
    Integer numTrips;
    Integer numFloorsPassed;
    MODE currentMode;
    Boolean isOccupied;

}
