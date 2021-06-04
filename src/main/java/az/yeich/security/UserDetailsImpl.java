package az.yeich.security;

import az.yeich.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private final String Id;
    private final String email;
    private final String username;
    private final String password;
    private final Boolean isEmailVerified;


    public UserDetailsImpl(String Id, String email, String username, String password, boolean isEmailVerified){
        this.Id = Id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isEmailVerified = isEmailVerified;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getFirstname(),
                user.getPassword(),
                user.isEmailVerified()
        );
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public boolean getIsEmailVerified() {
        return this.isEmailVerified;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return this.Id;
    }

    public String getEmail() {
        return this.email;
    }
}
