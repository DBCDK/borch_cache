package dk.dbc.borchk;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class defines the other classes that make up this JAX-RS application
 * by having the getClasses method return a specific set of resources.
 */
@ApplicationPath("api")
public class BorChkApplication extends Application {
    private static final Set<Class<?>> classes = new HashSet<>(Collections.singletonList(BorChk.class));

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
