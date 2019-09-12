package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService uomService;

    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String viewIngredients(@PathVariable String id, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
        model.addAttribute("recipe", recipeCommand);

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{rid}/ingredient/{iid}/show")
    public String showRecipeIngredient(@PathVariable String rid, @PathVariable String iid, Model model) {

        model.addAttribute("ingredient", ingredientService.findCommandByRecipeIdAndIngredientId(Long.valueOf(rid), Long.valueOf(iid)));

        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{rid}/ingredient/{iid}/update")
    public String showUpdateIngredientPage(@PathVariable String rid, @PathVariable String iid, Model model) {

        IngredientCommand ingredientCommand = ingredientService.findCommandByRecipeIdAndIngredientId(Long.valueOf(rid), Long.valueOf(iid));
        model.addAttribute("ingredient", ingredientCommand);

        model.addAttribute("uomList", uomService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("receipe id:" + savedCommand.getRecipeId());
        log.debug("ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{rid}/ingredient/new")
    public String newRecipe(@PathVariable String rid, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(rid));
        // TODO: Raise exception if null

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeCommand.getId());
        model.addAttribute("ingredient", ingredientCommand);
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("uomList", uomService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }


}
