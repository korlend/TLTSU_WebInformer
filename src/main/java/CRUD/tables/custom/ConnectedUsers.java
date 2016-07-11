package CRUD.tables.custom;

import spring.json.SimpleJsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Артем on 13.06.2016.
 */
public class ConnectedUsers {
    private int id;
    private String device_id;
    private String connected_time;
    private List<String> preferred_groups;

    public ConnectedUsers() {}

    public ConnectedUsers(String device_id, String preferred_groups) {
        this.device_id = device_id;
        new SimpleJsonParser().parseList(preferred_groups).forEach(el -> this.preferred_groups.add(el.toString()));
    }

    @Override
    public String toString() {
        return "ConnectedUsers{" +
                "id=" + id +
                ", device_id='" + device_id + '\'' +
                ", connected_time='" + connected_time + '\'' +
                ", preferred_groups=" + preferred_groups +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return preferred_groups.contains(o);
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getDevice_id() != null ? getDevice_id().hashCode() : 0);
        result = 31 * result + (getConnected_time() != null ? getConnected_time().hashCode() : 0);
        result = 31 * result + (getPreferred_groups() != null ? getPreferred_groups().hashCode() : 0);
        return result;
    }

    public List<String> getPreferred_groups() {
        return preferred_groups;
    }

    public void setPreferred_groups(List<String> preferred_groups) {
        this.preferred_groups = preferred_groups;
    }

    public String getConnected_time() {
        return connected_time;
    }

    public void setConnected_time(String connected_time) {
        this.connected_time = connected_time;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
