package api.colorsApp;

import api.BaseTest;
import api.colorsApp.controller.ColorEntityReadsController;
import core.logging.ApiTestContext;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners({AllureTestNg.class})
@Epic("Colors")
@Feature("Years")
public class YearsSortingTest extends BaseTest {
    private ColorEntityReadsController colorEntityReads;

    @BeforeMethod
    public void setup() {
        colorEntityReads = new ColorEntityReadsController();
    }

    @Test(groups = {"regression"})
    public void sortedYearsTest() {
        ApiTestContext.setStepName("step1");
        logger.info("Getting all years from colors");
        List<Integer> years = colorEntityReads.getAllYearsFromColors();
        logger.info("Asserting that years are sorted");
        List<Integer> yearsSorted = years.stream().sorted().toList();
        Assert.assertEquals(years, yearsSorted);
    }
}
