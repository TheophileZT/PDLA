package model;
import java.sql.ResultSet;
public class CustomPair {
    private ResultSet resultSet;
    private Boolean booleanValue;

    public CustomPair(ResultSet resultSet, Boolean booleanValue) {
        this.resultSet = resultSet;
        this.booleanValue = booleanValue;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }
    public Boolean getBooleanValue() {
        return booleanValue;
    }
    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
}
