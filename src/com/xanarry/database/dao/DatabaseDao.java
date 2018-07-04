package com.xanarry.database.dao;



import com.xanarry.database.domain.Field;
import com.xanarry.database.domain.Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DatabaseDao {
    List<String> getDatabaseList();
    List<Table> getTableList(@Param("dbName") String dbName);
    List<Field> getFieldList(@Param("dbName") String dbName, @Param("tableName") String tableName);
}
