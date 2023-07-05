package ForPets.JWT;

import ForPets.Entity.MemberEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class UserDetailsImpl implements UserDetails {

    private MemberEntity member;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl from(MemberEntity member){
        log.warn("유저디테일 임플");
        SimpleGrantedAuthority simpleGrantedAuthority =
                new SimpleGrantedAuthority(member.getUsingRole().toString());
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(simpleGrantedAuthority);

        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.member = member;
        userDetails.authorities = collection;
        log.warn(simpleGrantedAuthority.toString());
        return userDetails;
    }

    public MemberEntity getUser(){
        return member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
