package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Doctor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DoctorAPI {

    private ArrayList<Doctor> doctors;

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
        if (doctors.size() != 0) {
            return doctors.size();
        } else

            return 0;
    }


    public String listDoctors()
    {

        return null;
    }


    public String listDoctors(String listOfDoctors) {

        return null;
    }

    public String searchDoctorsByName(ArrayList<Doctor>doctors) {

        return null;
    }

    public int calculateAnnualFees() {

        return 0;
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
