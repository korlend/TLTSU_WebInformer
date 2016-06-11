package CRUD.tables.custom;

/**
 * Created by Артем on 11.06.2016.
 */
public class GroupMaxModTime {
    private String maxModTime;
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public String getMaxModTime() {
        return maxModTime;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setMaxModTime(String maxModTime) {
        this.maxModTime = maxModTime;
    }
}
