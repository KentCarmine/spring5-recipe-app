package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.model.Ingredient;

import java.util.Set;

public interface IngredientService {

//    Set<Ingredient> findAll();

    IngredientCommand findCommandByRecipeIdAndIngredientId(Long rId, Long iId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

}
