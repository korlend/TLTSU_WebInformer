package CRUD.oldtables;

/**
 * Created by Артем on 02.05.2016.
 */
public class Auditories {
    private Integer idAuditory;
    private String name;
    private String fullName;
    private Integer auditoryTypeId;

    public String getName() {
        return name;
    }

    public Integer getAuditoryTypeId() {
        return auditoryTypeId;
    }

    public Integer getIdAuditory() {
        return idAuditory;
    }

    public String getFullName() {
        return fullName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuditoryTypeId(Integer auditoryTypeId) {
        this.auditoryTypeId = auditoryTypeId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setIdAuditory(Integer idAuditory) {
        this.idAuditory = idAuditory;
    }
}
