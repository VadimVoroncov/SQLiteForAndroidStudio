package com.example.sqliteforandroidstudio;

public class CustomerModel {
    int id;
    String Name;
    int age;
    boolean isActive;

    // constructors
    public CustomerModel(int id, String name, int age, boolean isActive) {
        this.id = id;
        Name = name;
        this.age = age;
        this.isActive = isActive;
    }

    public CustomerModel() {
    }

    // toString is necessary for printing the contents of a class object
    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
