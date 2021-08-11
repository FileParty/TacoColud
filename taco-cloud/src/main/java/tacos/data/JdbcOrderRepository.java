package tacos.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.vo.Order;
import tacos.vo.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository{
	
	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacoInserter;
	private ObjectMapper objectMapper;
	// SimpleJdbcInsert는 테이블을 지정하면 insert같은 구문을 생략해도되는 것 같다..
	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		this.orderInserter = new SimpleJdbcInsert(jdbc)
				.withTableName("Taco_Order")
				.usingGeneratedKeyColumns("id");
		
		this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
			.withTableName("Taco_order_Tacos");
		
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public Order save(Order order) {
		order.setPlacedAt(new Date());
		long orderId = saveOrderDetails(order);
		order.setId(orderId);
		List<Taco> tacos = order.getTacos();
		
		for( Taco taco : tacos) {
			saveTacoToOrder(taco,orderId);
		}
		
		return order;
	}
	
	private long saveOrderDetails(Order order) {
		// Date형은 자바에서 생성시에 long으로 생성하기에
		// objectMapper(Jackson 맵퍼라는대 잘 모르겠음. 검색필요)으로 convertValue를 실행시켜
		// 데이터 타입을 맞게 바꿔주는 역활..
		@SuppressWarnings("unchecked")
		Map<String, Object> values = 
				objectMapper.convertValue(order, Map.class);
		values.put("placedAt", order.getPlacedAt());
		
		long orderId =
				orderInserter
					.executeAndReturnKey(values)
					.longValue();
		return orderId;
	}
	
	private void saveTacoToOrder(Taco taco, long orderId) {
		Map<String, Object> values = new HashMap<>();
		values.put("tacoOrder", orderId);
		values.put("taco", taco.getId());
		orderTacoInserter.execute(values);
	}
	
	

}
