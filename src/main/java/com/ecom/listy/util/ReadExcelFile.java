package com.ecom.listy.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by anirudh on 20/10/14.
 */
@Component
public class ReadExcelFile {

    private static final Resource excelFile=null;
    
    
    
    public static void main(String args[]) {

    	ApplicationContext appContext = 
    	    	   new ClassPathXmlApplicationContext();
    	
    	Resource excelFile = appContext.getResource("file:C:\\Basket\\ActiveBox\\Work - Projects\\Listy\\Items-index.xlsx");
    	
        List<Category> categoryList = getCategoryListsFromExcel(excelFile,4);

        for (Category cat : categoryList)
        	System.out.println(cat);
    }

    
    public static List<Category> getCategoryListsFromExcel(Resource excelFile, int onlyReadXSheets) {
        
    	
    	List<Category> categoryList = new ArrayList<Category>();	
    	
    	
    	InputStream fis = null;
        try {
            fis = excelFile.getInputStream();

            // Using XSSF for xlsx format, for xls use HSSF
            Workbook workbook = new XSSFWorkbook(fis);

            //int numberOfSheets = workbook.getNumberOfSheets();

            //looping over each workbook sheet
            for (int i = 0; i < onlyReadXSheets; i++) 
            {
            	//read first worksheet:
            	//int i = 0;
            		
            			
                Sheet sheet = workbook.getSheetAt(i);
                
                List<Item> itemsList = new ArrayList<Item>();
                Category cat = new Category();
                
                System.out.println("working on sheet : "+sheet.getSheetName());
                String[] catNames = sheet.getSheetName().split("-");
                cat.setName_en(catNames[0].trim());
                cat.setName_ar(catNames[1].trim());
                
                Iterator<Row> rowIterator = sheet.iterator();

                //iterating over each row
                while (rowIterator.hasNext()) {

                	Item item = new Item();
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();

                    //Iterating over each cell (column wise)  in a particular row.
                    while (cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();
                        
                        //Cell with index 0 contains name-en in Science
                        if (cell.getColumnIndex() == 0 && !cell.getStringCellValue().startsWith("#")) {
                            item.setName_en(cell.getStringCellValue().trim());
                        }                     
                        //Cell with index 1 contains name-ar in Science
                        else if (cell.getColumnIndex() == 1 && !cell.getStringCellValue().startsWith("#")) {
                            item.setName_ar(cell.getStringCellValue().trim());
                        }
                        //Cell with index 2 contains inventory in Science
                        else if (cell.getColumnIndex() == 2 && Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                            item.setInvQuantity((float)cell.getNumericCellValue());
                        }
                        //Cell with index 3 contains description in English
                        else if (cell.getColumnIndex() == 3 && !cell.getStringCellValue().startsWith("#")) {
                            item.setDescription(cell.getStringCellValue().trim());
                        }

                        //
                        //The Cell Containing String will is name.
//                        if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
//                            student.setName(cell.getStringCellValue());
//
//                            //The Cell Containing numeric value will contain marks
//                        } else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
//
//                            //Cell with index 1 contains marks in Maths
//                            if (cell.getColumnIndex() == 1) {
//                                student.setMaths(String.valueOf(cell.getNumericCellValue()));
//                            }
//                            //Cell with index 2 contains marks in Science
//                            else if (cell.getColumnIndex() == 2) {
//                                student.setScience(String.valueOf(cell.getNumericCellValue()));
//                            }
//                            //Cell with index 3 contains marks in English
//                            else if (cell.getColumnIndex() == 3) {
//                                student.setEnglish(String.valueOf(cell.getNumericCellValue()));
//                            }
//                        }
                    }
                    //end iterating a row, add all the elements of a row in list
                    //System.out.println(item);
                    
                    //to skip addin the header row, 
                    if (item.getName_en() != null)
                    	itemsList.add(item);
                }
                
                cat.setItems(itemsList);
                categoryList.add(cat);
            }

            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        return categoryList;
    }


}
