package models;

import java.util.ArrayList;
import java.util.HashSet;

public class Consultants extends RegisteredDoctor {

    private HashSet<String> specialism = new HashSet<>();
    public Consultants(String name, String dob, char gender, String address, String contactNumber, ArrayList<Qualification> qualifications, HashSet<String> specialism, boolean qualifiedInIreland, ArrayList<Publications>) {
        super(name, dob, gender, address, contactNumber, qualifiedInIreland, qualifications);
    }

    public double calcRegistrationFee() {
        double registrationFee;
        if (isQuailifiedInIreland()) {          //might need super here dont think so though
            registrationFee = 205;
        } else registrationFee = 360;
        return registrationFee;
    }

    public HashSet<String> getSpecialism() {
        if (specialism.size() != 0) {
            return specialism;
        } else
            return null;
    }


    public void setSpecialism(HashSet<String> specialism) {

    }

    public String listHashSet() {
        String specialsList = specialism.toString();
        return specialsList;
    }

    @Override
    public boolean isQuailifiedInIreland() {
        if (super.isQuailifiedInIreland() == true) {
            return true;
        } else

            return false;
    }

    @Override
    public void setQualifiedInIreland() {
        if (isQuailifiedInIreland() == true) ;


    }


    @Override
    public String toString() {
        return super.toString() + "\n" + "Specialisms:    " + getSpecialism();
    }
}

