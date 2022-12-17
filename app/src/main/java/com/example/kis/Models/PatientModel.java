package com.example.kis.Models;

import androidx.annotation.NonNull;

public class PatientModel {
    private int patientId;
    private String preName;
    private String name;
    private int birthDate;
    //constructor
    public PatientModel(int patientId, String preName, String name, int birthDate) {
        this.patientId = patientId;
        this.preName = preName;
        this.name = name;
        this.birthDate = birthDate;
    }
    public PatientModel() {
    }
    //toString prints all
    @NonNull
    @Override
    public String toString() {
        return "PatientModel{" +
                "patientId=" + patientId +
                ", preName='" + preName +
                ", name=" + name +
                ", birthDate=" + birthDate +
                '}';
    }
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public String getBirthDateS() {return "" + birthDate;}

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }
}