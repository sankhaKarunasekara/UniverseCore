package lk.universe.core.domain;

import lk.universe.core.enums.State;

import java.util.Date;
import java.util.List;

public class Tile {

    long id;
    String name;

    //one tile can be generated using multiple data sets
    //id, State
    private List<Long> dataSets;

    UserPublic createdUser;
    Date createdDate;
    long templateId;
    State state;

    public List<Long> getDataSets() {
        return dataSets;
    }

    public void setDataSets(List<Long> dataSets) {
        this.dataSets = dataSets;
    }

    public UserPublic getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(UserPublic createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(long templateId) {
        this.templateId = templateId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

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

}
