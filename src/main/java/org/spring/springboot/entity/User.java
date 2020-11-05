package org.spring.springboot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = -3493284972656442685L;

    private int id;
    private String name;
    private int age;

}
