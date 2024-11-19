package learning.DSWII.code.business.user2.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoLogin {
    @NotBlank(message = "- El usuario no puede estar en blanco")
    private String nameUser;
    @NotBlank(message = "- La contraseña no puede estar en blanco")
    private String password;
}
