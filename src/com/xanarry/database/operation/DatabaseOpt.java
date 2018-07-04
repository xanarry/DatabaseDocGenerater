package com.xanarry.database.operation;

import com.xanarry.database.domain.Field;
import com.xanarry.database.domain.Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DatabaseOpt {
    List<String> getDatabaseList();
    List<Table> getTableList(String dbName);
    public List<Field> getFieldList(String dbName, String tableName);
}
