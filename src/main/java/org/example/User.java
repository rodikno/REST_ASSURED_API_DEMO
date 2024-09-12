package org.example;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    String username;
    String password;
    Integer age;

}
