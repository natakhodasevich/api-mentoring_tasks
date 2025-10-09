package core.utils;

import static org.apache.commons.lang3.StringUtils.strip;

public class UrlUtil {

    private UrlUtil() {
    }

    public static String combineUrl(String baseUrl, Object... params) {
        var url = strip(baseUrl, "/");
        for (var param: params) {
            url += "/" + param.toString();
        }

        return url;
    }

    public static String resolveUri(String uri) {
        if(uri == null) {
            return "";
        }
        if(uri != null && uri.endsWith("/")) {
            return uri.substring(0, uri.length() - 1);
        }
        return uri;
    }
}

