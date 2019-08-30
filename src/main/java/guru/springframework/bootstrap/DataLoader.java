package guru.springframework.bootstrap;

import guru.springframework.model.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    public void run(String... args) {
        setUp();
    }

    public void setUp() {
        UnitOfMeasure tableSpoonUom = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure teapoonUom = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure dashUom = unitOfMeasureRepository.findByDescription("Dash").get();
        UnitOfMeasure pintUom = unitOfMeasureRepository.findByDescription("Pint").get();
        UnitOfMeasure cupsUom = unitOfMeasureRepository.findByDescription("Cup").get();
        UnitOfMeasure blankUom = unitOfMeasureRepository.findByDescription("").get();
        UnitOfMeasure cloveUom = unitOfMeasureRepository.findByDescription("Clove").get();

        // Guacamole Recipe
        Ingredient avocado = new Ingredient("ripe avocado",  new BigDecimal(2), blankUom);
        Ingredient salt = new Ingredient("kosher salt", new BigDecimal(0.5), teapoonUom);
        Ingredient citrusJuice = new Ingredient("fresh lime or lemon juice", new BigDecimal(1), tableSpoonUom);
        Ingredient onion = new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUom);
        Ingredient chillies = new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(1), blankUom);
        Ingredient cilantro = new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tableSpoonUom);


        Ingredient pepper = new Ingredient("freshly grated black pepper", new BigDecimal(1), dashUom);

        Ingredient tomato = new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(0.5), blankUom);

        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(600);
        guacamole.setCookTime(0);
        guacamole.setServings(2);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setSource("Simply Recipes");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.addCategory(categoryRepository.findByDescription("Mexican").get());
        guacamole.addCategory(categoryRepository.findByDescription("American").get());
        guacamole.addIngredient(avocado);
        guacamole.addIngredient(salt);
        guacamole.addIngredient(citrusJuice);
        guacamole.addIngredient(onion);
        guacamole.addIngredient(chillies);
        guacamole.addIngredient(cilantro);
        guacamole.addIngredient(pepper);
        guacamole.addIngredient(tomato);

        guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        Notes guacNote = new Notes();
        guacNote.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
        guacamole.setNotes(guacNote);

        recipeRepository.save(guacamole);

        // Tacos
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacoRecipe.setCookTime(9);
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setServings(4);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacoRecipe.setSource("Simply Recipes");
        tacoRecipe.addCategory(categoryRepository.findByDescription("Mexican").get());
        tacoRecipe.addCategory(categoryRepository.findByDescription("American").get());
        tacoRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        tacoRecipe.setNotes(tacoNotes);

        tacoRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teapoonUom));
        tacoRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teapoonUom));
        tacoRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teapoonUom));
        tacoRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teapoonUom));
        tacoRecipe.addIngredient(new Ingredient("Garlic, Chopped", new BigDecimal(1), cloveUom));
        tacoRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), blankUom));
        tacoRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupsUom));
        tacoRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), blankUom));
        tacoRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), blankUom));
        tacoRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom));
        tacoRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), blankUom));
        tacoRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), blankUom));
        tacoRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupsUom));
        tacoRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), blankUom));

        recipeRepository.save(tacoRecipe);


    }

}
