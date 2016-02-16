package com.lifeistech.android.network.api;

import com.lifeistech.android.network.entity.Employees;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by rild on 16/02/16.
 */
public interface EmployeesAPI {
    @GET("/get_names.json")
    public void getEmployees(Callback<Employees> response);
}
