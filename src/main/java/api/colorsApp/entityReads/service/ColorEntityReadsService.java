package api.colorsApp.entityReads.service;

import api.colorsApp.entityReads.ColorEndpoints;
import api.colorsApp.entityReads.models.ColorDataResponse;

import java.util.List;

import static core.utils.PayloadEntityReadsUtil.PayloadEntityReader.givenEntityArrayType;
import static core.utils.RequestSpecificationUtil.buildReqResApiRequestSpec;

public class ColorEntityReadsService {

    public List<ColorDataResponse> getAllColors() {
        return givenEntityArrayType(ColorDataResponse[].class)
                .withRequestSpecification(buildReqResApiRequestSpec())
                .getEntitiesFromUrl(ColorEndpoints.GET_ALL_COLORS, true, ColorDataResponse.class, "data");
    }
}
