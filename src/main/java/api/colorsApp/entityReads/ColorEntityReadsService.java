package api.colorsApp.entityReads;

import java.util.List;

import static core.utils.PayloadEntityReadsUtil.PayloadEntityReader.givenEntityArrayType;
import static core.utils.RequestSpecificationUtil.buildReqResApiRequestSpec;

public class ColorEntityReadsService {

    /**
     * Retrieves all colors from the API.
     * @return a list of {@link ColorDataResponseModel} representing all available colors
     */
    public List<ColorDataResponseModel> getAllColors() {
        return givenEntityArrayType(ColorDataResponseModel[].class)
                .withRequestSpecification(buildReqResApiRequestSpec())
                .getEntitiesFromUrl(ColorEndpoints.GET_ALL_COLORS, true, ColorDataResponseModel.class, "data");
    }
}
