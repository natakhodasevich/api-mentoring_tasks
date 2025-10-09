package api.colors;

import api.colors.entityReads.ColorEntityReads;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class YearsSortingTest {
    private ColorEntityReads colorEntityReads;

    @BeforeMethod
    public void setup() {
        colorEntityReads = new ColorEntityReads();
    }

    @Test
    public void sortedYearsTest() {
        List<Integer> years = colorEntityReads.getAllYearsFromColors();
        List<Integer> yearsSorted = years.stream().sorted().toList();
        Assert.assertEquals(years, yearsSorted);
    }
}
