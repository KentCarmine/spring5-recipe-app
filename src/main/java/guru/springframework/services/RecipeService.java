package guru.springframework.services;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;

import java.util.Set;

public interface RecipeService {

    Iterable<Recipe> getAll();

}
