package dk.dbc.borchk;

import dk.dbc.borchk.interceptor.MDCProviderSimple;
import dk.dbc.borchk.marchallers.BorChkRequest;
import dk.dbc.oss.ns.borchk.BorrowerCheckRequest;
import dk.dbc.oss.ns.borchk.BorrowerCheckResponse;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(
        serviceName = "borrowerCheckService",
        portName = "borrowerCheckPortType",
        targetNamespace = "http://oss.dbc.dk/ns/borchk_wsdl",
        endpointInterface = "dk.dbc.oss.ns.borchk_wsdl.BorrowerCheckPortType",
        wsdlLocation = "WEB-INF/classes/wsdl/borchk.wsdl"
)
@Stateless
public class BorChk {
    private static final Logger LOGGER = LoggerFactory.getLogger(BorChk.class);

    @EJB
    private BorChkBusinessLogic borChkBusinessLogic;

    @PostConstruct
    @WebMethod(exclude = true)
    @Counted(name = "init")
    public void init() {
        LOGGER.info("Enter init -->");
        try {
            LOGGER.debug("Hello, world!");
        } finally {
            LOGGER.info("Exit init <--");
        }
    }

    @MDCProviderSimple
    @SimplyTimed(name = "borrowerCheck")
    public BorrowerCheckResponse borrowerCheck(final BorrowerCheckRequest borrowerCheckRequest) {
        LOGGER.trace("Enter borrowerCheck --> borrowerCheckRequest={}", borrowerCheckRequest);
        BorrowerCheckResponse borrowerCheckResponse = new BorrowerCheckResponse();
        try {
            BorChkRequest borChkRequest = mapRequest(borrowerCheckRequest);
            borrowerCheckResponse = borChkBusinessLogic.borrowerCheck(borChkRequest);
            return borrowerCheckResponse;
        } finally {
            LOGGER.trace("Exit borrowerCheck <-- borrowerCheckResponse={}", borrowerCheckResponse);
        }
    }

    // Map wsimport generated request to internal request format.
    // This is done purely to enable caching of requests (it's very tricky to cache wsimport generated requests).
    private BorChkRequest mapRequest(final BorrowerCheckRequest borrowerCheckRequest) {
        if (borrowerCheckRequest == null) {
            return null;
        }
        BorChkRequest borChkRequest = new BorChkRequest();
        borChkRequest.setLibraryCode(borrowerCheckRequest.getLibraryCode());
        borChkRequest.setServiceRequester(borrowerCheckRequest.getServiceRequester());
        borChkRequest.setUserId(borrowerCheckRequest.getUserId());
        borChkRequest.setUserPincode(borrowerCheckRequest.getUserPincode());
        return borChkRequest;
    }
}
