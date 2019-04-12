package models;

import java.util.ArrayList;
import java.util.HashSet;

public class Consultants extends Specialist{

    private ArrayList<Publications> publications; // = new ArrayList<>();
    private HashSet<String> specialism; // = new HashSet<>();
    public Consultants(String name, int doctorNumber, String dob, char gender, String address, String contactNumber, ArrayList<Qualification> qualifications, HashSet<String> specialism, boolean qualifiedInIreland, ArrayList<Publications> publications) {
        super(name, doctorNumber, dob, gender, address, contactNumber, qualifications, specialism, qualifiedInIreland);
        this.specialism = specialism;
        this.publications = publications;
    }


    public double calcRegistrationFee() {
        double registrationFee;
        if (isQuailInIre()) {
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

    public ArrayList<Publications> getPublications() {
        return publications;
    }

    public void setSpecialism(HashSet<String> specialism) {

    }

    public String listHashSet() {
        String specialsList = specialism.toString();
        return specialsList;
    }

    @Override
    public boolean isQuailInIre() {
        if (super.isQuailInIre() == true) {
            return true;
        } else

            return false;
    }

    @Override
    public void setQualifiedInIreland() {
        if (isQuailInIre() == true) ;


    }


    /**
     * For loops used for asthetic purposes. To remove '[ ]' from toString
     * @return
     */
    @Override
    public String toString() {
        String publiList = "";
        for (int i = 0; i < publications.size(); i++)  {
            publiList += "\n" + publications.get(i);
        }
        String specStri = "";
        for (String special:specialism) {
            specStri += "\n \t \t" +  special;
        }
        return "Consultant " + super.toString() + "\n" + "Specialisms:  " + specStri + "\n" + "Publications: " + publications.size() + publiList;
    }
}

