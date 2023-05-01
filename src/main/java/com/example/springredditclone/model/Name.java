package com.example.springredditclone.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class Name {
    private String firstname;
    private String middlename;
    private String lastname;
}
