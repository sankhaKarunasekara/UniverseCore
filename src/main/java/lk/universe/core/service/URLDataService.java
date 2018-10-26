package lk.universe.core.service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import lk.universe.core.dao.URLDataDAO;
import lk.universe.core.domain.URLData;
import lk.universe.core.enums.ExtractMethod;
import lk.universe.core.enums.State;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLDataService {

    private final static Logger LOGGER = Logger.getLogger(URLDataService.class.getName());
    private DatastoreService datastore;

    public URLDataService(){
        this.datastore = DatastoreServiceFactory.getDatastoreService();
    }

    private URLDataDAO urlDataDAO;

    public URLDataDAO getUrlDataDAO() {
        if (urlDataDAO == null) {
            urlDataDAO = new URLDataDAO();
        }
        return urlDataDAO;
    }

    public URLData getURLData(Long id){
        URLDataDAO urlDataDAO = getUrlDataDAO();
        return urlDataDAO.getURLDataEntity(id);
    }

    public List<URLData> getAllURLData() {
        URLDataDAO urlDataDAO = getUrlDataDAO();
        return urlDataDAO.getAllUrlDataEntries();
    }

    public void createURLData(String url, String extractMethodString, String label){

        try {
            URLDataDAO urlDataDAO = getUrlDataDAO();
            ExtractMethod extractMethod = ExtractMethod.valueOf(extractMethodString);
            //TODO: check the url is live
            String stateString = "LIVE";
            State state = State.valueOf(stateString);

            URLData urlData = new URLData(label, url, extractMethod, state);
            LOGGER.info(urlData.getLabel());
            urlDataDAO.createURLDataEntity(urlData);
        }catch(Exception e){
            LOGGER.log(Level.WARNING,"Error",e);
        }
    }

    public void updateURLData(Long urlDataId, String url, String extractMethodString, String label){

        URLData urlData = getURLData(urlDataId);
        urlData.setUrl(url);
        urlData.setExtactMethod(ExtractMethod.valueOf(extractMethodString));

        URLDataDAO urlDataDAO = getUrlDataDAO();
        urlDataDAO.updateURLDataEntity(urlData);
    }

    public void updateURLState(Long urlDataId, String newState){

        URLData urlData = getURLData(urlDataId);
        State state = State.valueOf(newState);
        urlData.setState(state);

        URLDataDAO urlDataDAO = getUrlDataDAO();
        urlDataDAO.updateURLDataEntity(urlData);
    }

    public void deleteURLData(long urlDataId) {
        URLDataDAO urlDataDAO = getUrlDataDAO();
        urlDataDAO.deleteURLDataEntity(urlDataId);
    }
}
