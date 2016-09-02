package com.android.khaled.assuit_guide.Json;

import com.android.khaled.assuit_guide.Model.Model_Item;

/**
 * Created by khaled on 28/08/16.
 */
public class Json_Response {
    private String result;
    private Model_Item[] data ;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Model_Item[] getData() {
        return data;
    }

    public void setData(Model_Item[] data) {
        this.data = data;
    }
}
