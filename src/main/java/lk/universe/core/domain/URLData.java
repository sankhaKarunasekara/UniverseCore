package lk.universe.core.domain;
import lk.universe.core.enums.ExtractMethod;
import lk.universe.core.enums.State;
import lk.universe.core.util.DateUtil;

import java.util.Date;

public class URLData {

    long id;
    String label;
    String url;

    //one to many, one URL data has only one extract method
    //if you want to extract different data use same url with different method
    ExtractMethod extractMethod;
    Date lastCheckedTime;

    /**
     * true: urlActive, false: urlFaild
     */
    State state;

    public URLData(){

    }

    public long getId() {
        return id;
    }

    public URLData(String label, String url, ExtractMethod extractMethod, State state){
        long currentTimeMills = System.currentTimeMillis();
        this.id = currentTimeMills;
        this.label = label;
        this.lastCheckedTime = DateUtil.getTime(currentTimeMills);
        this.url = url;
        this.extractMethod = extractMethod;
        this.state = state;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getLastCheckedTime() {
        return lastCheckedTime;
    }

    public void setLastCheckedTime(Date lastCheckedTime) {
        this.lastCheckedTime = lastCheckedTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setExtactMethod(ExtractMethod extractMethod) {
        this.extractMethod = extractMethod;
    }

    public ExtractMethod getExtractMethod() {
        return extractMethod;
    }
}
