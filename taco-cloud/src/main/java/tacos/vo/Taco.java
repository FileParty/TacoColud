package tacos.vo;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {
	
	@NotNull
	@Size(min=5, message="이름은 최소 5글자 이상입니다.")
	private String name;
	
	@Size(min=1, message="최소 1개를 선택해야합니다.")
	private List<String> ingredients;

}
