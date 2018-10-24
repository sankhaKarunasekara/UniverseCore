package lk.universe.core.domain;

import com.google.appengine.api.datastore.Text;
import lk.universe.core.enums.Frequency;
import lk.universe.core.enums.State;

import java.util.Date;
import java.util.List;

public class DataSet {

    private long id;
    private String name;
    private Date createdDate;
    private Date lastUpdatedTime;
    private UserPublic createdBy;
    private Frequency frequency;
    private State state;
    private String domain;
    //include the alternative URL if something fail.
    //might use java reflections
    private List<Long> urlDataList;

    //data set, later this should more in to bucket as a file name
    private Text data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public UserPublic getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserPublic createdBy) {
        this.createdBy = createdBy;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Long> getUrlDataList() {
        return urlDataList;
    }

    public void setUrlDataList(List<Long> urlDataList) {
        this.urlDataList = urlDataList;
    }

    public Text getData() {
        return data;
    }

    public void setData(Text data) {
        this.data = data;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}