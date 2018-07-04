package com.xanarry.database.domain;

public class Field {
    private String fieldName; //字段名
    private String type; //字段类型
    private String nullable; //是否可以为空
    private String isKey; //是否主键
    private String defaultVal; //默认值
    private String extra; //extra
    private String comment; //字段的注释,备注

    public Field() {}

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public String getIsKey() {
        return isKey;
    }

    public void setIsKey(String isKey) {
        this.isKey = isKey;
    }

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldName='" + fieldName + '\'' +
                ", type='" + type + '\'' +
                ", nullable='" + nullable + '\'' +
                ", isKey='" + isKey + '\'' +
                ", defaultVal='" + defaultVal + '\'' +
                ", extra='" + extra + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
