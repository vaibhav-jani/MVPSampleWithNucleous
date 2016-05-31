package ws.entity;

import java.io.Serializable;

/**
 * Created by vaibhav on 30/5/16.
 */
public class SampleRequest extends BaseRequest implements Serializable {

    private String firstName;

    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
