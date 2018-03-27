package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelRoom {
    private final IntegerProperty roomNumber;
    private final StringProperty name;
    private final StringProperty surname;
    private final StringProperty type;
    private final IntegerProperty charge;

    public ModelRoom(int roomNumber,String name, String surname, String type, int charge){
        this.roomNumber = new SimpleIntegerProperty(roomNumber);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.type = new SimpleStringProperty(type);
        this.charge = new SimpleIntegerProperty(charge);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public int getRoomNumber() {
        return roomNumber.get();
    }

    public IntegerProperty roomNumberProperty() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getCharge() {
        return charge.get();
    }

    public IntegerProperty chargeProperty() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge.set(charge);
    }
}
