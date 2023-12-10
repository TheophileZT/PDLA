package model;

/**
 * Status enum with the different status of a mission
 */
public enum Status {

    // Pending status when the mission is created
    Pending(0),

    // Accepted status when the mission is accepted by a validator
    Accepted(1),

    // Assigned status when the mission is assigned to a helper
    Assigned(2),

    // Refused status when the mission is refused by a validator
    Refused(3),

    // Done status when the mission is completed by a helper
    Done(4);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public String toString() {
        return switch (value) {
            case 0 -> "Pending";
            case 1 -> "Accepted";
            case 2 -> "Assigned";
            case 3 -> "Refused";
            case 4 -> "Done";
            default -> "Unknown";
        };
    }
}
