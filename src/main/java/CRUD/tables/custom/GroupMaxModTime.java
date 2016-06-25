package CRUD.tables.custom;

import CRUD.tables.Table;

import java.sql.Timestamp;
import java.util.Comparator;

/**
 * Created by Артем on 11.06.2016.
 */
public class GroupMaxModTime implements Comparator, Comparable, Table {
    private Timestamp maxModTime;
    private String groupName;

    @Override
    public int compare(Object o1, Object o2) {
        return ((GroupMaxModTime) o1).maxModTime.compareTo(((GroupMaxModTime) o2).maxModTime);
    }

    @Override
    public int compareTo(Object o) {
        return this.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupMaxModTime)) return false;

        GroupMaxModTime that = (GroupMaxModTime) o;

        if (getMaxModTime() != null ? !getMaxModTime().equals(that.getMaxModTime()) : that.getMaxModTime() != null)
            return false;
        return !(getGroupName() != null ? !getGroupName().equals(that.getGroupName()) : that.getGroupName() != null);

    }

    public Timestamp getMaxModTime() {
        return maxModTime;
    }

    public GroupMaxModTime setMaxModTime(Timestamp maxModTime) {
        this.maxModTime = maxModTime;
        return this;
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

    public GroupMaxModTime setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getUNIName() {
        return groupName;
    }
}
