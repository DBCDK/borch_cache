package dk.dbc.borchk.logging;

import java.util.UUID;

public class TrackingIdGenerator {

    public static String getNewTrackingId() {
        return UUID.randomUUID().toString();
    }
}
