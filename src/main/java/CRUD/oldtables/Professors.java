package CRUD.oldtables;

/**
 * Created by Артем on 02.05.2016.
 */
public class Professors {
    private Integer idProfessor;
    private String name;
    private String fullName;
    private Integer chairId;

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setChairId(Integer chairId) {
        this.chairId = chairId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Integer getChairId() {
        return chairId;
    }
}
