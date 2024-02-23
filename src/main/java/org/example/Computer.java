package org.example;

public abstract class Computer {
    private int id;
    private String brand;
    private  String computerName;
    private String computers;
    private String system;
    private  String type;
    private int hascam;

    public Computer(int id , String brand , String computerName ,
                    String computers , String system , String type , int hascam) {
        this.id = id;
        this.brand = brand;
        this.computerName = computerName;
        this.computers = computers;
        this.system = system;
        this.type = type;
        this.hascam = hascam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getComputers() {
        return computers;
    }

    public void setComputers(String computers) {
        this.computers = computers;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHascam() {
        return hascam;
    }

    public void setHascam(int hascam) {
        this.hascam = hascam;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", computerName='" + computerName + '\'' +
                ", computers='" + computers + '\'' +
                ", system='" + system + '\'' +
                ", type='" + type + '\'' +
                ", hascam=" + hascam +
                '}';
    }


}
