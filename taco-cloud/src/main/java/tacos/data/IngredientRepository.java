package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.vo.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{
	
	// CrudRepository 인터페이스에는 데이터베이스의 CURD 연산을 위한 메서드가 선언되어있음.
	// 첫번째 매개변수는 리퍼지터리에 저장되는 개체타입, 두번쨰 매개변수는 개체 id 속성의 타입이다.
	// Iterable<Ingredient> findAll();
	// Ingredient findById(String id);
	// Ingredient save(Ingredient ingredient);

}
