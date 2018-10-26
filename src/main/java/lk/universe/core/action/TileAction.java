package lk.universe.core.action;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import lk.universe.core.domain.Tile;
import lk.universe.core.service.TileService;
import lk.universe.core.util.JSONEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "TileAction", value = "/tile/*")
public class TileAction extends HttpServlet {

    private TileService tileService;
    private final static Logger LOGGER = Logger.getLogger(TileAction.class.getName());

    private TileService getTileService() {
        if (tileService == null) {
            tileService = new TileService();
        }
        return tileService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String reqType = request.getRequestURI();

        switch (reqType) {
            case "/tile/get":
                handleGetTile(request, response);
                break;
            case "/tile/getAll":
                handleGetAllTiles(request, response);
                break;
            default:
                //sendErrorMessage(response, 501);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void handleGetTile(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TileService tileService = getTileService();

        String userEmail = request.getParameter("user_email");
        Long tileId = Long.parseLong(request.getParameter("tile_id"));
        JSONObject resObj = new JSONObject();

        try {

            Tile tile = tileService.getTile(tileId);
            resObj = JSONEncoder.encodeTile(tile);
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception e) {

            LOGGER.log(Level.WARNING, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                resObj.put("error_message", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

        }

        response.getWriter().println(resObj);
    }

    private void handleGetAllTiles(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TileService tileService = getTileService();
        JSONObject resObj = new JSONObject();

        try {

            List<Tile> tiles = tileService.getAllTiles();

            List<JSONObject> jsonObjectList = tiles.stream().map(tile -> {
                try {
                    return JSONEncoder.encodeTile(tile);
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
}
