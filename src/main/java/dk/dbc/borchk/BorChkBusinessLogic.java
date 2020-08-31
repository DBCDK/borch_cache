package dk.dbc.borchk;

import dk.dbc.borchk.marchallers.BorChkRequest;
import dk.dbc.oss.ns.borchk.BorrowerCheckResponse;
import dk.dbc.oss.ns.borchk.StatusType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.cache.annotation.CacheResult;
import javax.ejb.Stateless;

@Stateless
public class BorChkBusinessLogic {
    private static final Logger LOGGER = LoggerFactory.getLogger(BorChkBusinessLogic.class);


    @CacheResult(cacheName = "BORCHK_CACHE")
    public BorrowerCheckResponse borrowerCheck(final BorChkRequest borChkRequest) {
        LOGGER.trace("Enter borrowerCheck -->");
        BorrowerCheckResponse borrowerCheckResponse = new BorrowerCheckResponse();
        try {
            StatusType statusType = validateRequest(borChkRequest);
            if (statusType != StatusType.OK) {
                borrowerCheckResponse.setRequestStatus(statusType);
                return borrowerCheckResponse;
            }
            // TODO: THL: some magic happens here
            return borrowerCheckResponse;
        } finally {
            LOGGER.trace("Exit borrowerCheck <-- borrowerCheckResponse={}", borrowerCheckResponse);
        }
    }

    private StatusType validateRequest(final BorChkRequest borChkRequest) {
        if (borChkRequest == null) {
            return StatusType.SERVICE_UNAVAILABLE;
        }
        if (StringUtils.isBlank(borChkRequest.getUserId())) {
            return StatusType.NO_USER_IN_REQUEST;
        }
        return StatusType.OK;
    }


    @CacheResult(cacheName = "BORCHK_CACHE")
    public NumberString pairintAndString(final int val) {
        LOGGER.trace("Enter pairintAndString -->");
        try {
            return new NumberString(val);
        } finally {
            LOGGER.trace("Exit pairintAndString <--");
        }
    }


}
