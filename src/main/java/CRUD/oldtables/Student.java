package CRUD.oldtables;

/**
 * Created by Артем on 01.05.2016.
 */
public class Student {
    private Integer groupId;
    private String fullName;
    private Integer id;

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    public Integer getGroupId() {
        return groupId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getFullName() {
        return fullName;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
}
