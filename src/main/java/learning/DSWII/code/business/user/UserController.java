package learning.DSWII.code.business.user;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import learning.DSWII.code.business.user.request.SoInsert;
import learning.DSWII.code.business.user.response.ResponseDelete;
import learning.DSWII.code.business.user.response.ResponseGetAll;
import learning.DSWII.code.business.user.response.ResponseInsert;
import learning.DSWII.code.dto.DtoUser;

import learning.DSWII.code.service.UserService;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "insert", consumes = { "multipart/form-data" })
    public ResponseEntity<ResponseInsert> actionInsert(@Valid @ModelAttribute SoInsert soInsert,
            BindingResult bindingResult) {
                ResponseInsert responseSoInsert = new ResponseInsert();
        try {
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(error -> {
                    responseSoInsert.addResponseMessage(error.getDefaultMessage());
                });
                return new ResponseEntity<>(responseSoInsert, HttpStatus.OK);
            }
            DtoUser dtoUser = new DtoUser();

            dtoUser.setFirstName(soInsert.getFirstName());
            dtoUser.setSurName(soInsert.getSurName());
            dtoUser.setDni(soInsert.getDni());
            dtoUser.setEmail(soInsert.getEmail());

            Map<String, Object> response =userService.insert(dtoUser);
            if ("success".equals(response.get("type"))) {
                responseSoInsert.success();
                responseSoInsert.addResponseMessage(response.get("message").toString());
                responseSoInsert.setData(response.get("data"));
            }
            else if("error".equals(response.get("type"))){
                responseSoInsert.error();
                responseSoInsert.addResponseMessage(response.get("message").toString());
            }
            else if("exception".equals(response.get("type"))){
                responseSoInsert.exception();
                responseSoInsert.addResponseMessage(response.get("message").toString());
            }

            return new ResponseEntity<>(responseSoInsert, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(responseSoInsert, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getall")
    public ResponseEntity<ResponseGetAll> actionGetAll() {
		ResponseGetAll responseSoGetAll = new ResponseGetAll();

		List<DtoUser> listDtoUser = userService.getAll();

		for (DtoUser dtoUser : listDtoUser) {
			Map<String, Object> map = new HashMap<>();

			map.put("idUser", dtoUser.getIdUser());
			map.put("firstName", dtoUser.getFirstName());
			map.put("surName", dtoUser.getSurName());
			map.put("dni", dtoUser.getDni());
			map.put("email", dtoUser.getEmail());

			responseSoGetAll.dto.listUser.add(map);
		}

		responseSoGetAll.success();

		return new ResponseEntity<>(responseSoGetAll, HttpStatus.OK);
	}

    @DeleteMapping(path = "delete/{idUser}")
	public ResponseEntity<ResponseDelete> actionDelete(@PathVariable String idUser) {
		ResponseDelete responseDelete = new ResponseDelete();

        Map<String, String> response = userService.delete(idUser);
        
        if ("success".equals(response.get("type"))) {
            responseDelete.success();
            responseDelete.addResponseMessage(response.get("message"));
        }
        else if("error".equals(response.get("type"))){
            responseDelete.error();
            responseDelete.addResponseMessage(response.get("message"));
        }
        else if("exception".equals(response.get("type"))){
            responseDelete.exception();
            responseDelete.addResponseMessage(response.get("message"));
        }   

		return new ResponseEntity<>(responseDelete, HttpStatus.OK);
	}
}
