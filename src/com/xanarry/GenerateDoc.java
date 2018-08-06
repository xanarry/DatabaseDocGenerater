package com.xanarry;


import com.xanarry.database.domain.Field;
import com.xanarry.database.domain.Table;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class GenerateDoc {
    public static void createDoc(List<Table> tableList, String docPath) throws IOException {
        //创建新的word
        XWPFDocument doc = new XWPFDocument();
        for (Table t : tableList) {
            insertTableToDoc(doc, t);
        }

        try {
            //导出到本地,E:\demo.docx
            FileOutputStream out = new FileOutputStream(docPath);
            try {
                doc.write(out);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void insertTableToDoc(XWPFDocument doc, Table table) {
        //创建段落
        XWPFParagraph p1 = doc.createParagraph();

        //写入段落内容
        XWPFRun run = p1.createRun();
        run.addBreak();
        run.addBreak();
        run.setText(String.format("%s(%s)", table.getName(), table.getComment()));
        //插入字段
        insertTableContent(doc, table);
    }

    private static void insertTableContent(XWPFDocument doc, Table dbtable) {
        List<Field> fieldList = dbtable.getFieldList();
        //创建有5列的表格, 加三行是一行为表名, 一行为表功能,一行为表头, 后面才是实际内容
        XWPFTable table = doc.createTable(fieldList.size() + 3, 5);
        //设置表格宽度
        setW(table, 5);
        //插入表头
        insertHeader(table, dbtable.getName(), dbtable.getComment());

        //表字段数据插入
        for (int i = 0; i < fieldList.size(); i++) {
            List<XWPFTableCell> tableCells = table.getRow(i + 3).getTableCells();
            Field field = fieldList.get(i);
            tableCells.get(0).setText(field.getFieldName());
            tableCells.get(1).setText(field.getType());
            tableCells.get(2).setText(field.getIsKey());
            tableCells.get(3).setText(field.getNullable().toLowerCase().equals("yes") ? "" : "NOT NULL");
            tableCells.get(4).setText(field.getComment());
        }
    }



    private static void insertHeader(XWPFTable table, String dbTableName, String dbTableComment) {
        List<XWPFTableCell> tableName = table.getRow(0).getTableCells();
        tableName.get(0).setText("表名");
        //将第一列到第四列合并
        for (int i = 1; i <= 4; i++) {
            if (i == 1)
                tableName.get(i).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            else
                tableName.get(i).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
        }
        tableName.get(1).setText(dbTableName);

        List<XWPFTableCell> tableFunction = table.getRow(1).getTableCells();
        tableFunction.get(0).setText("功能");
        for (int i = 1; i <= 4; i++) {
            if (i == 1)
                tableFunction.get(i).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            else
                tableFunction.get(i).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
        }
        tableFunction.get(1).setText(dbTableComment);


        //设置表头
        List<XWPFTableCell> tableHeads = table.getRow(2).getTableCells();
        tableHeads.get(0).setText("字段名");
        tableHeads.get(1).setText("类型");
        tableHeads.get(2).setText("是否主键");
        tableHeads.get(3).setText("约束");
        tableHeads.get(4).setText("备注");
    }

    private static void setW(XWPFTable table, int colNum) {
        CTTbl ttbl = table.getCTTbl();
        CTTblGrid tblGrid = ttbl.getTblGrid() != null ? ttbl.getTblGrid()
                : ttbl.addNewTblGrid();
        for (int i = 0; i < colNum; i++) {
            CTTblGridCol gridCol = tblGrid.addNewGridCol();
            gridCol.setW(new BigInteger("" + 1700));
        }
        table.setCellMargins(20, 20, 20, 20);
    }
}
