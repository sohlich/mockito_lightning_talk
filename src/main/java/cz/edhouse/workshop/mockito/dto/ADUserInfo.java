package cz.edhouse.workshop.mockito.dto;

/**
 * Created by Radomir Sohlich on 18/03/2017.
 */
public class ADUserInfo {

    private String id;
    private String firstName;
    private String lastName;
    private String domain;
    private String email;


    public ADUserInfo(String id, String firstName, String lastName, String domain, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.domain = domain;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDomain() {
        return domain;
    }

    public String getEmail() {
        return email;
    }
}
