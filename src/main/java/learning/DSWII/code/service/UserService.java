package learning.DSWII.code.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learning.DSWII.code.dto.DtoUser;
import learning.DSWII.code.entity.TUser;
import learning.DSWII.code.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> insert(DtoUser dtoUser){

        if (userRepository.findByEmail(dtoUser.getEmail()).isPresent() &&
        userRepository.findByDni(dtoUser.getDni()).isPresent()) {
            return new HashMap<String, Object>() {{
                put("type", "error");
                put("message", "El correo electrónico y el DNI ya están en uso.");
            }};
        }else if(userRepository.findByEmail(dtoUser.getEmail()).isPresent()){
            return new HashMap<String, Object>() {{
                put("type", "error");
                put("message", "El correo electrónico ya está en uso.");
            }};
        }else if(userRepository.findByDni(dtoUser.getDni()).isPresent()){
            return new HashMap<String, Object>() {{
                put("type", "error");
                put("message", "El DNI ya está en uso.");
            }};
        }
        dtoUser.setIdUser(UUID.randomUUID().toString());
        try{
        TUser tUser=new TUser();

        tUser.setIdUser(dtoUser.getIdUser());
		tUser.setFirstName(dtoUser.getFirstName());
		tUser.setSurName(dtoUser.getSurName());
		tUser.setDni(dtoUser.getDni());
        tUser.setEmail(dtoUser.getEmail());

		userRepository.save(tUser);
        return new HashMap<String, Object>() {{
            put("type", "success");
            put("message", "El usuario se inserto exitosamete.");
            put("data",tUser);
        }};
    }catch(Exception e){
        return new HashMap<String, Object>() {{
            put("type", "exception");
            put("message", "Error al insertar usuario: " + e.getMessage());
        }};
    }
    }

    public List<DtoUser> getAll() {
        List<TUser> listTUser = userRepository.findAll();

        List<DtoUser> listDtoUser = new ArrayList<>();

        for (TUser tUser : listTUser) {
            DtoUser dtoUser = new DtoUser();

            dtoUser.setIdUser(tUser.getIdUser());
            dtoUser.setFirstName(tUser.getFirstName());
            dtoUser.setSurName(tUser.getSurName());
            dtoUser.setDni(tUser.getDni());
            dtoUser.setEmail(tUser.getEmail());

            listDtoUser.add(dtoUser);
        }


        return listDtoUser;
    }

    /*public boolean delete(String idUser) {
        userRepository.deleteById(idUser);

        return true;
    }*/
    public Map<String, String> delete(String idUser) {
        if (!userRepository.existsById(idUser)) {
            return new HashMap<String, String>() {{
                put("type", "error");
                put("message", "El usuario no se encontró.");
            }};
        }

        try {
            userRepository.deleteById(idUser);
            return new HashMap<String, String>() {{
                put("type", "success");
                put("message", "Usuario eliminado exitosamente.");
            }};
        } catch (Exception e) {
            return new HashMap<String, String>() {{
                put("type", "exception");
                put("message", "Error al eliminar usuario: " + e.getMessage());
            }};
        }
    }
}
