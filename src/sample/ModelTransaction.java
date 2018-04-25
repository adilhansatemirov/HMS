package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/*Author's information
* Author: Adilkhan Satemirov
* Email: adilkhansatemirovv@gmail.com
* Phone number: 8(775)216-01-56
*/
public class ModelTransaction {
    private final StringProperty name;
    private final StringProperty surname;
    private final StringProperty time;
    private final IntegerProperty spent;
    private final StringProperty spentOn;

    public ModelTransaction(String name,String surname, String time, int spent, String spentOn){
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.time = new SimpleStringProperty(time);
        this.spent = new SimpleIntegerProperty(spent);
        this.spentOn = new SimpleStringProperty(spentOn);
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

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public int getSpent() {
        return spent.get();
    }

    public IntegerProperty spentProperty() {
        return spent;
    }

    public void setSpent(int spent) {
        this.spent.set(spent);
    }

    public String getSpentOn() {
        return spentOn.get();
    }

    public StringProperty spentOnProperty() {
        return spentOn;
    }

    public void setSpentOn(String spentOn) {
        this.spentOn.set(spentOn);
    }
}
