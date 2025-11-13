package api.colorsApp;

import api.BaseTest;
import api.colorsApp.entityReads.ColorEntityReadsController;
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
@Feature("Color Values")
public class ColorPantoneValuePatternTest extends BaseTest {
    private ColorEntityReadsController colorEntityReads;

    @BeforeMethod
    public void setup() {
        colorEntityReads = new ColorEntityReadsController();
    }


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
