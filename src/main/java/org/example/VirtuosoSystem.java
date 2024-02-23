package org.example;

public class VirtuosoSystem extends Computer {
    public VirtuosoSystem(int id , String brand , String computerName , String computers , String system , String type ,
                          int hascam) {
        super(id , brand , computerName , computers , system , type , hascam);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getBrand() {
        return super.getBrand();
    }

    @Override
    public void setBrand(String brand) {
        super.setBrand(brand);
    }

    @Override
    public String getComputerName() {
        return super.getComputerName();
    }

    @Override
    public void setComputerName(String computerName) {
        super.setComputerName(computerName);
    }

    @Override
    public String getComputers() {
        return super.getComputers();
    }

    @Override
    public void setComputers(String computers) {
        super.setComputers(computers);
    }

    @Override
    public String getSystem() {
        return super.getSystem();
    }

    @Override
    public void setSystem(String system) {
        super.setSystem(system);
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public void setType(String type) {
        super.setType(type);
    }

    @Override
    public int getHascam() {
        return super.getHascam();
    }

    @Override
    public void setHascam(int hascam) {
        super.setHascam(hascam);
    }

    @Override
    public String toString() {
        return "метод 'VirtuosoSystem' " + super.toString();
    }
}
