
package lk.universe.core.enums;

/**
 * @author Sankha Karunasekara
 * @apiNote Type of methods that can used to extract data from a web page
 */
public enum ExtractMethod {

    ONE("ONE"),
    TWO("TWO"),
    THREE("THREE"),
    UNKNOWN("UNKNOWN");

    private String method;

    ExtractMethod(String method) {
        this.method = method;
    }

    public String getMethodAsString() {
        return method;
    }

    public static ExtractMethod getMethodFromString(String text) {

        for (ExtractMethod extractMethod : ExtractMethod.values()) {
            if (extractMethod.getMethodAsString().equalsIgnoreCase(text)) {
                return extractMethod;
            }
        }

        return ExtractMethod.UNKNOWN;
    }
}
