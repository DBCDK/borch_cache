package dk.dbc.borchk.logging;

import org.apache.commons.lang3.StringUtils;
import org.jvnet.jaxb2_commons.lang.DefaultToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

public class BorChkToStringStrategy extends DefaultToStringStrategy {

    @Override
    public boolean isUseIdentityHashCode() {
        return false;
    }

    @Override
    public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, Object value, boolean valueSet) {
        this.appendFieldStart(parentLocator, parent, fieldName, stringBuilder, valueSet);
        if (StringUtils.equals("userPincode", fieldName) && value != null) {
            stringBuilder.append("pin code removed");
        } else {
            this.append(LocatorUtils.property(parentLocator, fieldName, value), stringBuilder, (Object) value);
        }
        this.appendFieldEnd(parentLocator, parent, fieldName, stringBuilder, valueSet);
        return stringBuilder;
    }
}
