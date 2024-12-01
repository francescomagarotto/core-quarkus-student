package org.fmagarot.resources;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StudentResource {
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phone;
}
