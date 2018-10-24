package lk.universe.core.dao;

import com.google.appengine.api.datastore.*;
import lk.universe.core.domain.URLData;
import lk.universe.core.enums.ExtractMethod;
import lk.universe.core.enums.State;
import lk.universe.core.util.Constants;
import lk.universe.core.util.LoggingLines;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLDataDAO {
   // https://cloud.google.com/datastore/docs/concepts/entities#datastore-datastore-basic-entity-java
    private final static Logger LOGGER = Logger.getLogger(URLDataDAO.class.getName());
    private DatastoreService datastore;

    public URLDataDAO() {
        this.datastore = DatastoreServiceFactory.getDatastoreService();
    }

    public URLData getURLDataEntity(Long id) {

        try {
            Key key = new Entity(Constants.URL_DATA_ENTITY_KIND,id).getKey();
            Entity entity = datastore.get(key);
            return getURLData(entity);

        } catch (EntityNotFoundException e) {
            LOGGER.log(Level.INFO,"User Error",e);
        }
        return null;
    }

    public List<URLData> getAllUrlDataEntries(){

        Query urlDataQuery = new Query(Constants.URL_DATA_ENTITY_KIND);

        List<Entity> entities = datastore.prepare(urlDataQuery).asList(FetchOptions.Builder.withDefaults());
        List<URLData> urlDataList = new ArrayList<URLData>();

        for(Entity entity : entities){
            URLData urlData = getURLData(entity);
            urlDataList.add(urlData);
        }
        return urlDataList;
    }

    public void createURLDataEntity(URLData urlData){

        try {
            Entity urlDataEntity = new Entity(Constants.URL_DATA_ENTITY_KIND, urlData.getId());

            urlDataEntity.setProperty("id", urlData.getId());
            urlDataEntity.setProperty("label", urlData.getLabel());
            urlDataEntity.setProperty("url", urlData.getUrl());
            urlDataEntity.setProperty("lastCheckedTime", urlData.getLastCheckedTime());
            urlDataEntity.setProperty("extractMethod", urlData.getExtractMethod().getMethodAsString());
            urlDataEntity.setProperty("state", urlData.getState().getMethodAsString());

            datastore.put(urlDataEntity);

        }catch(Exception e){
            LOGGER.log(Level.INFO,LoggingLines.ERROR_ADDING,e);
            throw e;
        }
    }

    public void updateURLDataEntity(URLData urlData){

        Transaction transaction = datastore.beginTransaction();

        try {
            Key key = KeyFactory.createKey(Constants.URL_DATA_ENTITY_KIND, urlData.getId());
            Entity urlDataEntity = datastore.get(transaction, key);

            urlDataEntity.setProperty("label", urlData.getLabel());
            urlDataEntity.setProperty("url", urlData.getUrl());
            urlDataEntity.setProperty("lastCheckedTime", urlData.getLastCheckedTime());
            urlDataEntity.setProperty("extractMethod", urlData.getExtractMethod().getMethodAsString());
            urlDataEntity.setProperty("state", urlData.getState().getMethodAsString());

            datastore.put(transaction, urlDataEntity);
            transaction.commit();

        }catch(EntityNotFoundException e){
            LOGGER.log(Level.INFO,LoggingLines.ERROR_ENTITY_NOT_FOUND+"URLData",e);
            try {
                throw e;
            } catch (Exception e1) {
                LOGGER.log(Level.INFO,LoggingLines.ERROR_UPDATING+"URLData",e1);
            }
        }finally {
            if (transaction.isActive()) transaction.rollback();
        }
    }

    public void updateURLDataState(Long id, State state){

        Transaction transaction = datastore.beginTransaction();

        try {
            Key key = KeyFactory.createKey(Constants.URL_DATA_ENTITY_KIND, id);
            Entity urlDataEntity = datastore.get(transaction, key);
            urlDataEntity.setProperty("state",state.getMethodAsString());

            datastore.put(transaction, urlDataEntity);
            transaction.commit();

        }catch(EntityNotFoundException e){
            LOGGER.log(Level.INFO,LoggingLines.ERROR_ENTITY_NOT_FOUND+"URLData",e);
            try {
                throw e;
            } catch (Exception e1) {
                LOGGER.log(Level.INFO,LoggingLines.ERROR_UPDATING+"URLData",e1);
            }
        }finally {
            if (transaction.isActive()) transaction.rollback();
        }
    }

    public void deleteURLDataEntity(long urlDataId){

        Key key = KeyFactory.createKey(Constants.URL_DATA_ENTITY_KIND,urlDataId);
        Transaction transaction = datastore.beginTransaction();

        try {
            datastore.delete(transaction, key);
            transaction.commit();
        }catch(Exception e){
            LOGGER.log(Level.INFO, LoggingLines.ERROR_DELETING,e);
            throw e;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    private URLData getURLData(Entity entity) {

        URLData urlData = new URLData();

        try{
            urlData.setId(entity.getKey().getId());
            urlData.setLabel((String) entity.getProperty("label"));
            urlData.setUrl((String) entity.getProperty("url"));
            urlData.setExtactMethod(ExtractMethod.valueOf((String)entity.getProperty("extractMethod")));
            urlData.setState(State.valueOf((String)entity.getProperty("state")));
            urlData.setLastCheckedTime((Date)entity.getProperty("lastCheckedTime"));
            LOGGER.warning("date"+urlData.getLastCheckedTime().toString());
            return urlData;

        }catch(Exception e){
            throw e;
        }
    }
}
