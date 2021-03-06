package com.kuali.dto;

import com.kuali.Main;
import lombok.Data;

/**
 * Created by cyndeeshank on 4/24/17.
 */
@Data
public class Elevator {

    public enum DIRECTION {
        UP, DOWN
    }
    public enum DOOR_STATUS {
        OPEN, CLOSED
    }
    public enum MODE {
        ONLINE, MAINTENANCE
    }
    public Elevator (Integer id, Integer floor, DOOR_STATUS status) {
        id = id;
        currentFloor = floor;
        doorStatus = status;
        direction = DIRECTION.UP;
        numFloorsPassed = 0;
        numTrips = 0;
        currentMode = MODE.ONLINE;
        occupied = false;
    }

    Integer id;
    Integer currentFloor;
    Integer requestedFloor;
    DOOR_STATUS doorStatus;
    Integer numTrips;
    Integer numFloorsPassed;
    MODE currentMode;
    Boolean occupied;
    DIRECTION direction;

}
