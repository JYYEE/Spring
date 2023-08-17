package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Member {
	@NotBlank(message = "ID비었다.") //  hibernate-validator 라이브러리 추가해야 사용가능. 
	private String userId = "a001";
	@NotBlank(message = "이름 비었다.")
	@Size(max = 3) 
	private String userName = "hongkd";
	@NotBlank(message = "PW비었다.")
	private String password = "1234";
	private int coin = 100;
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd") // registerByGet02의 요청 파라미터 패턴 설정
	private Date dateOfBirth;
	@Valid // 중첩된 자바빈즈의 입력값 검증을 지정한다. 
	private Address address; // member 필드로 address객체 선언했을 때 데이터 바인딩 되는지 확인 -> name을 address.address의 필드 로 설정해야 가능
	@Valid // 중첩된 자바빈즈의 입력값 검증을 지정한다. 
	private List<Card> cardList;
	@Email
	private String email;
	private String gender;
	private String hobby;
	private String[] hobbyArray;
	private boolean foreigner;
	private String nationality;
	private String developer;
	private String cars;
	private String[] carArray;
	private List<String> carList;
	private List<String> hobbyList;
	private String introduction;
	private String birthDay;
}
