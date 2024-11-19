package learning.DSWII.code.business.user2;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import learning.DSWII.code.business.user2.request.SoLogin;
import learning.DSWII.code.business.user2.response.ResponseLogin;
import learning.DSWII.code.service.User2Service;

@RestController
@RequestMapping("user2/public/")
public class User2Controller {
    @Autowired
    private User2Service userService;

    @PostMapping(path = "login", consumes = { "multipart/form-data" })
    public ResponseEntity<ResponseLogin> actionLogin(@Valid @ModelAttribute SoLogin soLogin,
            BindingResult bindingResult) {
                ResponseLogin responseSoLogin = new ResponseLogin();
        try {
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(error -> {
                    responseSoLogin.addResponseMessage(error.getDefaultMessage());
                });
                return new ResponseEntity<>(responseSoLogin, HttpStatus.OK);
            }

            Map<String, Object> response =userService.login(soLogin.getNameUser(),soLogin.getPassword());

            if ("success".equals(response.get("type"))) {
                responseSoLogin.success();
                responseSoLogin.addResponseMessage(response.get("message").toString());
                responseSoLogin.setData(response.get("data"));
                responseSoLogin.setToken(response.get("token"));
            }
            else if("error".equals(response.get("type"))){
                responseSoLogin.error();
                responseSoLogin.addResponseMessage(response.get("message").toString());
            }
            else if("exception".equals(response.get("type"))){
                responseSoLogin.exception();
                responseSoLogin.addResponseMessage(response.get("message").toString());
            }

            return new ResponseEntity<>(responseSoLogin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(responseSoLogin, HttpStatus.BAD_REQUEST);
        }
    }
}