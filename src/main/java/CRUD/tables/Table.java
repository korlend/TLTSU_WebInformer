package CRUD.tables;

/**
 * Created by Артем on 22.05.2016.
 */
public interface Table {
    /**
     * This classes for table results.
     Every class contains parameters for resulting column, with defined types.

     * Folder standard is for actual tables, existing in MySQL database and
     corresponding almost in everything with Oracle database.

     * Folder custom is for custom results, performed with special queries,
     described in classes.

     */

    public String getUNIName();
}
