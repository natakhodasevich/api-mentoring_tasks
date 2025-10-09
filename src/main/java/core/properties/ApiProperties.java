package core.properties;

import static core.utils.PropertiesLoader.getPropertyByKey;
import static core.utils.UrlUtil.resolveUri;

public class ApiProperties {
    private static ThreadLocal<String> baseUriReqRes = ThreadLocal.withInitial(() -> getPropertyByKey("BASE_URI_REQ_RES"));

    public static String getBaseUriReqRes() {
        return resolveUri(baseUriReqRes.get());
    }

}
