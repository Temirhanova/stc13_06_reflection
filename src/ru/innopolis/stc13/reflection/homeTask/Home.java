package ru.innopolis.stc13.reflection.homeTask;

public class Home {
    private int floor;
    private int window;
    private boolean live;
    private String address;

    public Home() {}

    public Home(int floor, int window, boolean live, String address) {
        this.floor = floor;
        this.window = window;
        this.live = live;
        this.address = address;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Home = [ floor : " + this.floor + ",\n " +
                "window : " + this.window + ",\n " +
                "live : " + this.live + ",\n " +
                "address : " + this.address + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        Home home = (Home) obj;
        System.out.println("this = " + this.address);
        System.out.println("home = " + home.address);
        if (this.floor == home.floor &&
            this.window == home.window &&
            this.live == home.live &&
            this.address.equals(home.address)
                ) {
            return true;
        } else {
            return false;
        }
    }
}
