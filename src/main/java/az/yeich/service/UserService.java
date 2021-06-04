package az.yeich.service;

import az.yeich.model.User;

public interface UserService {
    User fetchUserById(String userId);
}
