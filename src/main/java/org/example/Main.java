package org.example;

import org.example.models.Pet;

public class Main {
    public static void main(String[] args) {
        User user = new User();


        user.toBuilder().
                age(34)
                .username("Rodion")
                .password("qaz123!!!")
                .build();


        Pet pet = new Pet();
    }

}