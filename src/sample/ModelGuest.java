package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;

public class ModelGuest {
    //CLASS THAT CONTAINS ATTRIBUTES OF GUEST
    //CONNECTED TO DATABASE

    //ATTRIBUTES
    private final StringProperty ID;
    private final StringProperty name;
    private final StringProperty surname;
    private final StringProperty timeOfArrival;
    private final StringProperty timeOfDeparture;
    private final StringProperty passport;
    private final StringProperty takeFrom;
    private final StringProperty nightsToStay;
    private final StringProperty contactNumber;
    private final StringProperty roomID;
    private final StringProperty creditCardNumber;
    private final StringProperty CVV;
    private final StringProperty monthOfExpire;
    private final StringProperty yearOfExpire;
    private final StringProperty currentBalance;

    private HashMap<Integer,String> numberMonthMap = new HashMap<>();
    private static int dayOfDeparture = 0;
    private static int monthOfDeparture = 0;
    private static int yearOfDeparture = 0;

/*
    *   1: ID
    *   2: name
    *   3: surname
    *   4: timeOfArrival
    *   5: nightsToStay
    *   6: needsTransfer
    *   7: takeFrom
    *   8: contactNumber
    *   9: roomID
    *   10: creditCardNumber
    *   11: CVV
    *   12: monthOfExpire
    *   13: yearOfExpire
    *   14: currentBalance
*/
    public ModelGuest(String ID, String name, String surname, String timeOfArrival,
                      String nightsToStay, String passport, String takeFrom, String contactNumber,
                      String roomID, String creditCardNumber, String CVV, String monthOfExpire,
                      String yearOfExpire, String currentBalance, String timeOfDeparture){
        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.timeOfArrival = new SimpleStringProperty(customizedDate(timeOfArrival));
        this.nightsToStay = new SimpleStringProperty(nightsToStay);
        this.passport = new SimpleStringProperty(passport);
        this.takeFrom = new SimpleStringProperty(takeFrom);
        this.contactNumber = new SimpleStringProperty(contactNumber);
        this.roomID = new SimpleStringProperty(roomID);
        this.creditCardNumber = new SimpleStringProperty(creditCardNumber);
        this.CVV = new SimpleStringProperty(CVV);
        this.monthOfExpire = new SimpleStringProperty(monthOfExpire);
        this.yearOfExpire = new SimpleStringProperty(yearOfExpire);
        this.currentBalance = new SimpleStringProperty(currentBalance);
        this.timeOfDeparture = new SimpleStringProperty(timeOfDeparture(nightsToStay));
    }
    //2018-03-15 00:00:00.000

    //00:00:00 15:December:2018

    public String timeOfDeparture(String nightsToStay){
        int day = (dayOfDeparture + Integer.parseInt(nightsToStay));
        if(day>30)
            monthOfDeparture++;
        day = day%30;
        if(day<10) {
            return "0" + day + ":" + numberMonthMap.get(monthOfDeparture)+":"+ yearOfDeparture;
        } else{
            return day + ":" + numberMonthMap.get(monthOfDeparture)+":"+ yearOfDeparture;
        }
    }
    //2018-03-15 00:00:00.000
    public String customizedDate(String dateTime){
        fillMap();
        String date = dateTime.substring(0,10);
        String[] arrayOfDate = date.split("[-.]");
        String day = arrayOfDate[2];
        dayOfDeparture = Integer.parseInt(day);
        String month = numberMonthMap.get(Integer.parseInt(arrayOfDate[1].substring(1)));
        monthOfDeparture = Integer.parseInt(arrayOfDate[1].substring(1));
        String year = arrayOfDate[0];
        yearOfDeparture = Integer.parseInt(year);

        return dateTime.substring(11,19) +" "+ day + ":"+month+":"+year;
    }

