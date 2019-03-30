package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Doctor;
import models.General;
import models.Specialist;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

public class DoctorAPI {

    private ArrayList<Doctor> doctors = new ArrayList<>();


    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public Doctor getDoctor(int index) {
        if ((numberOfDoctors() != 0) && index < numberOfDoctors()) {
            return doctors.get(index);
        } else
            return null;
    }

    public boolean removeDoctor(int index) {
        if ((numberOfDoctors() != 0) && (index <= numberOfDoctors() && (index >= 0))) {
            doctors.remove(index);
            return true;
        }
        return false;
    }

    public int numberOfDoctors() {
        return doctors.size();
    }
/*
    public boolean isQuailifiedInIreland() {

        return qualifiedInIreland;
    }
*/

    public String listDoctors() {
        String listOfDoctors = "";
        if (numberOfDoctors() != 0) {
            for (int i = 0; i < numberOfDoctors(); i++) {
                listOfDoctors = listOfDoctors + i + ") " + getDoctor(i) + "\n";
            }
        }
        return listOfDoctors;
    }

    public String listQualifications(int indexOfDoc) {
        String listOfQualifications = "";
        for (int i = 0; i < getDoctor(indexOfDoc).getQualifications().size(); i++) {
            listOfQualifications += i + ") " + getDoctor(indexOfDoc).getQualifications().toString();

        }
        return listOfQualifications;

    }
/*
    public HashSet<String> getSpecialism() {
        if (Specialist. != null) {

            return specialism;
        } else
            return null;
    }
/*
    public String listAllRegisteredDoctors() {
        {
            String listOfDoctors = null;
            for (int i = 0; i < numberOfDoctors(); i++) {
                if (getDoctor(i).) {
                    listOfDoctors = +i + getDoctor(i).toString() + "\n";
                }
                return listOfDoctors;
            }

            return null;
        }
    }
*/


    public String listDoctors(String listOfDoctors) {


        return null;
    }

    public String doctorByCategory(String category) {
        String docByCategory1 = "";
        String docByCategory2 = "";
        String docByCategory3 = "";
        for (int i = 0; i < numberOfDoctors(); i++) {
            double regFee = doctors.get(i).calcRegistrationFee();
            if ((regFee == 194) || (regFee == 410)) {
                docByCategory1 += i + doctors.get(i).toString() + "\n";
            } else if ((regFee == 641) || (regFee == 425)) {
                docByCategory2 += i + doctors.get(i).toString() + "\n";
            } else
                docByCategory3 += i + doctors.get(i).toString() + "\n";
        }
        if (category.equalsIgnoreCase("1")) {
            return
                    docByCategory1;
        } else if (category.equalsIgnoreCase("2")) {
            return docByCategory2;
        } else
            return
                    docByCategory3;
    }

    public ArrayList<Doctor> searchDoctorsByName(String specificDoctorByName) {
        ArrayList<Doctor> doctorByName = new ArrayList<>();
        for (int i = 0; i < numberOfDoctors(); i++) {
            if (getDoctor(i).getName().equalsIgnoreCase(specificDoctorByName)) {
                doctorByName.add(getDoctor(i));
            }

        }

        return doctorByName;
    }

    public int calculateAnnualFees() {
        int runningTotal = 0;
        for (int i = 0; i < numberOfDoctors(); i++){
            runningTotal += (int) getDoctor(i).calcRegistrationFee();
        }

        return runningTotal;
    }

    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("doctors.xml"));
        doctors = (ArrayList<Doctor>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("doctors.xml"));
        out.writeObject(doctors);
        out.close();
    }
}
