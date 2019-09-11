package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private RecipeRepository recipeRepository;
    private IngredientToIngredientCommand ingredientToIngredientCommandConverter;

    @Autowired
    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand converter) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommandConverter = converter;
    }

    @Override
    public IngredientCommand findCommandByRecipeIdAndIngredientId(Long rid, Long iid) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(Long.valueOf(rid));
        if (!recipeOpt.isPresent()) {
            // TODO: Impl error handling
            log.error("Recipe id is not found. Id = " + rid);
        }

        Recipe recipe = recipeOpt.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(iid))
                .map(ingredient -> ingredientToIngredientCommandConverter.convert(ingredient))
                .findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            // TODO: Impl error handling
            log.error("Ingredient Id not found: " + iid);
        }

        return ingredientCommandOptional.get();
    }

}
