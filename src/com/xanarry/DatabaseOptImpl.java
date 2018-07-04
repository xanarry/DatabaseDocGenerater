package com.xanarry;

import com.xanarry.database.DataSource;
import com.xanarry.database.dao.DatabaseDao;
import com.xanarry.database.domain.Field;
import com.xanarry.database.domain.Table;
import com.xanarry.database.operation.DatabaseOpt;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DatabaseOptImpl implements DatabaseOpt {
    @Override
    public List<String> getDatabaseList() {
        SqlSession sqlSession = DataSource.getSqlSesion();
        DatabaseDao databaseDao = sqlSession.getMapper(DatabaseDao.class);
        List<String> databaseList = databaseDao.getDatabaseList();
        sqlSession.close();
        return databaseList;
    }

    @Override
    public List<Table> getTableList(String dbName) {
        SqlSession sqlSession = DataSource.getSqlSesion();
        DatabaseDao databaseDao = sqlSession.getMapper(DatabaseDao.class);
        List<Table> tableList = databaseDao.getTableList(dbName);
        sqlSession.close();
        return tableList;
    }

    @Override
    public List<Field> getFieldList(String dbName, String tableName) {
        SqlSession sqlSession = DataSource.getSqlSesion();
        DatabaseDao databaseDao = sqlSession.getMapper(DatabaseDao.class);
        List<Field> fieldList = databaseDao.getFieldList(dbName, tableName);
        sqlSession.close();
        return fieldList;
    }
}
