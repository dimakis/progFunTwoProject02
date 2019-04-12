package models;

import java.util.ArrayList;
import java.util.HashSet;

public class Publications {
    private String articleName;
    private String datePublished;
    private int timesCited;
    private HashSet<String> areaOfExpertise = new HashSet<>();
    private boolean irishMedicalJournal;
    private ArrayList<Publications> publications = new ArrayList<>();


    public Publications(String articleName, String datePublished, int timesCited, HashSet areaOfExpertise, boolean irishMedicalJournal) {
        this.articleName = articleName;
        this.datePublished = datePublished;
        this.timesCited = timesCited;
        this.areaOfExpertise = areaOfExpertise;
        this.irishMedicalJournal = irishMedicalJournal;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public int getTimesCited() {
        return timesCited;
    }

    public void setTimesCited(int timesCited) {
        this.timesCited = timesCited;
    }

    public HashSet<String> getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(HashSet areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    public boolean isIrishMedicalJournal() {
        return irishMedicalJournal;
    }

    public void setIrishMedicalJournal(boolean irishMedicalJournal) {
        this.irishMedicalJournal = irishMedicalJournal;
    }

    @Override
    public String toString() {
            String areaOfExper = "";
            ArrayList<String> expertise = new ArrayList<>(areaOfExpertise);
        for (int i = 0; i < expertise.size(); i++) {
            areaOfExper += "\n" + "\t" + "\t" + expertise.get(i);
        }
        return "\t" + "Article Name: " + articleName + "\n" +
                "\t" +"Date Published: " + datePublished + "\n" +
                "\t" +"Times Cited: " + timesCited + "\n" +
                "\t" +"Area Of Expertise: " + areaOfExper + "\n" +
                "\t" +"Published in Irish Medical Journal: " + irishMedicalJournal + "\n";
    }
}
