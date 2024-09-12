package org.example.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private long id;
    private String name;
    private String status;
}
