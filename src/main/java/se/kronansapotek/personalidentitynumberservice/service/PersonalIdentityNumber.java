package se.kronansapotek.personalidentitynumberservice.service;

public class PersonalIdentityNumber {
    private final String inputString;

    private final int calculatedControlNumber;

    public PersonalIdentityNumber(String inputString, int calculatedControlNumber) {
        this.inputString = inputString;
        this.calculatedControlNumber = calculatedControlNumber;
    }

    public boolean isValid() {
        return calculatedControlNumber == getControlNumber();
    }

    private int getControlNumber() {
        return Integer.parseInt("" + inputString.charAt(inputString.length() - 1));
    }

    public String getInputString() {
        return inputString;
    }

    public int getCalculatedControlNumber() {
        return calculatedControlNumber;
    }
}
