package uz.malga.logisticcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.malga.logisticcompany.entity.User;
import uz.malga.logisticcompany.payload.ApiResponse;
import uz.malga.logisticcompany.payload.SignIn;
import uz.malga.logisticcompany.payload.UserDto;
import uz.malga.logisticcompany.secret.CurrentUser;
import uz.malga.logisticcompany.secret.JwtFilter;
import uz.malga.logisticcompany.service.UserService;
import uz.malga.logisticcompany.utils.AppConstants;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JwtFilter jwtFilter;


    @PreAuthorize("hasAuthority('ADD_EMPLOYEE')")
    @PostMapping("/saveOrEdit")
    public HttpEntity<ApiResponse> userAdd(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.userSave(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? apiResponse.getMessage().equals("Saved") ? 201 : 202 : 409).body(apiResponse);

    }

//    @PreAuthorize("hasAuthority('EDIT_EMPLOYEE')")
//    @PostMapping("/edit/{id}")
//    public HttpEntity<ApiResponse> edit(@PathVariable UUID id, @RequestBody UserDto userDto) {
//        ApiResponse apiResponse = userService.edited(id, userDto);
//
//        return ResponseEntity.status(apiResponse.isSuccess() ? apiResponse.getMessage().equals("Edited") ? 201 : 202 : 409).body(apiResponse);
//
//    }

    @PreAuthorize("hasAuthority('GET_EMPLOYEE')")
    @GetMapping("/allByPageable")
    public HttpEntity<?> all(
            @RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(value = "search",defaultValue = "all") String search
    ) throws IllegalAccessException {
        return ResponseEntity.ok(userService.getAllUsersByPageble(page,size,search));
    }

    @GetMapping("/changeActive/{id}")
    public HttpEntity<?> changeActive(@PathVariable UUID id){
        return ResponseEntity.ok(userService.changeActive(id));
    }

    @PreAuthorize("hasAuthority('DELETE_EMPLOYEE')")
    @GetMapping("/delete/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable UUID id) {
        ApiResponse apiResponse = userService.delete(id);

        return ResponseEntity.status(apiResponse.isSuccess() ? apiResponse.getMessage().equals("Delete") ? 201 : 202 : 409).body(apiResponse);

    }


    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody SignIn signIn) {
        return ResponseEntity.ok(userService.login(signIn));
    }


    @GetMapping("/me")
    public HttpEntity<?> me(@CurrentUser User user){
        return ResponseEntity.status(user!=null?200:409).body(user);
    }

}
