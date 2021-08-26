package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.vo.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long>{
	// 기본 변수들은 객체 변수로 대체..?
	/* Taco save(Taco design); */

}
