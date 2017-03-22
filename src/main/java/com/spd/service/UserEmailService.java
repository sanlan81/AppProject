package com.spd.service;

import com.spd.bean.UserEmailBean;
import com.spd.entity.User;
import com.spd.entity.UserEmail;
import com.spd.mapper.ObjectMapper;
import com.spd.repository.UserEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEmailService {

    private final ObjectMapper objectMapper;

    private final UserService userService;
    private final UserEmailRepository userEmailRepository;

    @Autowired
    public UserEmailService(UserEmailRepository userEmailRepository, UserService userService, ObjectMapper objectMapper) {
        this.userEmailRepository = userEmailRepository;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    public void saveUserEmail(String emailUser, String extraEmail) {
        Optional<User> userOptional = userService.getByEmail(emailUser);
        if (userOptional.isPresent()) {
            int userId = userOptional.get().getId();
            Optional<UserEmail> userEmailOptional = userEmailRepository.findByUserIdAndEmail(userId, extraEmail);
            if (!userEmailOptional.isPresent()) {
                createUserEmail(userId, extraEmail);
            }
        }
    }

    private void createUserEmail(int userId, String email) {
        User user = userService.getById(userId);
        UserEmail userEmail = new UserEmail();
        userEmail.setEmail(email);
        userEmail.setUser(user);
        userEmailRepository.save(userEmail);
    }

    public void deleteUserEmail(int idEmail) {
        userEmailRepository
                .findOneById(idEmail)
                .ifPresent(userEmailRepository::delete);
    }

    public List<UserEmailBean> getListByUserEmail(String email) {
        Optional<User> userOptional = userService.getByEmail(email);
        List<UserEmail> userEmails = userEmailRepository.findByUserId(userOptional.get().getId());
        return objectMapper.mapAsList(userEmails, UserEmailBean.class);
    }

    public UserEmail getUserEmail(String email) {
        Optional<UserEmail> userEmailOptional = userEmailRepository.findOneByEmail(email);
        if (userEmailOptional.isPresent()) {
            return userEmailOptional.get();
        }
        throw new NullPointerException("Not found user with email");
    }
}
