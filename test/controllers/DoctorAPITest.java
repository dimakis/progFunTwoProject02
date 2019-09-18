package controllers;

import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;


public class DoctorAPITest {

    DoctorAPI doctorList;
    DoctorAPI doctorNone;

    Doctor doctorIntern;
    Doctor doctorGeneral;
    Doctor doctorSpecialist;
    Doctor doctorConsultant;

    Qualification qualOne;
    Qualification qualTwo;

    @Before
    public void setUp() {

        doctorIntern = new Intern("Mike", 1, "26/03/1990", 'm', "", "#1", new ArrayList<Qualification>());
        doctorConsultant = new Consultants("Dimitri", 2, "26/03/1990", 'm', "", "#2", new ArrayList<Qualification>(), new HashSet<String>(), true, new ArrayList<Publications>());
        doctorGeneral = new General("Jack", 3, "26/03/1990", 'm', "", "#3", new ArrayList<Qualification>(), false);
        doctorSpecialist = new Specialist("Mary", 4, "26/03/1990", 'f', "", "#4", new ArrayList<Qualification>(), new HashSet<String>(), true);

        // Setting up a populated list
        doctorList = new DoctorAPI();
        doctorList.addDoctor(doctorIntern);
        doctorList.addDoctor(doctorConsultant);
        doctorList.addDoctor(doctorSpecialist);
        //doctorList.addDoctor(doctorGeneral);

        //setting up unpopulated list
        doctorNone = new DoctorAPI();

        qualOne = new Qualification("level 8", "Med Stuff", "WIT", "1996");
        qualTwo = new Qualification("level 9", "Med Stuff", "CIT", "2000");


    }

    @After
    public void tearDown() {
        doctorList = doctorNone = null;
        doctorGeneral = doctorConsultant = doctorIntern = doctorSpecialist = null;
        qualOne = qualTwo = null;
    }

    @Test
   public void addDoctor() {
        //adding to populated list
        assertEquals(3, doctorList.numberOfDoctors());
        doctorList.addDoctor(doctorGeneral);
        assertEquals(doctorList.getDoctor(1), doctorConsultant);
        assertEquals(4, doctorList.numberOfDoctors());

        //adding to an empty list
        assertEquals(0, doctorNone.numberOfDoctors());
        doctorNone.addDoctor(doctorGeneral);
        assertEquals(doctorNone.getDoctor(0), doctorGeneral);
        assertEquals(1, doctorNone.numberOfDoctors());
    }

    @Test
    public void isValidDoc() {
        //checks to see if doctor by given ID is in list of doctors
        assertTrue(doctorList.isValidDoc(4));

        //check to see if doctor is not in the list of doctors
        assertFalse(doctorList.isValidDoc(6));
    }

    @Test
    public void getDocById() {
        //Checks to see if getDocById returns the right doctor from list
        assertEquals("Dimitri", doctorList.getDocById(2).getName());

        //Checks to see if a doctor that exists but is not  in list is returned or null is returned
        assertNull(doctorList.getDocById(3));
        //assertThat()
    }


    @Test
   public void getDoctor() {
        //Checks to see if correct doc by index is got
        assertEquals("Mike", doctorList.getDoctor(0).getName());

        //checks that null is returned when an index with no doctor is specified
        assertNull(doctorList.getDoctor(3));
    }

    @Test
    public void removeDocById() {
        //Checks to see if correct doctor is removed from list
        assertTrue(doctorList.removeDocById(4));

        //Checks method returns null false is no doctor has the ID number specified
        assertFalse(doctorList.removeDocById(3));
    }

    @Test
    public void removeDoctor() {
        //Test to see if correct Doc is removed by index
        assertTrue(doctorList.removeDocById(2));
        assertFalse(doctorList.isValidDoc(2));

        //Test that method doesn't remove all docs
        assertEquals("Mary", doctorList.getDoctor(1).getName());
    }

    @Test
   public void numberOfDoctors() {
        //Checks the correct number of doctors is returned from a populated list
        assertEquals(3, doctorList.numberOfDoctors());

        //Checks the correct number of doctors is returned from an empty list
        assertEquals(0, doctorNone.numberOfDoctors());
    }

    @Test
   public void listDoctors() {
        // List contains names of 3 docs in list
        assertTrue(doctorList.listDoctors().contains("Dimitri"));
        assertTrue(doctorList.listDoctors().contains("Mary"));
        assertTrue(doctorList.listDoctors().contains("Mike"));

        //List doesn't contain Jack
        assertFalse(doctorList.listDoctors().contains("Jack"));
    }

    @Test
   public void listQualifications() {
        //Checks to see if list qualifications works
        doctorList.getDocById(2).getQualifications().add(qualOne);
        doctorList.getDocById(2).getQualifications().add(qualTwo);
        assertTrue(doctorList.listQualifications(1).contains("1996"));
        assertTrue(doctorList.listQualifications(1).contains("2000"));
    }

    @Test
   public void listContactDeets() {
        //tests to see if correct details are passed to string as is contact number
        assertTrue(doctorList.listContactDeets().contains("#2"));

        //test to see if docs not in list contacts are in string by checking contact number
        assertFalse(doctorList.listContactDeets().contains("#3"));
    }

    @Test
   public void doctorByCategory() {
        // checks to see if method returns the correct string - category4(Consultants)
        // as Mike is an intern
        assertTrue(doctorList.doctorByCategory(4).contains("Dimitri"));

        // Dimitri does not appear in Category 1, 2, or 3
        assertFalse(doctorList.doctorByCategory(1).contains("Dimitri"));
        assertFalse(doctorList.doctorByCategory(2).contains("Dimitri"));
        assertFalse(doctorList.doctorByCategory(3).contains("Dimitri"));

    }

    @Test
    public void docByCategoryArr() {
        //Checks to see if category 4(Consultants) contains doctorConsultant
        assertTrue(doctorList.docByCategoryArr(4).get(0).equals(doctorList.getDocById(2)));

        //tests to see if category
        assertFalse(doctorList.docByCategoryArr(3).get(0).equals(doctorList.getDocById(2)));
    }

    @Test
    public void searchDoctorsByName() {
        //test to see if doctor list returns doctor in list
        assertTrue(doctorList.searchDoctorsByName("Mik").contains("Mike"));

        //removing mike
        doctorList.removeDocById(1);

        //testing for mike
        assertFalse(doctorList.searchDoctorsByName("Mike").contains("Mike"));
    }
}