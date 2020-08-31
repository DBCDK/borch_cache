package dk.dbc.borchk.rest;

import dk.dbc.borchk.BorChkBusinessLogic;
import dk.dbc.borchk.NumberString;
import dk.dbc.borchk.interceptor.MDCProviderSimple;
import dk.dbc.borchk.json.JSONBContext;
import dk.dbc.borchk.json.JSONBException;
import dk.dbc.borchk.setup.CacheStat;
import dk.dbc.borchk.setup.HazelcastSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@Path("")
public class BorChkInfo {
    private static final Logger LOGGER = LoggerFactory.getLogger(BorChkInfo.class);


    private final JSONBContext jsonbContext = new JSONBContext();

    @EJB
    private HazelcastSetup hazelcastSetup;


    @EJB
    private BorChkBusinessLogic borChkBusinessLogic;


    /**
     * HowRU method. Returns HTTP 200 if (number of rows i VIP table can be accessed) or HTTP 500 if database cannot be accessed.
     *
     * @return json object with number of rows in vip table or internal server error.
     */
    @MDCProviderSimple
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("howru")
    public Response howRU() {
        LOGGER.trace("Entering howRU -->");
        try {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("status", "ok");
            JsonObject jsonObject = jsonObjectBuilder.build();
            String res = jsonObject.toString();
            return Response.status(Response.Status.OK).entity(res).type(MediaType.APPLICATION_JSON).build();
        } finally {
            LOGGER.trace("Leaving howRU <--");
        }
    }

    /**
     * Stats method. STUB method for now, should do something clever!
     *
     * @return json object with relevant status information
     */
    @MDCProviderSimple
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("stats")
    public Response stats() {
        LOGGER.trace("Entering stats -->");
        try {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("status", "ok");
            JsonObject jsonObject = jsonObjectBuilder.build();
            String res = jsonObject.toString();
            return Response.status(Response.Status.OK).entity(res).type(MediaType.APPLICATION_JSON).build();
        } finally {
            LOGGER.trace("Leaving stats <--");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cache")
    public Response cache() {
        LOGGER.debug("Enter cache -->");
        try {
            String result = getCacheStatistics();
            return Response.ok(result, MediaType.APPLICATION_JSON).build();
        } catch (JSONBException e) {
            return Response.serverError().build();
        } finally {
            LOGGER.trace("Exit cache <--");
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("numberstring/{val}")
    public Response numberString(@PathParam("val") final String val) {
        LOGGER.debug("Enter numberString -->");
        try {
            NumberString numberString = borChkBusinessLogic.pairintAndString(Integer.valueOf(val));

            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add(numberString.getStringNumber(), numberString.getNumber());
            JsonObject jsonObject = jsonObjectBuilder.build();
            String res = jsonObject.toString();

            return Response.status(Response.Status.OK).entity(res).type(MediaType.APPLICATION_JSON).build();
        } finally {
            LOGGER.trace("Exit numberString <--");
        }
    }

    private String getCacheStatistics() throws JSONBException {
        Map<String, Object> cacheResult = new HashMap<>();
        List<CacheStat> caches = new ArrayList<>();
        for (String name : hazelcastSetup.getCacheNames()) {
            caches.add(hazelcastSetup.getCacheStatistics(name));
        }
        cacheResult.put("result", "ok");
        cacheResult.put("cache", caches);
        return jsonbContext.marshall(cacheResult);
    }


}
