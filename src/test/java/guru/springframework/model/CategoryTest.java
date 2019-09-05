package guru.springframework.model;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CategoryTest {

    private Category category;

    @Before
    public void setUp() {
        category = new Category();

    }

    @Test
    public void getId() {
        Long l = 4l;
        category.setId(l);
        assertEquals(l, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}
