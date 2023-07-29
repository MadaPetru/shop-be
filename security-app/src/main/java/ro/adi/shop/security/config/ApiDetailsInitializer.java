package ro.adi.shop.security.config;


import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ApiDetailsInitializer {

    private static final String LOGIN_API_ID_POST = "POST/users/login";
    private static final String LOGIN_API_ID_OPTIONS = "OPTIONS/users/login";
    private static final String SEARCH_PRODUCTS_API_ID_OPTIONS = "OPTIONS/products/search";
    private static final String SEARCH_PRODUCTS_API_ID_POST = "POST/products/search";
    private static final Map<String, ApiDetails> apiDetailsMap = new HashMap<>();

    static {
        var detailsForLoginApi = getApiDetailsForLoginEndpoint();
        var detailsForSearchProductsEndpoint = getApiDetailsForSearchProductsEndpoint();
        apiDetailsMap.put(LOGIN_API_ID_POST, detailsForLoginApi);
        apiDetailsMap.put(LOGIN_API_ID_OPTIONS, detailsForLoginApi);
        apiDetailsMap.put(SEARCH_PRODUCTS_API_ID_OPTIONS, detailsForSearchProductsEndpoint);
        apiDetailsMap.put(SEARCH_PRODUCTS_API_ID_POST, detailsForSearchProductsEndpoint);
    }

    public Map<String, ApiDetails> getApiDetailsMappedById() {

        return apiDetailsMap;
    }

    private static ApiDetails getApiDetailsForLoginEndpoint() {

        var detailsForLoginApi = new ApiDetails();
        detailsForLoginApi.setUri("/users/login");
        detailsForLoginApi.setMethod("POST");
        var apiRestriction = new ApiRestriction();
        apiRestriction.setMaxRetries((byte) 5);
        apiRestriction.setMinutesToBeBlocked((byte) 60);
        detailsForLoginApi.setApiRestriction(apiRestriction);
        return detailsForLoginApi;
    }

    private static ApiDetails getApiDetailsForSearchProductsEndpoint() {

        var detailsForLoginApi = new ApiDetails();
        detailsForLoginApi.setUri("/products/search");
        detailsForLoginApi.setMethod("OPTIONS");
        var apiRestriction = new ApiRestriction();
        apiRestriction.setMaxRetries((byte) 100);
        apiRestriction.setMinutesToBeBlocked((byte) 5);
        detailsForLoginApi.setApiRestriction(apiRestriction);
        return detailsForLoginApi;
    }
}
