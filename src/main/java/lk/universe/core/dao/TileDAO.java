package lk.universe.core.dao;

import com.google.appengine.api.datastore.*;
import lk.universe.core.domain.Tile;
import lk.universe.core.util.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TileDAO {
    private final static Logger LOGGER = Logger.getLogger(TileDAO.class.getName());
    private DatastoreService datastore;

    public TileDAO() {
        this.datastore = DatastoreServiceFactory.getDatastoreService();
    }

    private Tile getTile(Entity entity) {

        Tile tile = new Tile();

        try{
            tile.setId(entity.getKey().getId());
            tile.setName((String) entity.getProperty("name"));
            tile.setDataSets((List<Long>)entity.getProperty("datasets"));
            tile.setTemplateId((Long)entity.getProperty("templateId"));
            tile.setCreatedDate((Date)entity.getProperty("createdDate"));
            LOGGER.warning("date"+tile.getCreatedDate().toString());
            return tile;
        }catch(Exception e){
            throw e;
        }
    }

    public Tile getTileEntity(Long id) {

        try {
            Key key = new Entity(Constants.TILE_ENTITY_KIND,id).getKey();
            Entity entity = datastore.get(key);
            return getTile(entity);

        } catch (EntityNotFoundException e) {
            LOGGER.log(Level.INFO,"User Error",e);
        }
        return null;
    }

    public List<Tile> getAllTileEntries(){

        Query urlDataQuery = new Query(Constants.TILE_ENTITY_KIND);

        List<Entity> entities = datastore.prepare(urlDataQuery).asList(FetchOptions.Builder.withDefaults());
        List<Tile> tileList = new ArrayList<>();

        for(Entity entity : entities){
            Tile tile = getTile(entity);
            tileList.add(tile);
        }

//        tileList = entities.stream().map(entity ->
//            getTile(entity)).collect(Collectors.toList());

        return tileList;
    }
}
