package model;

public enum Status {
    Pending(0),
    Accepted(1),
    Assigned(2),
    Refused(3),
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
