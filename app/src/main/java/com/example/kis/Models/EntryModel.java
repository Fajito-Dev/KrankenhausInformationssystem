package com.example.kis.Models;

public class EntryModel {
    private int eintragId;
    private int patientIde;
    private int date;
    private int bedNr;
    private int visited;
    private String condition;
    private boolean mrt;
    private boolean bloodtest;
    private String note;
    private float leukoNl;
    private float lymphoProzent;
    private float lymphoAbsolut;

    public EntryModel(int patientIde, int date, int bedNr, boolean visited, String condition, boolean mrt, boolean bloodtest, String note, float leukoNl, float lymphoProzent, float lymphoAbsolut) {
        this.patientIde = patientIde;
        this.date = date;
        this.bedNr = bedNr;
        this.visited = visited;
        this.condition = condition;
        this.mrt = mrt;
        this.bloodtest = bloodtest;
        this.note = note;
        this.leukoNl = leukoNl;
        this.lymphoProzent = lymphoProzent;
        this.lymphoAbsolut = lymphoAbsolut;
    }
    public EntryModel(int eintragId, int patientIde, int date, int bedNr, boolean visited, String condition, boolean mrt, boolean bloodtest, String note, float leukoNl, float lymphoProzent, float lymphoAbsolut) {
        this.eintragId = eintragId;
        this.patientIde = patientIde;
        this.date = date;
        this.bedNr = bedNr;
        this.visited = visited;
        this.condition = condition;
        this.mrt = mrt;
        this.bloodtest = bloodtest;
        this.note = note;
        this.leukoNl = leukoNl;
        this.lymphoProzent = lymphoProzent;
        this.lymphoAbsolut = lymphoAbsolut;
    }
    public EntryModel(){

    }

    public String toString() {
        return "EntryModel{" +
                "patientId=" + patientIde +
                ", bedNr='" + bedNr +
                ", visited=" + visited +
                ", condition=" + condition +
                ", mrt=" + mrt +
                ", bloodtest=" + bloodtest +
                ", note=" + note +
                '}';
    }

    public int getEintragId() {return eintragId;}

    public void setEintragId(int eintragId) {this.eintragId = eintragId;}

    public int getPatientIde() {return patientIde;}

    public void setPatientIde(int patientIde) {this.patientIde = patientIde;}

    public int getBedNr() {return bedNr;}

    public void setBedNr(int bedNr) {this.bedNr = bedNr;}

    public int getVisited() {return visited;}

    public void setVisited(int visited) {this.visited = visited;}

    public String getCondition() {return condition;}

    public void setCondition(String condition) {this.condition = condition;}

    public boolean isMrt() {return mrt;}

    public void setMrt(boolean mrt) {this.mrt = mrt;}

    public boolean isBloodtest() {return bloodtest;}

    public void setBloodtest(boolean bloodtest) {this.bloodtest = bloodtest;}

    public String getNote() {return note;}

    public void setNote(String note) {this.note = note;}

    public int getDate() {return date;}

    public void setDate(int date) {this.date = date;}

    public float getLeukoNl() {return leukoNl;}

    public void setLeukoNl(float leukoNl) {this.leukoNl = leukoNl;}

    public float getLymphoProzent() {return lymphoProzent;}

    public void setLymphoProzent(float lymphoProzent) {this.lymphoProzent = lymphoProzent;}

    public float getLymphoAbsolut() {return lymphoAbsolut;}

    public void setLymphoAbsolut(float lymphoAbsolut) {this.lymphoAbsolut = lymphoAbsolut;}
}