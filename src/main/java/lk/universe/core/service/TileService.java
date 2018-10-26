package lk.universe.core.service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import lk.universe.core.dao.TileDAO;
import lk.universe.core.domain.Tile;

import java.util.List;
import java.util.logging.Logger;

public class TileService {
    private final static Logger LOGGER = Logger.getLogger(URLDataService.class.getName());
    private DatastoreService datastore;

    public TileService() {
        this.datastore = DatastoreServiceFactory.getDatastoreService();
    }

    private TileDAO tileDAO;

    public TileDAO getTileDAO() {
        if (tileDAO == null) {
            tileDAO = new TileDAO();
        }
        return tileDAO;
    }

    public Tile getTile(Long id) {
        TileDAO tileDAO = getTileDAO();
        return tileDAO.getTileEntity(id);
    }

    public List<Tile> getAllTiles() {
        TileDAO tileDAO = getTileDAO();
        return tileDAO.getAllTileEntries();
    }
}


