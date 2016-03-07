package com.geverson.people;

import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

/**
 * Created by gnco on 03/03/2016.
 */
@Entity
@Table(name = "people")
public class People {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String location;

    @Column
    private Integer age;

    public People() {
    }

    public People(String firstName, String lastName, String location, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
