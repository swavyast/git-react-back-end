package com.ml.gitmanager.entities;

public enum Gender {

    MALE("Male"), FEMALE("Female"), OTHERS("Other");
    
    String value;

    Gender(String value){
        this.value = value;
    }

}
