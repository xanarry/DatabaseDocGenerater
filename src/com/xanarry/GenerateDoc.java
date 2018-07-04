package com.xanarry;


import com.xanarry.database.domain.Field;
import com.xanarry.database.domain.Table;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridCol;

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
            insertItem(doc, t);
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

    private static void insertItem(XWPFDocument doc, Table table) {
        //创建段落
        XWPFParagraph p1 = doc.createParagraph();

        //写入段落内容
        XWPFRun run = p1.createRun();
        run.addBreak();
        run.addBreak();
        run.setText(String.format("%s(%s)", table.getName(), table.getComment()));
        //插入字段
        insertCells(doc, table.getFieldList());
    }

    private static void insertCells(XWPFDocument doc, List<Field> fieldList) {
        //创建有5列的表格
        XWPFTable table = doc.createTable(fieldList.size() + 1, 5);
        //设置表格宽度
        setW(table, 5);
        //插入表头
        insertHeader(table);

        //表字段数据插入
        for (int i = 0; i < fieldList.size(); i++) {
            List<XWPFTableCell> tableCells = table.getRow(i + 1).getTableCells();
            Field field = fieldList.get(i);
            tableCells.get(0).setText(field.getFieldName());
            tableCells.get(1).setText(field.getType());
            tableCells.get(2).setText(field.getIsKey());
            tableCells.get(3).setText(field.getNullable());
            tableCells.get(4).setText(field.getComment());
        }
    }



    private static void insertHeader(XWPFTable table) {
        List<XWPFTableCell> tableCells = table.getRow(0).getTableCells();
        tableCells.get(0).setText("字段名");
        tableCells.get(1).setText("类型");
        tableCells.get(2).setText("是否主键");
        tableCells.get(3).setText("是否必填");
        tableCells.get(4).setText("备注");
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
