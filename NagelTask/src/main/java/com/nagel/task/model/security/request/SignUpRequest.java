package com.nagel.task.model.security.request;

import java.util.HashSet;
import java.util.Set;

public class SignUpRequest {

    private String username;
    private Set<String> role = new HashSet<>();
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
