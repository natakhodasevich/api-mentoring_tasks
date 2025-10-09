package api.colors.entityReads.service;

import api.colors.entityReads.ColorEndpoints;
import api.colors.entityReads.models.ColorDataResponse;

import java.util.List;

import static core.utils.PayloadEntityReadsUtil.PayloadEntityReader.givenEntityArrayType;
import static core.utils.RequestSpecificationUtils.buildReqResApiRequestSpec;

public class ColorEntityReadsService {

    public List<ColorDataResponse> getAllColors() {
        return givenEntityArrayType(ColorDataResponse[].class)
                .withRequestSpecification(buildReqResApiRequestSpec())
                .getEntitiesFromUrl(ColorEndpoints.GET_ALL_COLORS, true, ColorDataResponse.class, "data");
    }
}
