package tacos.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true) // JAP에서는 개체가 인자 없는 생성자를 가져아 하므로 어노테이션 지정
@Entity // 해당 클래스를 JAP 개체로 선언하려면 반드시 추가해야함
public class Ingredient {
	
	// Id 어노테이션을 추가하면 이 속성이 데이터베이스의 개체를 고유하게 식별한다는 것을 나타냄
	@Id
	private final String id;
	private final String name;
	private final Type type;
	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

}
