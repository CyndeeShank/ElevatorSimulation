package com.kuali;

import com.kuali.dto.Elevator;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // declare the number of Floors and Elevators
    public static Integer numElevators = 3;
    public static Integer numFloors = 5;
    public static List<Elevator> elevatorList = new ArrayList();
    public enum DIRECTION {
        UP, DOWN
    }

    public static void main(String[] args) {

        // create the elevators
        for (int i = 1; i<=numElevators; i++) {
           Elevator elevator = new Elevator(1, Elevator.DOOR_STATUS.CLOSED);
           elevatorList.add(elevator);
        }
    }

    public Elevator requestElevator(DIRECTION direction, Integer currentFloor) {

        // go thru the list of elevators and get each floor, direction and whether occupied or not
        // if there is an unoccupied elevator at the currentFloor, it takes priority over all others
        for (Elevator elevator : elevatorList) {
            if (elevator.getCurrentFloor() == currentFloor && !elevator.getIsOccupied()) {
                return elevator;
            }

        }


        // determine which elevator is closest if unoccupied, or if an occupied elevator is moving past the
        // current floor in the requested direction
    }
}
