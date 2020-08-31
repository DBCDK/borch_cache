package dk.dbc.borchk;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NumberString implements Serializable {

    private static final long serialVersionUID = 2511858674839189779L;
    private int number;
    private String stringNumber;


    public NumberString(int number) {
        this.number = number;
        this.stringNumber = fromIntToString(number);
    }

    private String fromIntToString (int value) {
        return NumberToWords.getName(value);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStringNumber() {
        return stringNumber;
    }

    public void setStringNumber(String stringNumber) {
        this.stringNumber = stringNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumberString)) return false;
        NumberString that = (NumberString) o;
        return getNumber() == that.getNumber() &&
                Objects.equals(getStringNumber(), that.getStringNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getStringNumber());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("number", number)
                .append("stringNumber", stringNumber)
                .toString();
    }
}
