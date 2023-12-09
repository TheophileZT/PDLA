package model;

public enum Status {
    Pending(0),
    Accepted(1),
    Refused(2),
    Done(3);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
