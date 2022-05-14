package com.princevelasco.Battleship;

enum Ship {
    Carrier(5), Battleship(4), Submarine(3), Cruiser(3), Destroyer(2);

    private final int length;

    Ship(int length) {
        this.length = length;
    }

    public static int getShipLength(int i) {
        return values()[i].getLength();
    }

    public static String getShipName(int i) {
        return values()[i].name();
    }

    public static Ship getShip(int i) {
        return values()[i];
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name();
    }
}
