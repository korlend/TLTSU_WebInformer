package CRUD.tables.custom;

/**
 * Created by Артем on 11.06.2016.
 */
public class GroupMaxModTime {
    private String maxModTime;
    private String groupName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupMaxModTime)) return false;

        GroupMaxModTime that = (GroupMaxModTime) o;

        if (getMaxModTime() != null ? !getMaxModTime().equals(that.getMaxModTime()) : that.getMaxModTime() != null)
            return false;
        return !(getGroupName() != null ? !getGroupName().equals(that.getGroupName()) : that.getGroupName() != null);

    }

    @Override
    public int hashCode() {
        int result = getMaxModTime() != null ? getMaxModTime().hashCode() : 0;
        result = 31 * result + (getGroupName() != null ? getGroupName().hashCode() : 0);
        return result;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getMaxModTime() {
        return maxModTime;
    }

    public GroupMaxModTime setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public GroupMaxModTime setMaxModTime(String maxModTime) {
        this.maxModTime = maxModTime;
        return this;
    }
}
