package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.srmserver.entitiy.Category;
import uz.pdp.srmserver.entitiy.Role;
import uz.pdp.srmserver.entitiy.User;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.SignIn;
import uz.pdp.srmserver.payload.UserDto;
import uz.pdp.srmserver.payload.UserEditDto;
import uz.pdp.srmserver.repository.RoleRepository;
import uz.pdp.srmserver.repository.UserRepository;
import uz.pdp.srmserver.secret.JwtProvider;
import uz.pdp.srmserver.utils.CommonUtills;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtProvider jwtProvider;


    public ApiResponse userSave(UserDto userDto) {

        User user = new User();

        try {
            if (userDto.getId()!=null){
                user=userRepository.getById(userDto.getId());
            }
            List roles=new ArrayList();
            roles.add(roleRepository.getById(userDto.getRole()));
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setUsername(userDto.getUsername());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setRoles(roles);
            user.setActive(userDto.isActive());
            userRepository.save(user);
            return new ApiResponse(userDto.getId()!=null?"Saved":"Edited", true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("error",false);
    }


//    public ApiResponse edited(UUID id, UserDto userDto) {
//
//        User user = userRepository.getById(id);
//        user.setFirstName(userDto.getFirstName() == null ? user.getLastName() : userDto.getFirstName());
//        user.setLastName(userDto.getLastName() == null ? user.getLastName() : userDto.getLastName());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword() == null ? user.getPassword() : userDto.getPassword()));
//        user.setUsername(userDto.getUsername() == null ? user.getUsername() : userDto.getUsername());
//
//        user.setPhoneNumber(userDto.getPhoneNumber() == null ? user.getPhoneNumber() : userDto.getPhoneNumber());
//        user.setRoles(userDto.getRole() == null ? user.getRoles() : roleRepository.findAllByIdIn(userDto.getRole()));
//        user.setActive(userDto.isActive());
//        userRepository.save(user);
//        return new ApiResponse("Edited", true);
//    }


    public String login(SignIn signIn) {
        Authentication authenticate = manager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        User principal = (User) authenticate.getPrincipal();
        String token = jwtProvider.generateToken(principal);
        return token;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();

    }


    public Object profile(UserEditDto userEditDto, User user) {
        user.setPassword(passwordEncoder.encode(userEditDto.getPassword()));
        userRepository.save(user);
        return "Edited!";
    }

    public ApiResponse delete(UUID id) {
userRepository.deleteById(id);

return new ApiResponse("Delete",true);

    }
    public static UserDto getUserDto(User user){
        UserDto dto=new UserDto();

        dto.setRoles(user.getRoles());
        dto.setUsername(user.getUsername());
        dto.setActive(user.isActive());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setFirstName(user.getFirstName());
        dto.setId(user.getId());
        dto.setFixSalary(user.getFixSalary());

        return dto;
    }

    public ApiResponse getAllUsersByPageble(Integer page, Integer size, String search) throws IllegalAccessException {

        List<User> users = new ArrayList<>();
        long totalElements = 0;
        if (!search.equals("all")) {
            users = userRepository.findAllByUsernameContaining(search);
        } else {
            if (size > 0) {
                Page<User> userPage = userRepository.findAll(CommonUtills.getPageableByIdDesc(page, size));
                users = userPage.getContent();
                totalElements = userPage.getTotalElements();
            } else {
                users = userRepository.findAll();
            }
        }
        return new ApiResponse(true, "USerPage", users.stream().map(UserService::getUserDto).collect(Collectors.toList()), totalElements);
    }
    public ApiResponse changeActive(UUID id) {
        User byId = userRepository.getById(id);
        byId.setActive(!byId.isActive());
        userRepository.save(byId);
        return new ApiResponse(byId.isActive() ? "Activated" : "Blocked", true);
    }
}

