package tacos.data;

import tacos.vo.Ingredient;

public interface IngredientRepository {
	
	Iterable<Ingredient> findAll();
	Ingredient findById(String id);
	Ingredient save(Ingredient ingredient);

}
