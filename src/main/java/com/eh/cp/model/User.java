package com.eh.cp.model;

import lombok.Getter;
import lombok.Setter;
/**
 * @author MD. EMRAN HOSSAIN
 * @since 24 MAR, 2023
 * @version 1.1
*/
@Getter
@Setter
public class User {
    private long id;
    private String name;
    private String username;
    private String password;
    private String email;

    public User(String name, String username,  String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
