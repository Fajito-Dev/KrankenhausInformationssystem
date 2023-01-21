package com.example.kis.Models;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class PatientModel {
    private int patientId;
    private String preName;
    private String name;
    private String birthDate;
    //constructor
    public PatientModel(int patientId, String preName, String name, String birthDate) {
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

    public String getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate bD = LocalDate.parse(birthDate, formatter);
        LocalDate now = LocalDate.now();
        Period age = Period.between(bD, now);
        return Integer.toString(age.getYears());
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getFullName(){return preName+ " " +name;}
}