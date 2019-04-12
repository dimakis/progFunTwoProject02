package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class has methods used in the mainMenu options.
 */
public class DoctorAPI {

    private ArrayList<Doctor> doctors = new ArrayList<>();


    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);

    }

    /**
     * Checks to see if the doctor selected by ID is a valid doctor in ArrayList
     *
     * @param docId
     * @return Boolean
     */
    public boolean isValidDoc(int docId) {
        boolean validDoc = false;
        if (doctors.contains(getDocById(docId)))    {
            validDoc = true;
        }
        return validDoc;
    }


    /**
     * Gets the Doctor from the ArrayList by the Doctors ID
     *
     * @param doctorToGet
     * @return
     */
    public Doctor getDocById(int doctorToGet){
        Doctor getDocById = null;
        for (int i = 0; i < numberOfDoctors(); i++) {
            if (getDoctor(i).getDoctorNumber() == doctorToGet)  {
                getDocById = getDoctor(i);
            }
        }
        return getDocById;
    }


    /**
     * Gets Doctor by index position in ArrayList
     *
     * @param index
     * @return
     */
    public Doctor getDoctor(int index) {
        if ((index >= 0) && (index < numberOfDoctors())) {
            return doctors.get(index);
        }
        else
            return null;
    }

        /**
         * Removes Doctor from ArrayList by given ID
         *
         * @param docIdToRemove
         * @return
         */
        public boolean removeDocById ( int docIdToRemove){
            boolean removeDoc = false;
            for (int i = 0; i < numberOfDoctors(); i++) {
                if (getDoctor(i).getDoctorNumber() == docIdToRemove) {
                    removeDoctor(i);
                    removeDoc = true;
                }
            }
            return removeDoc;
        }

        /**
         * Removes Doctor from ArrayList by given index
         *
         * @param index
         * @return
         */
        public boolean removeDoctor ( int index){
            if ((numberOfDoctors() != 0) && (index <= numberOfDoctors() && (index >= 0))) {
                doctors.remove(index);
                return true;
            }
            return false;
        }

    /**
     * Returns size of the ArrayList
     *
     * @return
     */
    public int numberOfDoctors() {
        if (doctors.size() != 0) {
            return doctors.size();
        } else
            return 0;
    }

    /**
     * Returns a full list of all doctors in array list
     *
     * @return
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

    /**
     * Returns a string of the qualifications of the doctor by the index of said doctor in ArrayList
     *
     * @param indexOfDoc
     * @return
     */
    public String listQualifications(int indexOfDoc) {
        String listOfQualifications = "";
        for (int i = 0; i < getDoctor(indexOfDoc).getQualifications().size(); i++) {
            listOfQualifications += i + ") " + getDoctor(indexOfDoc).getQualifications().toString();

        }
        return listOfQualifications;
    }

    /**
     * Returns a String of each doctor's contact details, else if no doctors are in the database returns empty string.
     * @return
     */
    public String listContactDeets() {
        String contactDeetsList = "";
        for (int i = 0; i < numberOfDoctors(); i++) {
            contactDeetsList += getDoctor(i).viewContactDetails();
        }
        return contactDeetsList;
    }

    /**
     * Returns a String of all the doctors by the category chosen, else returns error message.
     * @param category
     * @return
     */
    public String doctorByCategory(int category) {
        String docByCategoryGen = "";
        String docByCategorySpec = "";
        String docByCategoryIntern = "";
        String docByCategoryConsult = "";
        String toReturn = "";
        for (int i = 0; i < numberOfDoctors(); i++) {
            if (doctors.get(i) instanceof General) {
                docByCategoryGen += i + getDoctor(i).toString();
            } else if (doctors.get(i) instanceof Specialist) {
                docByCategorySpec += i + getDoctor(i).toString();
            } else if (doctors.get(i) instanceof Intern) {
                docByCategoryIntern += i + getDoctor(i).toString();
            } else
                docByCategoryConsult += i + getDoctor(i).toString();

            if (category == 1) {
                toReturn = docByCategoryGen;
            } else if (category == 2) {
                toReturn = docByCategorySpec;
            } else if (category == 3) {
                toReturn = docByCategoryIntern;
            } else if (category == 4) {
                toReturn = docByCategoryConsult;
            }
        }
        if (toReturn.equals("")) {
            toReturn = "There are no Doctors in the selected category";
        }
        return toReturn;
    }

    /**
     * Returns a String of all the contact details of the doctors by the category chosen, else returns error message.
     * @param category
     * @return
     */
    public String doctorByCategoryContactDeets(int category) {
        String docByCategoryGen = "";
        String docByCategorySpec = "";
        String docByCategoryIntern = "";
        String docByCategoryConsult = "";
        String toReturn = "";
        for (int i = 0; i < numberOfDoctors(); i++) {
            if (getDoctor(i) instanceof General) {
                docByCategoryGen += i + getDoctor(i).viewContactDetails();
            } else if (getDoctor(i) instanceof Specialist) {
                docByCategorySpec += i + getDoctor(i).viewContactDetails();
            } else if (getDoctor(i) instanceof Intern) {
                docByCategoryIntern += i + getDoctor(i).viewContactDetails();
            } else
                docByCategoryConsult += i + getDoctor(i).viewContactDetails();
            if (category == 1) {
                toReturn = docByCategoryGen;
            } else if (category == 2) {
                toReturn = docByCategorySpec;
            } else if (category == 3) {
                toReturn = docByCategoryIntern;
            } else if (category == 4) {
                toReturn = docByCategoryConsult;
            }
        }
        if (toReturn.equals("")) {
            toReturn = "There are no Doctors in the selected category";
        }
        return toReturn;
    }

    /**
     * Returns an Arraylist of type Doctor, doctors are sorted by category chosen.
     * @param category
     * @return
     */
    public ArrayList docByCategoryArr(int category) {
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
        ArrayList toReturn = null;
        if (category == 1) {
            toReturn = arrGeneral;
        } else if (category == 2) {
            toReturn = arrSpecialist;
        } else if (category == 3) {
            toReturn = arrIntern;
        } else if (category == 4) {
            toReturn = arrConsultant;
        }
        return
                toReturn;
    }

    /**
     * Returns a list of doctor's whose name matches a name input. Includes partial matches.
     * @param specificDoctorByName
     * @return
     */
    public String searchDoctorsByName(String specificDoctorByName) {
        String doctorByName = "";
        if (numberOfDoctors() != 0) {
            for (int i = 0; i < numberOfDoctors(); i++) {
                if (getDoctor(i).getName().toLowerCase().contains(specificDoctorByName.toLowerCase())) {
                    doctorByName += i + getDoctor(i).toString();
                }
            }
            if (doctorByName.equals("")) {
                doctorByName = "There are no doctors by that name";
            }
        }
            return doctorByName;
    }


    /**
     * Returns a list of doctor's contact details whose name matches a name input. Includes partial matches.
     * @param specificDoctorByName
     * @return
     */
    public String searchDocsByNameContact(String specificDoctorByName) {
        String doctorByName = "";
        for (int i = 0; i < numberOfDoctors(); i++) {
            if (getDoctor(i).getName().contains(specificDoctorByName)) {
                doctorByName += i + getDoctor(i).viewContactDetails();
            }
        }
        if (doctorByName.equals("")) {
            doctorByName = "There are no doctors by that name";
        }
        return doctorByName;
    }

    /**
     * Calculates annual fees. Returns int.
      * @return
     */
    public int calculateAnnualFees() {
        double runningTotal = 0;
        for (int i = 0; i < numberOfDoctors(); i++) {
            runningTotal += getDoctor(i).calcRegistrationFee();
        }

        return (int) runningTotal;
    }

    /**
     * Loads Doctor IDCount from .xml file. Sets the current count to the loaded value. Throws Exception - caught in driver.
     * @throws Exception
     */
    public void loadIdCount() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream iDis = xstream.createObjectInputStream(new FileReader("doctorsIdCount.xml"));
        Doctor.setDocIdNumber(iDis.readObject());
        iDis.close();
    }

    /**
     * Loads the doctors from the .xml file and adds them to existing ArrayList of doctos.Throws Exception - handled in Driver.
     * @throws Exception
     */
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("doctors.xml"));
        doctors.addAll((ArrayList<Doctor>) is.readObject());
        is.close();
    }

    /**
     * Saves current added doctor to .xml and saves the current ID count to seperate .xml. Throws Exception - handled in Driver.
     * @throws Exception
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("doctors.xml"));
        out.writeObject(doctors);
        out.close();
        ObjectOutputStream idOut = xstream.createObjectOutputStream(new FileWriter("doctorsIdCount.xml"));
        idOut.writeObject(Doctor.getIdCountnumber());
        idOut.close();
    }


}
