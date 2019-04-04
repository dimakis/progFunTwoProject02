package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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

    public String doctorByCategory(int category) {
        String docByCategoryGen = "";
        String docByCategorySpec = "";
        String docByCategoryIntern = "";
        String docByCategoryConsult = "";
        String toReturn = "";
        for (int i = 0; i < numberOfDoctors(); i++) {
            if (getDoctor(i) instanceof General) {
                docByCategoryGen += i + getDoctor(i).toString();
            } else if (getDoctor(i) instanceof Specialist) {
                docByCategorySpec += i + getDoctor(i).toString();
            } else if (getDoctor(i) instanceof Intern) {
                docByCategoryIntern += i + getDoctor(i).toString();
            } else
                docByCategoryConsult += i + getDoctor(i).toString();
            if (category == 1) {
                docByCategoryGen = toReturn;
            } else if (category == 2) {
                docByCategorySpec = toReturn;
            } else if (category == 3) {
                docByCategoryIntern = toReturn;
            } else
                docByCategoryConsult = toReturn;
        }
        return toReturn;
    }

    public ArrayList<Doctor> docByCategoryArr(int category) {
        ArrayList<Doctor> arrGeneral = new ArrayList();
        ArrayList<Doctor> arrSpecialist = new ArrayList();
        ArrayList<Doctor> arrIntern = new ArrayList();
        ArrayList<Doctor> arrConsultant = new ArrayList();
        for (int i = 0; i < numberOfDoctors(); i++) {
            if (getDoctor(i) instanceof General) {
                arrGeneral.add(getDoctor(i));
            } else if (getDoctor(i) instanceof Specialist) {
                arrSpecialist.add(getDoctor(i));
            } else if (getDoctor(i) instanceof Intern) {
                arrIntern.add(getDoctor(i));
            } else {
                arrConsultant.add(getDoctor(i));
            }
        }
        ArrayList<Doctor> toReturn;
        if (category == 1) {
            toReturn = arrGeneral;
        } else if (category == 2) {
            toReturn = arrSpecialist;
        } else if (category == 3) {
            toReturn = arrIntern;
        } else
            toReturn = arrConsultant;
        return
                toReturn;
    }

    public String searchDoctorsByName(String specificDoctorByName) {
        String doctorByName = "";
        for (int i = 0; i < numberOfDoctors(); i++) {
            if (getDoctor(i).getName().contains(specificDoctorByName)) {
                doctorByName += i + getDoctor(i).toString();
            }

        }

        return doctorByName;
    }

    /*
        public String listDocBySpecialism() {
            String listOfSpecialisms = "";
            for (int i = 0; i < numberOfDoctors(); i++) {
                if ((getDoctor(i) instanceof Specialist) || getDoctor(i) instanceof Consultants) {
                    getDoctor(i).get

                }
            }
        }
    */
    public String isRegisteredInIreland() {
        String listOfIrishRegDocs = "";
        for (int i = 0; i < numberOfDoctors(); i++) {
            if (getDoctor(i).isQuailifiedInIreland()) {
                listOfIrishRegDocs += i + getDoctor(i).toString() + "\n";
            }
        }
        return
                listOfIrishRegDocs;
    }

    public int calculateAnnualFees() {
        int runningTotal = 0;
        for (int i = 0; i < numberOfDoctors(); i++) {
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
