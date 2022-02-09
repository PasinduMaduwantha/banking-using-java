package main.java;

public class Customer {
// A customer has some attributes
    String name;
    String id;
    int age;
    String gender;
    float balance;
    String date;

    //make getters and setters
    public String getDate() {return date;}
    public void setDate(String date) { this.date = date;}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }
}
