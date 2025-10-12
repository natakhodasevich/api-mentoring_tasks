package api.colors;

import api.colors.entityReads.ColorEntityReads;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ColorPantoneValuePatternTest {
    private ColorEntityReads colorEntityReads;

    @BeforeMethod
    public void setup() {
        colorEntityReads = new ColorEntityReads();
    }

    @Test
    public void checkPantoneValuePattern() {
        List<String> colorsPantoneValueList = colorEntityReads.getAllPantoneValuesFromColors();

        colorsPantoneValueList.forEach(pantoneValue -> {
                    assertNotNull(pantoneValue, "Pantone value should not be null");
                    assertFalse(pantoneValue.isEmpty(), "Pantone value should not be empty");
                    assertTrue(pantoneValue.matches("\\d{2}-\\d{4}"),
                            "Pantone should match the pattern 12-3456, but does not for %s".formatted(pantoneValue));
                }
        );
    }
}
