package kr.or.ddit.security;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kr.or.ddit.mapper.LoginMapper;
import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.vo.CustomUser;
import kr.or.ddit.vo.DDITMemberVO;

public class CustomUserDetailsService implements UserDetailsService{

	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Inject
	private BCryptPasswordEncoder pe;
	@Inject
	private LoginMapper loginMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String password = "pwd1";
		// 인코딩된 암호화 비밀번호를 알아봅니다.
		// 같은 값을 입력해도 인코딩 된 값들은 다른 값이 나옴.
		// test 할 때는 끄고 했다가 나중에 마지막에 배포할 때 시행.
		log.info("#### 인코딩된 비밀번호를 확인합니다 #### : " + pe.encode(password));
		log.info("#### 인코딩된 비밀번호를 확인합니다 #### : " + pe.encode(password));
		log.info("#### 인코딩된 비밀번호를 확인합니다 #### : " + pe.encode(password));
		log.info("#### 인코딩된 비밀번호를 확인합니다 #### : " + pe.encode(password));
		log.info("#### 인코딩된 비밀번호를 확인합니다 #### : " + pe.encode(password));
		log.info("#### 인코딩된 비밀번호를 확인합니다 #### : " + pe.encode(password));
		
		log.info("load User by username : " + username);
		
		// userName은 사용자 명이 아니라 사용자 아이디를 의미한다.
		// member를 가지고 최종 리턴해서 넘겨야할 UserDetails를 만들어준다. 
		DDITMemberVO member;
		
		try {
			member = loginMapper.readByUserId(username);
			log.info("query by member mapper : " + member);
			return member == null? null : new CustomUser(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
