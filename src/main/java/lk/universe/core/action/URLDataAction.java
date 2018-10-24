package lk.universe.core.action;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import lk.universe.core.domain.URLData;
import lk.universe.core.service.URLDataService;
import lk.universe.core.util.JSONEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "URLDataAction", value = "/urldata/*")
public class URLDataAction extends HttpServlet {

    private URLDataService urlDataService;
    private final static Logger LOGGER = Logger.getLogger(URLDataAction.class.getName());

    public URLDataService getURLDataService() {
        if (urlDataService == null) {
            urlDataService = new URLDataService();
        }
        return urlDataService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reqType = request.getRequestURI();
        String reqData = "";
        switch(reqType){
            case "/urldata/get" :
                handleGetURLData(request, response, reqData);
                break;
            case "/urldata/getAll" :
                handleGetAllURLData(request, response, reqData);
                break;
            default :
                //sendErrorMessage(response, 501);
                break;
        }
    }

    /**
     * @desc Updates
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reqType = request.getRequestURI();
        BufferedReader reqPayLoad = request.getReader();
        String reqData = "";
        if(reqPayLoad != null){
            reqData = reqPayLoad.readLine();
        }

        switch(reqType){
            case "/urldata/update" :
                handleUpdateURLData(request, response, reqData);
            default :
                //sendErrorMessage(response, 501);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reqType = request.getRequestURI();
        BufferedReader reqPayLoad = request.getReader();
        String reqData = "";

        if(reqPayLoad != null){
            reqData = reqPayLoad.readLine();
        }

        switch(reqType){
            case "/urldata/add" :
                handleAddURLData(request, response, reqData);
                break;
            default :
                //sendErrorMessage(response, 501);
                break;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqType = request.getRequestURI();
        BufferedReader reqPayLoad = request.getReader();
        String reqData = "";

        if(reqPayLoad != null){
            reqData = reqPayLoad.readLine();
        }

        switch(reqType){
            case "/urldata/delete" :
                handleDeleteURLData(request, response, reqData);
                break;
            default :
                //sendErrorMessage(response, 501);
                break;
        }
    }

    public void handleGetURLData(HttpServletRequest request, HttpServletResponse response, String reqData) throws IOException{

        URLDataService urlDataService = getURLDataService();

        String userEmail = request.getParameter("user_email");
        Long urlDataId = Long.parseLong(request.getParameter("url_data_id"));
        JSONObject resObj = new JSONObject();

        try {

            URLData urlData = urlDataService.getURLData(urlDataId);
            resObj = JSONEncoder.encodeURLData(urlData);
            response.setStatus(HttpServletResponse.SC_OK);

        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getMessage(),e);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                resObj.put("error_message", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }

        response.getWriter().println(resObj);
    }

    private void handleGetAllURLData(HttpServletRequest request, HttpServletResponse response, String reqData) throws IOException{

        URLDataService urlDataService = getURLDataService();
        JSONObject resObj = new JSONObject();

        try {

            List<URLData> allUrlData = urlDataService.getAllURLData();
            //urlDataObject =  JSONEncoder.encodeURLData(urlData);

            List<JSONObject> jsonObjectList = allUrlData.stream().map(urlData -> {
                try {
                    return JSONEncoder.encodeURLData(urlData);
                } catch (JSONException e) {
                    return new JSONObject();
                }
            }).collect(Collectors.toList());

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(jsonObjectList);

        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getMessage(),e);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                resObj.put("error_message", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            response.getWriter().println(resObj);
        }

    }

    public void handleAddURLData(HttpServletRequest request, HttpServletResponse response, String reqData) throws IOException{
        //TODO: write this as a test case
        JSONObject jsonObject = null;
        URLDataService urlDataService = getURLDataService();
        JSONObject resObj = new JSONObject();

        try {
            jsonObject = new JSONObject(reqData);

            String userEmail = jsonObject.getString("user_email");
            String url = jsonObject.getString("url");
            String extractMethod = jsonObject.getString("extract_method");
            String label = jsonObject.getString("label");

            urlDataService.createURLData(url,extractMethod,label);
            response.setStatus(HttpServletResponse.SC_OK);

        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getMessage(),e);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                resObj.put("error_message", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        }

        response.getWriter().println(resObj);
    }

    public void handleUpdateURLData(HttpServletRequest request, HttpServletResponse response, String reqData) throws IOException{

        //TODO: write this as a test case
        JSONObject jsonObject = null;
        URLDataService urlDataService = getURLDataService();
        JSONObject resObj = new JSONObject();

        try {
            jsonObject = new JSONObject(reqData);

            String userEmail = jsonObject.getString("user_email");
            Long urlDataId = Long.parseLong( jsonObject.getString("url_data_id"));
            String url = jsonObject.getString("url");
            String extractMethod = jsonObject.getString("extract_method");
            String label = jsonObject.getString("label");

            urlDataService.updateURLData(urlDataId,url,extractMethod,label);
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getMessage(),e);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                resObj.put("error_message", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        response.getWriter().println(resObj);
    }

    public void handleDeleteURLData(HttpServletRequest request, HttpServletResponse response, String reqData) throws IOException{

        URLDataService urlDataService = getURLDataService();
        JSONObject resObj = new JSONObject();

        try {
            String userEmail = request.getParameter("user_email");
            Long urlDataId = Long.parseLong(request.getParameter("url_data_id"));

            urlDataService.deleteURLData(urlDataId);
            response.setStatus(HttpServletResponse.SC_OK);

        }catch (Exception e){
            LOGGER.log(Level.WARNING,e.getMessage(),e);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                resObj.put("error_message", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        response.getWriter().println(resObj);
    }
}
