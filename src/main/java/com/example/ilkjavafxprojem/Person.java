package com.example.ilkjavafxprojem;

public abstract class Person{
    private String name;
    private String id;

    public Person(String name, String id) //constructor
    {
        this.name = name;
        this.id = id;
    }

      public String getName() {//isim döndürür
        return name;
    }//get name

    public String getId() {//id döndürür
        return id;
    }//get id
}
