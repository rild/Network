package com.lifeistech.android.network.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rild on 16/02/16.
 */
public class Employees {
    @SerializedName(value="employees")
    public List<employeesclass> employees;

    public void setEmployees(List<employeesclass> employees) {
        this.employees = employees;

    }

    // employeesclass definition
    public static class employeesclass {
        String firstName;
        String lastName;


        @Override
        public String toString() {
            return (firstName + " " + lastName);

        }
    }

}