package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository uomRepository;
    private IngredientToIngredientCommand ingredientToIngredientCommandConverter;
    private IngredientCommandToIngredient ingredientCommandToIngredientConverter;

    @Autowired
    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepository, IngredientToIngredientCommand converter, IngredientCommandToIngredient ingredientCommandToIngredientConverter) {
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
        this.ingredientToIngredientCommandConverter = converter;
        this.ingredientCommandToIngredientConverter = ingredientCommandToIngredientConverter;
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

    @Transactional
    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(command.getRecipeId());

        if (!recipeOpt.isPresent()) {
            // TODO error if not found
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOpt.get();
            log.debug("######*" + recipe.toString());

            Optional<Ingredient> ingredientOpt = recipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOpt.isPresent()) {
                Ingredient ingredient = ingredientOpt.get();
                ingredient.setDescription(command.getDescription());
                ingredient.setAmount(command.getAmount());
                ingredient.setUom(uomRepository.findById(command.getUom().getId())
                .orElseThrow(() -> new RuntimeException("UOM Not found"))); // TODO: Update this
            } else {
                recipe.addIngredient(ingredientCommandToIngredientConverter.convert(command));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            return ingredientToIngredientCommandConverter.convert(savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId()
                            .equals(command.getId()))
                    .findFirst().get());
        }



    }

}
