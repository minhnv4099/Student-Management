/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataObject;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.ArrayList;
/**
 *
 * @author nguyenminh
 */


public class Student<K,V> {
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String numberPhone;
    private String birthDay;
    private HashMap<K,V>subList;
    
    public Student(String id, String firstName, String lastName, String gender, String birthDay, String email, String numberPhone) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setGender(gender);
        this.setBirthDay(birthDay);
        this.setEmail(email);
        this.setNumberPhone(numberPhone);
        this.subList = new HashMap<K, V>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
    
    public HashMap<K,V> getSubList(){
        return this.subList;
    }
    
    
    @Override
    public String toString() {
        return String.join("_",this.id,this.firstName,this.lastName,
                this.gender,this.birthDay,this.email,this.numberPhone);
    }
    
        @Override
    public int hashCode() {
        return 1;
    }

    
    
}
