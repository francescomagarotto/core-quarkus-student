package org.fmagarot.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StudentResource {

  @NotEmpty(message = "Student name must be present")
  private String name;
  @NotEmpty
  @Email(message = "Email must be well formed")
  private String email;
  @NotEmpty
  @Pattern(regexp = "^[0-9]+$", message = "Phone number must be numeric")
  private String phone;
}
