package learning.DSWII.code.business.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoInsert {
    @NotBlank(message = "- El nombre no puede estar en blanco")
    private String firstName;
    @NotBlank(message = "- El apellido no puede estar en blanco")
    private String surName;
    @Pattern(regexp = "^\\d{8}$", message = "- No es un DNI válido")
    private String dni;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "- No es un correo electrónico válido")
    private String email;
}
