package api.colorsApp;

import api.colorsApp.entityReads.ColorEntityReads;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ColorNamePatternTest {
    private ColorEntityReads colorEntityReads;

    @BeforeMethod
    public void setup() {
        colorEntityReads = new ColorEntityReads();
    }

    @Test
    public void checkColorValue() {
        List<String> colorsNamesList = colorEntityReads.getAllColorNamesFromColors();

        colorsNamesList.forEach(colorName -> {
                    assertNotNull(colorName, "Color value should not be null");
                    assertFalse(colorName.isEmpty(), "Color value should not be empty");
                    assertTrue(colorName.startsWith("#"), "Color should start with #, but if does not for %s".formatted(colorName));
                }
        );
    }
}
