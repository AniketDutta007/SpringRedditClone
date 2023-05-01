package com.example.springredditclone.dto;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@Embeddable
public class NameDTO {
    @NotBlank(message = "First name can't be null")
    private String firstname;
    @Nullable
    private String middlename;
    @Nullable
    private String lastname;
}
