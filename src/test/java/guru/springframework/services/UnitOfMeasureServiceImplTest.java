package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommandConverter = new UnitOfMeasureToUnitOfMeasureCommand();
    private UnitOfMeasureService service;

    @Mock
    private UnitOfMeasureRepository uomRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new UnitOfMeasureServiceImpl(uomRepository, unitOfMeasureToUnitOfMeasureCommandConverter);
    }

    @Test
    public void listAllUoms() {
        Set<UnitOfMeasure> uomSet = new HashSet<>();
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(1L);
        uomSet.add(uom);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        uomSet.add(uom2);

        when(uomRepository.findAll()).thenReturn(uomSet);

        Set<UnitOfMeasureCommand> commands = service.listAllUoms();

        assertEquals(2, commands.size());
        verify(uomRepository, times(1)).findAll();
    }


}