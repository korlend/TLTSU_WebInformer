package CRUD.oldtables;

/**
 * Created by Артем on 02.05.2016.
 */
public class Subjects {
    private Integer idSubject;
    private String name;

    public String getName() {
        return name;
    }

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }
}
