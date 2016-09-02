package com.android.khaled.assuit_guide.Model;

/**
 * Created by khaled on 02/09/16.
 */
public class Department {

    int ID;
    String Name;

    public Department(int ID, String Name) {
        this.ID = ID;
        this.Name = Name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
