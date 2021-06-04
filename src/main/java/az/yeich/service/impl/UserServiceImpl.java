package az.yeich.service.impl;

import az.yeich.exception.UserNotFoundException;
import az.yeich.model.User;
import az.yeich.respository.UserRepository;
import az.yeich.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User fetchUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }
}
