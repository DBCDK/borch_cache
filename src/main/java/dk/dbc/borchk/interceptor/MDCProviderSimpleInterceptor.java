package dk.dbc.borchk.interceptor;

import dk.dbc.borchk.logging.TrackingIdGenerator;
import org.slf4j.MDC;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@MDCProviderSimple
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class MDCProviderSimpleInterceptor {
    private static final String DBC_TRACKING_ID_KEY = "DBCTrackingId";

    @AroundInvoke
    public Object clearAndSetMdc(InvocationContext invocationContext) throws Exception {
        MDC.clear();
        MDC.put(DBC_TRACKING_ID_KEY, TrackingIdGenerator.getNewTrackingId());
        return invocationContext.proceed();
    }
}
