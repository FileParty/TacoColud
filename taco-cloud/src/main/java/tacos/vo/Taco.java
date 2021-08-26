package tacos.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // 데이터베이스가 자동으로 Taco의 id값을 생성하므로 해당 어노테이션 사용
	private long id;
	private Date createAt;
	
	@NotNull
	@Size(min=5, message="이름은 최소 5글자 이상입니다.")
	private String name;
	
	@ManyToMany(targetEntity=Ingredient.class) // 여러 객체(List등) 의 속성값은 ManyToMany속성에 값으로 어떤 클래스를 가지는지 지정
	@Size(min=1, message="최소 1개를 선택해야합니다.")
	private List<Ingredient> ingredients;
	
	@PrePersist // Taco 객체가 저장되기 전 createAt속성을 현재 날짜값 입력
	void createdAt() {
		this.createAt = new Date();
	}

}
