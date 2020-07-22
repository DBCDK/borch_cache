package dk.dbc.borchk;

import dk.dbc.borchk.interceptor.MDCProviderSimple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("")
public class BorChk {
    private static final Logger LOGGER = LoggerFactory.getLogger(BorChk.class);

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
}
