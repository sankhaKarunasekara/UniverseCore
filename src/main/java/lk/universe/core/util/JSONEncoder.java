package lk.universe.core.util;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import lk.universe.core.domain.URLData;

public class JSONEncoder {

    public static JSONObject encodeURLData(URLData urlData) throws JSONException {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", urlData.getId());
        jsonObject.put("label", urlData.getLabel());
        jsonObject.put("last_checked_time", urlData.getLastCheckedTime());
        jsonObject.put("url", urlData.getUrl());
        jsonObject.put("extract_method", urlData.getExtractMethod());
        jsonObject.put("state", urlData.getState());

        return jsonObject;
    }

}
