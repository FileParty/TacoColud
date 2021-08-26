package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class NoEncodingPasswordEncoder implements PasswordEncoder{
	
	// 암호화를 시키지 않기 위해 인터페이스를 구현해서 반환하는 클래스(데이터가 암호화안됨) -- 2021.08.26
	
	@Override
	public String encode(CharSequence rawPassword) {
		System.out.println("encode:" + rawPassword);
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		System.out.println(rawPassword + "\n" + encodedPassword);
		return rawPassword.equals(encodedPassword);
	}
	
	

}
