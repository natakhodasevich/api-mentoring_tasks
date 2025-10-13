package api.colorsApp.entityReads;

import api.colorsApp.entityReads.models.ColorDataResponse;
import api.colorsApp.entityReads.service.ColorEntityReadsService;

import java.util.List;

public class ColorEntityReads {
    ColorEntityReadsService colorEntityReadsService = new ColorEntityReadsService();

    public List<Integer> getAllYearsFromColors() {
        List<ColorDataResponse> colors = colorEntityReadsService.getAllColors();
        return colors.stream().map(ColorDataResponse::getYear).toList();
    }

    public List<String> getAllColorNamesFromColors() {
        List<ColorDataResponse> colors = colorEntityReadsService.getAllColors();
        return colors.stream().map(ColorDataResponse::getColor).toList();
    }

    public List<String> getAllPantoneValuesFromColors() {
        List<ColorDataResponse> colors = colorEntityReadsService.getAllColors();
        return colors.stream().map(ColorDataResponse::getPantone_value).toList();
    }
}
