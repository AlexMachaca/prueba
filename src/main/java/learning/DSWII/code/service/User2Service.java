package learning.DSWII.code.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learning.DSWII.code.Jwt.JwtService;

import learning.DSWII.code.dto.DtoUserLog;

import learning.DSWII.code.entity.TUser2;
import learning.DSWII.code.repository.User2Repository;


@Service
public class User2Service {
    @Autowired
    private User2Repository userRepository;
    @Autowired
    private JwtService jwtService;


    public Map<String, Object> login(String nameUser, String password) {
        // Asumimos que existe un método en userRepository para encontrar un usuario por nombre de usuario
        Optional<TUser2> optionalUser = userRepository.findByNameUser(nameUser);
        
        if (!optionalUser.isPresent()) {
            return new HashMap<String, Object>() {{
                put("type", "error");
                put("message", "Nombre de usuario incorrecto.");
            }};
        }
        TUser2 user = optionalUser.get();
        
        // Asumimos que tienes una forma de verificar la contraseña, por ejemplo, comparando la contraseña proporcionada
        // con la almacenada en la base de datos. Este es un ejemplo simplificado.
        if (!user.getPassword().equals(password)) {
            return new HashMap<String, Object>() {{
                put("type", "error");
                put("message", "Contraseña incorrecta.");
            }};
        }

         // Si las credenciales son correctas, generar un token JWT
         String token = jwtService.generateToken(user);
        
        // Si todo es correcto, procedemos a "login" o lo que necesites hacer después de validar las credenciales
        DtoUserLog dtoUserLog=new DtoUserLog();
        dtoUserLog.setIdUser2(user.getIdUser2());
        dtoUserLog.setNameUser(user.getNameUser());
        return new HashMap<String, Object>() {{
            put("type", "success");
            put("message", "Bienvenido al sistema \""+nameUser.toUpperCase()+"\".");
            put("data",dtoUserLog);
            put("token", token);  // Añadir el token a la respuesta
        }};
    }

}
