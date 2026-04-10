# Parking Lot Management System Design

## Class Diagram

```mermaid
classDiagram
    class ParkingLot {
      -FareCalculator fareCalculator
      -ParkingManager parkingManager
      -Map~String, ParkingTicket~ activeTickets
      +park(vehicleDetails, entryTime, requestedSlotType, entryGateID) ParkingTicket
      +status() Map~SlotType, Integer~
      +exit(parkingTicket, exitTime) double
    }

    class ParkingManager {
      -List~ParkingSlot~ slots
      +getNearestCompatibleSlot(vehicle, requestedSlotType, entryGateId) ParkingSlot
      +park(vehicle, requestedSlotType, entryGateId) ParkingSlot
      +vacateSlot(slot)
      +availableSlotsByType() Map~SlotType, Integer~
    }

    class ParkingSlot {
      <<abstract>>
      -String slotId
      -SlotType slotType
      -Map~String, Integer~ distanceByGate
      -boolean occupied
      +getHourlyRate() double
      +canFit(vehicle, requestedSlotType) boolean
      +distanceFromGate(gateId) int
      +occupySlot(vehicle)
      +vacateSlot()
    }

    class SmallSlot
    class MediumSlot
    class LargeSlot

    class FareCalculator {
      -FareStrategy fareStrategy
      +calculateFare(ticket) double
    }

    class FareStrategy {
      <<interface>>
      +calculateFare(ticket) double
    }

    class DefaultFareStrategy

    class ParkingTicket {
      -String ticketId
      -Vehicle vehicle
      -ParkingSlot parkingSlot
      -LocalDateTime entryTime
      -LocalDateTime exitTime
      +calculateParkingDurationMinutes() long
      +getAllocatedSlotType() SlotType
      +closeAt(exitTime) long
    }

    class Vehicle {
      -String vehicleId
      -VehicleType vehicleType
    }

    class VehicleType
    class SlotType

    ParkingLot --> ParkingManager
    ParkingLot --> FareCalculator
    ParkingLot --> ParkingTicket

    ParkingManager --> ParkingSlot
    ParkingSlot <|-- SmallSlot
    ParkingSlot <|-- MediumSlot
    ParkingSlot <|-- LargeSlot

    FareCalculator --> FareStrategy
    FareStrategy <|.. DefaultFareStrategy

    ParkingTicket --> ParkingSlot
    ParkingTicket --> Vehicle
```

## Design and Approach

- Single Responsibility Principle:
  - ParkingLot coordinates APIs and ticket lifecycle only.
  - ParkingManager handles slot search/allocation/release.
  - FareCalculator and FareStrategy handle billing.
  - ParkingSlot classes encapsulate slot type + gate distance behavior.
- Open/Closed Principle:
  - New fare rules can be added via FareStrategy implementations.
  - New slot types can be introduced via ParkingSlot extensions.
- Liskov Substitution Principle:
  - SmallSlot, MediumSlot, and LargeSlot are interchangeable as ParkingSlot.
- Interface Segregation Principle:
  - FareStrategy is a focused interface for pricing behavior.
- Dependency Inversion Principle:
  - FareCalculator depends on FareStrategy abstraction, not concrete implementation.

## Requirement Mapping

- Three slot types with different hourly rates: implemented by SmallSlot/MediumSlot/LargeSlot.
- Ticket contains vehicle details, allocated slot number/type, and entry time: ParkingTicket.
- Nearest compatible slot by entry gate: ParkingManager.getNearestCompatibleSlot.
- Vehicle-to-slot compatibility with larger-slot fallback: ParkingSlot.canFit + VehicleType.minimumRequiredSlotType.
- Billing based on allocated slot type rate: DefaultFareStrategy using ticket parking slot rate.
- APIs:
  - park(vehicleDetails, entryTime, requestedSlotType, entryGateID)
  - status()
  - exit(parkingTicket, exitTime)
