package com.korlend.CRUD.tables.standard;

import com.korlend.CRUD.tables.Table;

import java.util.Comparator;

/**
 * Created by Артем on 07.07.2016.
 */
public class ContentTableOfLesson implements Comparable, Comparator, Table {
    private int OID;
    private int TimeBeg;
    private int TimeEnd;

    @Override
    public int compare(Object o1, Object o2) {
        return ((ContentTableOfLesson) o1).getOID() < ((ContentTableOfLesson) o2).getOID() ? -1 :
                ((ContentTableOfLesson) o1).getOID() > ((ContentTableOfLesson) o2).getOID() ? 1 : 0;
    }

    @Override
    public int compareTo(Object o) {
        return this.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContentTableOfLesson)) return false;

        ContentTableOfLesson that = (ContentTableOfLesson) o;

        if (getOID() != that.getOID()) return false;
        if (getTimeBeg() != that.getTimeBeg()) return false;
        return getTimeEnd() == that.getTimeEnd();

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + getTimeBeg();
        result = 31 * result + getTimeEnd();
        return result;
    }

    public String getUNIName() {
        return Integer.toString(OID);
    }

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public int getTimeBeg() {
        return TimeBeg;
    }

    public void setTimeBeg(int timeBeg) {
        TimeBeg = timeBeg;
    }

    public int getTimeEnd() {
        return TimeEnd;
    }

    public void setTimeEnd(int timeEnd) {
        TimeEnd = timeEnd;
    }
}
