package framework.utils;

public enum DriverType {
    FIREFOX("Firefox"),
    CHROME("Chrome");

    private final String driverName;

    DriverType(String driverName) {
        this.driverName = driverName;
    }
}

