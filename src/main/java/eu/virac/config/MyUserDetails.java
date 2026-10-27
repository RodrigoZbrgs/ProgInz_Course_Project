package eu.virac.config;

import java.util.ArrayList;
import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import eu.virac.model.Users;
import eu.virac.model.security.MyAuthority;

public class MyUserDetails implements UserDetails {
	private Users user;
	public MyUserDetails(Users user) {
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> authoritiesForSecurity=
				new ArrayList<>();
		for(MyAuthority tempA:user.getAuthorities()) {
			SimpleGrantedAuthority sga= new SimpleGrantedAuthority(tempA.getTitle());
			authoritiesForSecurity.add(sga);
		}
		return authoritiesForSecurity;
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}
}
