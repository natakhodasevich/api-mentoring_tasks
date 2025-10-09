package api.colors.entityReads;

import api.colors.entityReads.models.ColorDataResponse;
import api.colors.entityReads.service.ColorEntityReadsService;

import java.util.List;

public class ColorEntityReads {
    ColorEntityReadsService colorEntityReadsService = new ColorEntityReadsService();

    public List<Integer> getAllYearsFromColors() {
        List<ColorDataResponse> colors = colorEntityReadsService.getAllColors();
        return colors.stream().map(ColorDataResponse::getYear).toList();
    }
}
