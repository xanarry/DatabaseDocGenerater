package com.xanarry;


import com.xanarry.database.domain.Table;
import com.xanarry.database.operation.DatabaseOpt;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static String selectDB(List<String> dbList) {
        for (int i = 1; i <= dbList.size(); i++) {
            System.out.println(i + ": " + dbList.get(i - 1));
        }
        System.out.print(String.format("please select one[1-%s], 0 to exit: ", dbList.size()));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int index = scanner.nextInt();
            if (index == 0) {
                System.exit(0);
            }
            if (index >= 1 && index <= dbList.size()) {
                return dbList.get(index - 1);
            }
            System.out.print("invalid input, input again: ");
        }
    }

    public static void main(String[] args) {
        DatabaseOpt databaseOpt = new DatabaseOptImpl();
        List<String> dbList = databaseOpt.getDatabaseList();
        String dbName = selectDB(dbList);

        System.out.println("you chosen: \"" + dbName + "\" doc is generating");

        List<Table> tableList = databaseOpt.getTableList(dbName);
        for (int i = 0; i < tableList.size(); i++) {
            Table table = tableList.get(i);
            table.setFieldList(databaseOpt.getFieldList(dbName, table.getName()));
        }

        try {
            GenerateDoc.createDoc(tableList, dbName + ".doc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("finished");
    }
}
