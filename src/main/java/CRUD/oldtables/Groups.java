package CRUD.oldtables;

/**
 * Created by Артем on 02.05.2016.
 */
public class Groups {
    private Integer idGroup;
    private String name;

    public String getName() {
        return name;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }
}
