package com.android.khaled.assuit_guide.rest;

import com.android.khaled.assuit_guide.Json.Departments_Response;
import com.android.khaled.assuit_guide.Json.Json_Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("resturants.php")
    Call<Json_Response> getResturants();

    @GET("labs.php")
    Call<Json_Response> getLabs();

    @GET("pharmacy.php")
    Call<Json_Response> getPharmacy();

    @GET("hotels.php")
    Call<Json_Response> getHotels();

    @GET("semsars.php")
    Call<Json_Response> getSemsars();

    @GET("departments.php")
    Call<Departments_Response> getDepartments();

    @GET("hospitals.php")
    Call<Json_Response> getHospitals();

    @GET("s_hospital.php")
    Call<Json_Response> getS_Hospitals();

    @GET("clinics.php")
    Call<Json_Response> getClinics(@Query("id") int id);

}
