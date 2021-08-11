package tacos.data;

import tacos.vo.Order;

public interface OrderRepository {
	
	Order save(Order order);

}
