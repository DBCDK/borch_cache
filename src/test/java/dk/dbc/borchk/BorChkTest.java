package dk.dbc.borchk;

import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BorChkTest {

    @Test
    public void test() {
        BorChkInfo borChk = new BorChkInfo();
        Response response = borChk.howRU();
        String responseString = (String) response.getEntity();
        assertThat(responseString, is("{\"status\":\"ok\"}"));
    }
}
