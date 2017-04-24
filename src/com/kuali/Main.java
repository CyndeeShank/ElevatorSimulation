package com.kuali;

import com.kuali.dto.Elevator;

import java.util.*;

public class Main {

    // declare the number of Floors and Elevators
    public static Integer numElevators = 3;
    public static Integer numFloors = 5;
    public static List<Elevator> elevatorList = new ArrayList();
    public static Map<Integer, Elevator> elevatorMap = new HashMap<Integer, Elevator>();
    public enum DIRECTION {
        UP, DOWN
    }

    public static void main(String[] args) {

        // create the elevators
        for (int i = 1; i<=numElevators; i++) {
           Elevator elevator = new Elevator(i, 1, Elevator.DOOR_STATUS.CLOSED);
           elevatorList.add(elevator);
           elevatorMap.put(i, elevator);
        }

    }

    public void elevatorReport(Integer elevatorId, Integer currentFloor, Elevator.DOOR_STATUS doorStatus, Boolean occupied) {
        // get the elevator from the map, update the floor and door status, add back to the map
        Elevator elevator = elevatorMap.get(elevatorId);
        elevator.setCurrentFloor(currentFloor);
        elevator.setDoorStatus(doorStatus);
        elevator.setOccupied(occupied);
        // check for number of trips... if more that 100, change mode to maintenance
        int numTrips = elevator.getNumTrips();
        if (numTrips < 100) {
            elevator.setNumTrips(numTrips++);
        }
        else {
            elevator.setCurrentMode(Elevator.MODE.MAINTENANCE);
        }

        elevatorMap.put(elevatorId, elevator);
    }

    public Elevator requestElevator(DIRECTION direction, Integer currentFloor) {

        Elevator closestElevator;

        // go thru the list of elevators and get each floor, direction and whether occupied or not
        // if there is an unoccupied elevator at the currentFloor, it takes priority over all others
        Set keys = elevatorMap.keySet();
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            Elevator elevator = elevatorMap.get(iterator);
            if (elevator.getCurrentFloor() == currentFloor && !elevator.getOccupied()) {
                closestElevator = elevator;
            }
        }



        // determine which elevator is closest if unoccupied, or if an occupied elevator is moving past the
        // current floor in the requested direction

        return closestElevator;
    }
}
