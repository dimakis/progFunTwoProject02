package models;

import controllers.DoctorAPI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class DoctorTest {
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
    public void viewContactDetails() {

    }


    @Test
    public void getDocIdNumber() {
    }

    @Test
    public void getIdCountnumber() {
    }

    @Test
    public void setDocIdNumber() {
    }
}