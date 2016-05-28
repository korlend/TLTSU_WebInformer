package CRUD.oldtables;

/**
 * Created by Артем on 02.05.2016.
 */
//KindOfWork in oracle
public class LessonTypes {
    private Integer idLessonType;
    private String name;

    public String getName() {
        return name;
    }

    public Integer getIdLessonType() {
        return idLessonType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdLessonType(Integer idLessonType) {
        this.idLessonType = idLessonType;
    }
}