    public void fillMap(){
        numberMonthMap.put(1,"January");
        numberMonthMap.put(2,"February");
        numberMonthMap.put(3,"March");
        numberMonthMap.put(4,"April");
        numberMonthMap.put(5,"May");
        numberMonthMap.put(6,"June");
        numberMonthMap.put(7,"July");
        numberMonthMap.put(8,"August");
        numberMonthMap.put(9,"September");
        numberMonthMap.put(10,"October");
        numberMonthMap.put(11,"November");
        numberMonthMap.put(12,"December");
    }

    public String getDateOfExpire(){
        return getMonthOfExpire()+"/"+getYearOfExpire();
    }

    public String getPassport() {
        return passport.get();
    }

    public StringProperty passportProperty() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport.set(passport);
    }

    public HashMap<Integer, String> getNumberMonthMap() {
        return numberMonthMap;
    }

    public void setNumberMonthMap(HashMap<Integer, String> numberMonthMap) {
        this.numberMonthMap = numberMonthMap;
    }

    public static int getDayOfDeparture() {
        return dayOfDeparture;
    }

    public static void setDayOfDeparture(int dayOfDeparture) {
        ModelGuest.dayOfDeparture = dayOfDeparture;
    }

    public static int getMonthOfDeparture() {
        return monthOfDeparture;
    }

    public static void setMonthOfDeparture(int monthOfDeparture) {
        ModelGuest.monthOfDeparture = monthOfDeparture;
    }

    public static int getYearOfDeparture() {
        return yearOfDeparture;
    }

    public static void setYearOfDeparture(int yearOfDeparture) {
        ModelGuest.yearOfDeparture = yearOfDeparture;
    }

    public String getTimeOfDeparture() {
        return timeOfDeparture.get();
    }

    public StringProperty timeOfDepartureProperty() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(String timeOfDeparture) {
        this.timeOfDeparture.set(timeOfDeparture);
    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
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

    public String getTimeOfArrival() {
        return timeOfArrival.get();
    }

    public StringProperty timeOfArrivalProperty() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(String timeOfArrival) {
        this.timeOfArrival.set(timeOfArrival);
    }

    public String getNeedsTransfer() {
        return passport.get();
    }

    public StringProperty needsTransferProperty() {
        return passport;
    }

    public void setNeedsTransfer(String needsTransfer) {
        this.passport.set(needsTransfer);
    }

    public String getTakeFrom() {
        return takeFrom.get();
    }

    public StringProperty takeFromProperty() {
        return takeFrom;
    }

    public void setTakeFrom(String takeFrom) {
        this.takeFrom.set(takeFrom);
    }

    public String getNightsToStay() {
        return nightsToStay.get();
    }

    public StringProperty nightsToStayProperty() {
        return nightsToStay;
    }

    public void setNightsToStay(String nightsToStay) {
        this.nightsToStay.set(nightsToStay);
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public StringProperty contactNumberProperty() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber.set(contactNumber);
    }

    public String getRoomID() {
        return roomID.get();
    }

    public StringProperty roomIDProperty() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID.set(roomID);
    }

    public String getCreditCardNumber() {
        return creditCardNumber.get();
    }

    public StringProperty creditCardNumberProperty() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber.set(creditCardNumber);
    }

    public String getCVV() {
        return CVV.get();
    }

    public StringProperty CVVProperty() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV.set(CVV);
    }

    public String getMonthOfExpire() {
        return monthOfExpire.get();
    }

    public StringProperty monthOfExpireProperty() {
        return monthOfExpire;
    }

    public void setMonthOfExpire(String monthOfExpire) {
        this.monthOfExpire.set(monthOfExpire);
    }

    public String getYearOfExpire() {
        return yearOfExpire.get();
    }

    public StringProperty yearOfExpireProperty() {
        return yearOfExpire;
    }

    public void setYearOfExpire(String yearOfExpire) {
        this.yearOfExpire.set(yearOfExpire);
    }

    public String getCurrentBalance() {
        return currentBalance.get();
    }

    public StringProperty currentBalanceProperty() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance.set(currentBalance);
    }
}
