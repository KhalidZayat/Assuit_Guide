package com.android.khaled.assuit_guide.Json;

import com.android.khaled.assuit_guide.Model.Department;

/**
 * Created by khaled on 02/09/16.
 */
public class Departments_Response
{
    private String result;
    private Department[] data ;

    public Departments_Response(String result, Department[] data) {
        this.result = result;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Department[] getData() {
        return data;
    }

    public void setData(Department[] data) {
        this.data = data;
    }
}

