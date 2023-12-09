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

    public int getValue() {
        return value;
    }
}
