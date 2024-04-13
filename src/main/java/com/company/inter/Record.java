package com.company.inter;

import java.sql.Timestamp;

/**
 *
 * @author islam
 */
public abstract class Record {
    private int recordId;
    private Timestamp date;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    public abstract Object[] toTableRow(int row);

    public abstract Object[] getColumnNames();
}
