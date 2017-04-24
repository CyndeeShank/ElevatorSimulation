package com.kuali;

import com.kuali.dto.Elevator;

import java.util.*;

public class Main {

    // declare the number of Floors and Elevators
    public static Integer numElevators = 3;
    public static Integer numFloors = 5;
    public static Map<Integer, Elevator> elevatorMap = new HashMap<Integer, Elevator>();

    public static void main(String[] args) {

        // create the elevators
        for (int i = 1; i<=numElevators; i++) {
           Elevator elevator = new Elevator(i, 1, Elevator.DOOR_STATUS.CLOSED);
           elevatorMap.put(i, elevator);
        }
        //TODO; handle the calls to the elevators here
    }

    public void elevatorReport(Integer elevatorId, Integer currentFloor, Integer requestedFloor, Elevator.DOOR_STATUS doorStatus, Boolean occupied) {
        // get the elevator from the map, update the floors, door status, occupied, number of trips and current mode, add back to the map
        Elevator elevator = elevatorMap.get(elevatorId);
        elevator.setCurrentFloor(currentFloor);
        elevator.setRequestedFloor(requestedFloor);
        elevator.setDoorStatus(doorStatus);
        elevator.setOccupied(occupied);
        // set the direction
        if (currentFloor < requestedFloor) {
            elevator.setDirection(Elevator.DIRECTION.UP);
        }
        else {
            elevator.setDirection(Elevator.DIRECTION.DOWN);
        }
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

    public Elevator requestElevator(Elevator.DIRECTION direction, Integer currentFloor) {

        Elevator closestElevator = null;

        // go thru the list of elevators and get each floor, direction and whether occupied or not
        Set keys = elevatorMap.keySet();
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            Elevator elevator = elevatorMap.get(iterator);
            // if there is an unoccupied elevator at the currentFloor, it takes priority over all others
            if (elevator.getCurrentFloor() == currentFloor && !elevator.getOccupied() && elevator.getCurrentMode() == Elevator.MODE.ONLINE) {
                closestElevator = elevator;
            }
            // if an occupied elevator is moving past the current floor in the requested direction
            else if (elevator.getCurrentFloor() < currentFloor && elevator.getRequestedFloor() > currentFloor
                    && elevator.getDirection() == direction && elevator.getOccupied() && elevator.getCurrentMode() == Elevator.MODE.ONLINE) {
                closestElevator = elevator;
            }
            // determine which elevator is closest if unoccupied
            else if (!elevator.getOccupied() &&
                    (elevator.getCurrentFloor() < currentFloor + 1 || elevator.getCurrentFloor() > currentFloor + 1)
                    && elevator.getCurrentMode() == Elevator.MODE.ONLINE) {
                closestElevator = elevator;
            }
            //TODO: need to handle check for other elevators that are more than 1 floor away...
        }
        return closestElevator;
    }
}
