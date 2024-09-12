package org.example.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Pet {
    private long id;
    private String name;
    private String status;
}
