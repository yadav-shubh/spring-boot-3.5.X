package com.shubham.beandefinition;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class Dog {
    private String name;
    private String breed;
    private int age;
    private boolean vaccinated;

    public Dog(){
        log.info("Dog constructor called");
    }
}
