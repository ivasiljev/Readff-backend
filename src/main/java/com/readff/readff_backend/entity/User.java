package com.readff.readff_backend.entity;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Map<String, List<String> > attributes;
}
