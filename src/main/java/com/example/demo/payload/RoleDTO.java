package com.example.demo.payload;

import com.example.demo.entity.enums.Authority;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NonNull
    private Set<Authority> authorities;
}
