package api.colorsApp;

import api.BaseTest;
import api.colorsApp.controller.ColorEntityReadsController;
import core.logging.ApiTestContext;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

@Listeners({AllureTestNg.class})
@Epic("Colors")
@Feature("Color Names")
public class ColorNamePatternTest extends BaseTest {
    private static final String expectedPattern = "#";
    private ColorEntityReadsController colorEntityReads;

    @BeforeMethod
    public void setup() {
        colorEntityReads = new ColorEntityReadsController();
    }

    @Test(groups = {"smoke"})
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
}
