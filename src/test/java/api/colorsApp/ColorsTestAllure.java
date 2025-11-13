package api.colorsApp;

import api.BaseTest;
import api.colorsApp.controller.ColorEntityReadsController;
import core.logging.ApiTestContext;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

@Epic("Allure")
@Feature("Colors")
public class ColorsTestAllure extends BaseTest {

    private static final String expectedPattern = "#";
    private ColorEntityReadsController colorEntityReads;

    @BeforeMethod
    public void setup() {
        colorEntityReads = new ColorEntityReadsController();
    }

    @Story("Checking color values")
    @Test(groups = {"regression"})
    public void checkColorValue() {
        logger.info("Getting all color names");
        ApiTestContext.setStepName("step1");
        List<String> colorsNamesList = colorEntityReads.getAllColorNamesFromColors();
        logger.info("Asserting each name is not null, not empty and starts with pattern %s".formatted(expectedPattern));
        colorsNamesList.forEach(colorName -> {
                    assertNotNull(colorName, "Color value should not be null");
                    assertFalse(colorName.isEmpty(), "Color value should not be empty");
                    assertTrue(colorName.startsWith("#"), "Color should start with #, but if does not for %s".formatted(colorName));
                }
        );
    }

    @Story("Checking color pantone values")
    @Test(groups = {"regression"})
    public void checkPantoneValuePattern() {
        ApiTestContext.setStepName("step1");
        logger.info("Get all pantone values");
        List<String> colorsPantoneValueList = colorEntityReads.getAllPantoneValuesFromColors();

        logger.info("Asserting that each pantone value is not empty, not null, and matches pattern");
        colorsPantoneValueList.forEach(pantoneValue -> {
                    assertNotNull(pantoneValue, "Pantone value should not be null");
                    assertFalse(pantoneValue.isEmpty(), "Pantone value should not be empty");
                    assertTrue(pantoneValue.matches("\\d{2}-\\d{4}"),
                            "Pantone should match the pattern 12-3456, but does not for %s".formatted(pantoneValue));
                }
        );
    }
}
