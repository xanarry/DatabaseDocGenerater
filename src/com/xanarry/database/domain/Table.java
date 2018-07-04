package com.xanarry.database.domain;

import java.util.List;

public class Table {
    private String name;
    private String comment;
    private List<Field> fieldList;

    public Table() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    @Override
    public String toString() {
        return "com.xanarry.database.domain.Table{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", fieldList=" + fieldList +
                '}';
    }
}
