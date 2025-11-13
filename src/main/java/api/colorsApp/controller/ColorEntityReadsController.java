package api.colorsApp.controller;

import api.colorsApp.model.ColorDataResponseModel;
import api.colorsApp.service.ColorEntityReadsService;

import java.util.List;

public class ColorEntityReadsController {
    ColorEntityReadsService colorEntityReadsService = new ColorEntityReadsService();

    /**
     * Retrieves the years of all colors from the API.
     *
     * @return a list of integers representing the year of each color
     */
    public List<Integer> getAllYearsFromColors() {
        List<ColorDataResponseModel> colors = colorEntityReadsService.getAllColors();
        return colors.stream().map(ColorDataResponseModel::getYear).toList();
    }

    /**
     * Retrieves the names of all colors from the API.
     *
     * @return a list of strings representing the name of each color
     */
    public List<String> getAllColorNamesFromColors() {
        List<ColorDataResponseModel> colors = colorEntityReadsService.getAllColors();
        return colors.stream().map(ColorDataResponseModel::getColor).toList();
    }

    /**
     * Retrieves the Pantone values of all colors from the API.
     *
     * @return a list of strings representing the Pantone value of each color
     */
    public List<String> getAllPantoneValuesFromColors() {
        List<ColorDataResponseModel> colors = colorEntityReadsService.getAllColors();
        return colors.stream().map(ColorDataResponseModel::getPantone_value).toList();
    }
}
