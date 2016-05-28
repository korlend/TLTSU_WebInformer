package CRUD.oldtables;

/**
 * Created by Артем on 02.05.2016.
 */
public class Chairs {
    private Integer idChair;
    private String name;
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return name;
    }

    public Integer getIdChair() {
        return idChair;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdChair(Integer idChair) {
        this.idChair = idChair;
    }
}
