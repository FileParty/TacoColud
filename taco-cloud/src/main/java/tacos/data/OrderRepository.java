package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.vo.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
	/*Order save(Order order);*/

}
