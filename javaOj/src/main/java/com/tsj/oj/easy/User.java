package com.tsj.oj.easy;
import lombok.*;

@Data
@EqualsAndHashCode
public class User {
    int id;
    String name = null;

    public User(int id){
        this.id = id;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "(" + id + ", " + name + ")";
    }

    public String getName() {
        return name;
    }
}