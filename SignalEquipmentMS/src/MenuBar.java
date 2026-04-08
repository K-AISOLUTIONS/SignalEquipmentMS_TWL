import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class MenuBar extends JMenuBar implements ActionListener {

    JMenu fileMenu; JMenu editMenu; JMenu helpMenu;
    JMenuItem loadItem; JMenuItem saveItem; JMenuItem exitItem;
    MenuBar(){

        fileMenu = new JMenu("File");

        this.add(fileMenu);

        loadItem = new JMenuItem("Load");

        loadItem.addActionListener(this);

        fileMenu.add(loadItem);

    }
    public void actionPerformed(ActionEvent e) {
        // load all the data from Excel and save them in different variables
        if(e.getSource() == loadItem){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Open file");
            fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
            FileFilter filter = new FileNameExtensionFilter("Excel File (.xlsx)", "xlsx");
            fileChooser.setFileFilter(filter);

            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File inputfile = fileChooser.getSelectedFile();
                try (FileInputStream in = new FileInputStream(inputfile)) {
                    XSSFWorkbook importedFile = new XSSFWorkbook(in);
                    for (int x = 0; x < importedFile.getNumberOfSheets(); x++) {
                        XSSFSheet currentSheet = importedFile.getSheetAt(x);
                        String currentSheetName = importedFile.getSheetName(x);
                        Iterator<Row> rowIterator = currentSheet.iterator();
                        switch (currentSheetName) {
                            case "Track Circuit":
                                ArrayList<String> tempLines = new ArrayList<>();
                                ArrayList<String> tempStations = new ArrayList<>();
                                ArrayList<String> tempSides = new ArrayList<>();
                                ArrayList<String> tempTrackIds = new ArrayList<>();
                                ArrayList<Double> tempLocationsLeft = new ArrayList<>();
                                ArrayList<Double> tempLocationsRight = new ArrayList<>();
                                ArrayList<Double> tempDistances = new ArrayList<>();

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    while(cellIterator.hasNext()){
                                        Cell cell = cellIterator.next();
                                        if (row.getRowNum() == 0) {

                                        } else {
                                            if (cell.getColumnIndex() == 0) {
                                                tempLines.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 1) {
                                                tempStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 2) {
                                                tempSides.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 3) {
                                                tempTrackIds.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 4) {
                                                tempLocationsLeft.add(cell.getNumericCellValue());
                                            } else if (cell.getColumnIndex() == 5) {
                                                tempLocationsRight.add(cell.getNumericCellValue());
                                            } else if (cell.getColumnIndex() == 6) {
                                                tempDistances.add(cell.getNumericCellValue());
                                            }
                                        }
                                    }
                                }


                                int TWL = 0; int KTL = 0; int TKL = 0; int ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.trackLines = new String[TWL];
                                TWLPanel.trackStations = new String[TWL];
                                TWLPanel.trackSides = new String[TWL];
                                TWLPanel.trackIds = new String[TWL];
                                TWLPanel.trackLocationsLeft = new double[TWL];
                                TWLPanel.trackLocationsRight = new double[TWL];
                                TWLPanel.trackDistances = new double[TWL];

                                KTLPanel.trackLines = new String[KTL];
                                KTLPanel.trackStations = new String[KTL];
                                KTLPanel.trackSides = new String[KTL];
                                KTLPanel.trackIds = new String[KTL];
                                KTLPanel.trackLocationsLeft = new double[KTL];
                                KTLPanel.trackLocationsRight = new double[KTL];
                                KTLPanel.trackDistances = new double[KTL];

                                TKLPanel.trackLines = new String[TKL];
                                TKLPanel.trackStations = new String[TKL];
                                TKLPanel.trackSides = new String[TKL];
                                TKLPanel.trackIds = new String[TKL];
                                TKLPanel.trackLocationsLeft = new double[TKL];
                                TKLPanel.trackLocationsRight = new double[TKL];
                                TKLPanel.trackDistances = new double[TKL];

                                ISLPanel.trackLines = new String[ISL];
                                ISLPanel.trackStations = new String[ISL];
                                ISLPanel.trackSides = new String[ISL];
                                ISLPanel.trackIds = new String[ISL];
                                ISLPanel.trackLocationsLeft = new double[ISL];
                                ISLPanel.trackLocationsRight = new double[ISL];
                                ISLPanel.trackDistances = new double[ISL];

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    //System.out.println(tempLines.size());
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.trackLines[TWL] = tempLines.get(i);
                                        TWLPanel.trackStations[TWL] = tempStations.get(i);
                                        TWLPanel.trackSides[TWL] = tempSides.get(i);
                                        TWLPanel.trackIds[TWL] = tempTrackIds.get(i);
                                        TWLPanel.trackLocationsLeft[TWL] = tempLocationsLeft.get(i);
                                        TWLPanel.trackLocationsRight[TWL] = tempLocationsRight.get(i);
                                        TWLPanel.trackDistances[TWL] = tempDistances.get(i);
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTLPanel.trackLines[KTL] = tempLines.get(i);
                                        KTLPanel.trackStations[KTL] = tempStations.get(i);
                                        KTLPanel.trackSides[KTL] = tempSides.get(i);
                                        KTLPanel.trackIds[KTL] = tempTrackIds.get(i);
                                        KTLPanel.trackLocationsLeft[KTL] = tempLocationsLeft.get(i);
                                        KTLPanel.trackLocationsRight[KTL] = tempLocationsRight.get(i);
                                        KTLPanel.trackDistances[KTL] = tempDistances.get(i);
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKLPanel.trackLines[TKL] = tempLines.get(i);
                                        TKLPanel.trackStations[TKL] = tempStations.get(i);
                                        TKLPanel.trackSides[TKL] = tempSides.get(i);
                                        TKLPanel.trackIds[TKL] = tempTrackIds.get(i);
                                        TKLPanel.trackLocationsLeft[TKL] = tempLocationsLeft.get(i);
                                        TKLPanel.trackLocationsRight[TKL] = tempLocationsRight.get(i);
                                        TKLPanel.trackDistances[TKL] = tempDistances.get(i);
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISLPanel.trackLines[ISL] = tempLines.get(i);
                                        ISLPanel.trackStations[ISL] = tempStations.get(i);
                                        ISLPanel.trackSides[ISL] = tempSides.get(i);
                                        ISLPanel.trackIds[ISL] = tempTrackIds.get(i);
                                        ISLPanel.trackLocationsLeft[ISL] = tempLocationsLeft.get(i);
                                        ISLPanel.trackLocationsRight[ISL] = tempLocationsRight.get(i);
                                        ISLPanel.trackDistances[ISL] = tempDistances.get(i);
                                        ISL++;
                                    }
                                }

                                break;
                            case "Platform":

                                tempLines = new ArrayList<>();
                                tempStations = new ArrayList<>();
                                ArrayList<Double> tempMidPtsUp = new ArrayList<>();
                                ArrayList<Double> tempMidPtsDn = new ArrayList<>();
                                ArrayList<String> tempNames = new ArrayList<>();
                                ArrayList<String> tempSinglePlat = new ArrayList<>();
                                ArrayList<String> tempPlatNumUp = new ArrayList<>();
                                ArrayList<String> tempPlatNumDn = new ArrayList<>();

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    while(cellIterator.hasNext()){
                                        Cell cell = cellIterator.next();
                                        if (row.getRowNum() == 0) {

                                        } else {
                                            if (cell.getColumnIndex() == 0) {
                                                tempLines.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 1) {
                                                tempStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 2) {
                                                tempMidPtsUp.add(cell.getNumericCellValue());
                                            } else if (cell.getColumnIndex() == 3) {
                                                tempMidPtsDn.add(cell.getNumericCellValue());
                                            } else if (cell.getColumnIndex() == 4) {
                                                tempNames.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 5) {
                                                tempSinglePlat.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 6) {
                                                tempPlatNumUp.add(Integer.toString((int)cell.getNumericCellValue()));
                                            } else if (cell.getColumnIndex() == 7) {
                                                tempPlatNumDn.add(Integer.toString((int)cell.getNumericCellValue()));
                                            }
                                        }
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.platformLines = new String[TWL];
                                TWLPanel.platformStations = new String[TWL];
                                TWLPanel.platformMidPtsUp = new double[TWL];
                                TWLPanel.platformMidPtsDn = new double[TWL];
                                TWLPanel.platformNames = new String[TWL];
                                TWLPanel.platformIsSingle = new String[TWL];
                                TWLPanel.platformNumberUp = new String[TWL];
                                TWLPanel.platformNumberDn = new String[TWL];

                                KTLPanel.platformLines = new String[KTL];
                                KTLPanel.platformStations = new String[KTL];
                                KTLPanel.platformMidPtsUp = new double[KTL];
                                KTLPanel.platformMidPtsDn = new double[KTL];
                                KTLPanel.platformNames = new String[KTL];
                                KTLPanel.platformIsSingle = new String[KTL];
                                KTLPanel.platformNumberUp = new String[KTL];
                                KTLPanel.platformNumberDn = new String[KTL];

                                TKLPanel.platformLines = new String[TKL];
                                TKLPanel.platformStations = new String[TKL];
                                TKLPanel.platformMidPtsUp = new double[TKL];
                                TKLPanel.platformMidPtsDn = new double[TKL];
                                TKLPanel.platformNames = new String[TKL];
                                TKLPanel.platformIsSingle = new String[TKL];
                                TKLPanel.platformNumberUp = new String[TKL];
                                TKLPanel.platformNumberDn = new String[TKL];

                                ISLPanel.platformLines = new String[ISL];
                                ISLPanel.platformStations = new String[ISL];
                                ISLPanel.platformMidPtsUp = new double[ISL];
                                ISLPanel.platformMidPtsDn = new double[ISL];
                                ISLPanel.platformNames = new String[ISL];
                                ISLPanel.platformIsSingle = new String[ISL];
                                ISLPanel.platformNumberUp = new String[ISL];
                                ISLPanel.platformNumberDn = new String[ISL];


                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.platformLines[TWL] = tempLines.get(i);
                                        TWLPanel.platformStations[TWL] = tempStations.get(i);
                                        TWLPanel.platformMidPtsUp[TWL] = tempMidPtsUp.get(i);
                                        TWLPanel.platformMidPtsDn[TWL] = tempMidPtsDn.get(i);
                                        TWLPanel.platformNames[TWL] = tempNames.get(i);
                                        TWLPanel.platformIsSingle[TWL] = tempSinglePlat.get(i);
                                        TWLPanel.platformNumberUp[TWL] = tempPlatNumUp.get(i);
                                        TWLPanel.platformNumberDn[TWL] = tempPlatNumDn.get(i);
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTLPanel.platformLines[KTL] = tempLines.get(i);
                                        KTLPanel.platformStations[KTL] = tempStations.get(i);
                                        KTLPanel.platformMidPtsUp[KTL] = tempMidPtsUp.get(i);
                                        KTLPanel.platformMidPtsDn[KTL] = tempMidPtsDn.get(i);
                                        KTLPanel.platformNames[KTL] = tempNames.get(i);
                                        KTLPanel.platformIsSingle[KTL] = tempSinglePlat.get(i);
                                        KTLPanel.platformNumberUp[KTL] = tempPlatNumUp.get(i);
                                        KTLPanel.platformNumberDn[KTL] = tempPlatNumDn.get(i);
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKLPanel.platformLines[TKL] = tempLines.get(i);
                                        TKLPanel.platformStations[TKL] = tempStations.get(i);
                                        TKLPanel.platformMidPtsUp[TKL] = tempMidPtsUp.get(i);
                                        TKLPanel.platformMidPtsDn[TKL] = tempMidPtsDn.get(i);
                                        TKLPanel.platformNames[TKL] = tempNames.get(i);
                                        TKLPanel.platformIsSingle[TKL] = tempSinglePlat.get(i);
                                        TKLPanel.platformNumberUp[TKL] = tempPlatNumUp.get(i);
                                        TKLPanel.platformNumberDn[TKL] = tempPlatNumDn.get(i);
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISLPanel.platformLines[ISL] = tempLines.get(i);
                                        ISLPanel.platformStations[ISL] = tempStations.get(i);
                                        ISLPanel.platformMidPtsUp[ISL] = tempMidPtsUp.get(i);
                                        ISLPanel.platformMidPtsDn[ISL] = tempMidPtsDn.get(i);
                                        ISLPanel.platformNames[ISL] = tempNames.get(i);
                                        ISLPanel.platformIsSingle[ISL] = tempSinglePlat.get(i);
                                        ISLPanel.platformNumberUp[ISL] = tempPlatNumUp.get(i);
                                        ISLPanel.platformNumberDn[ISL] = tempPlatNumDn.get(i);
                                        ISL++;
                                    }
                                }

                                break;
                            case "Signal":

                                tempLines = new ArrayList<>();
                                tempStations = new ArrayList<>();
                                tempSides = new ArrayList<>();
                                ArrayList<String> tempSignalIds = new ArrayList<>();
                                ArrayList<String> tempTypes = new ArrayList<>();
                                ArrayList<Double> tempLocations = new ArrayList<>();
                                ArrayList<String> tempLeftOrRights = new ArrayList<>();

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    while(cellIterator.hasNext()){
                                        Cell cell = cellIterator.next();
                                        if (row.getRowNum() == 0) {

                                        } else {
                                            if (cell.getColumnIndex() == 0) {
                                                tempLines.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 1) {
                                                tempStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 2) {
                                                tempSides.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 3) {
//                                    System.out.println(cell.getCellType());
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    //System.out.println(cell.getNumericCellValue())
                                                    int tempCell = (int)cell.getNumericCellValue();
                                                    String tempString = Integer.toString(tempCell);
                                                    if (tempCell < 10) {
                                                        tempString = "0" + tempString;
                                                    }
                                                    tempSignalIds.add(tempString);
                                                    //System.out.println(tempCell);
                                                } else {
                                                    //System.out.println(cell.getStringCellValue());
                                                    String tempString = cell.getStringCellValue();
                                                    if (tempString.equals("N/A,0")) {
                                                        tempString = "";
                                                    }
                                                    if (tempString.equals("N/A,1")) {
                                                        tempString = " ";
                                                    }
                                                    tempSignalIds.add(tempString);
                                                }
                                            } else if (cell.getColumnIndex() == 4) {
                                                tempTypes.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 5) {
                                                tempLocations.add(cell.getNumericCellValue());
                                            } else if (cell.getColumnIndex() == 6) {
                                                tempLeftOrRights.add(cell.getStringCellValue());
                                            }
                                        }
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.signalLines = new String[TWL];
                                TWLPanel.signalStations = new String[TWL];
                                TWLPanel.signalSides = new String[TWL];
                                TWLPanel.signalIds = new String[TWL];
                                TWLPanel.signalTypes = new String[TWL];
                                TWLPanel.signalLocations = new double[TWL];
                                TWLPanel.signalLeftOrRights = new String[TWL];

                                KTLPanel.signalLines = new String[KTL];
                                KTLPanel.signalStations = new String[KTL];
                                KTLPanel.signalSides = new String[KTL];
                                KTLPanel.signalIds = new String[KTL];
                                KTLPanel.signalTypes = new String[KTL];
                                KTLPanel.signalLocations = new double[KTL];
                                KTLPanel.signalLeftOrRights = new String[KTL];

                                TKLPanel.signalLines = new String[TKL];
                                TKLPanel.signalStations = new String[TKL];
                                TKLPanel.signalSides = new String[TKL];
                                TKLPanel.signalIds = new String[TKL];
                                TKLPanel.signalTypes = new String[TKL];
                                TKLPanel.signalLocations = new double[TKL];
                                TKLPanel.signalLeftOrRights = new String[TKL];

                                ISLPanel.signalLines = new String[ISL];
                                ISLPanel.signalStations = new String[ISL];
                                ISLPanel.signalSides = new String[ISL];
                                ISLPanel.signalIds = new String[ISL];
                                ISLPanel.signalTypes = new String[ISL];
                                ISLPanel.signalLocations = new double[ISL];
                                ISLPanel.signalLeftOrRights = new String[ISL];


                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.signalLines[TWL] = tempLines.get(i);
                                        TWLPanel.signalStations[TWL] = tempStations.get(i);
                                        TWLPanel.signalSides[TWL] = tempSides.get(i);
                                        TWLPanel.signalIds[TWL] = tempSignalIds.get(i);
                                        TWLPanel.signalTypes[TWL] = tempTypes.get(i);
                                        TWLPanel.signalLocations[TWL] = tempLocations.get(i);
                                        TWLPanel.signalLeftOrRights[TWL] = tempLeftOrRights.get(i);
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTLPanel.signalLines[KTL] = tempLines.get(i);
                                        KTLPanel.signalStations[KTL] = tempStations.get(i);
                                        KTLPanel.signalSides[KTL] = tempSides.get(i);
                                        KTLPanel.signalIds[KTL] = tempSignalIds.get(i);
                                        KTLPanel.signalTypes[KTL] = tempTypes.get(i);
                                        KTLPanel.signalLocations[KTL] = tempLocations.get(i);
                                        KTLPanel.signalLeftOrRights[KTL] = tempLeftOrRights.get(i);
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKLPanel.signalLines[TKL] = tempLines.get(i);
                                        TKLPanel.signalStations[TKL] = tempStations.get(i);
                                        TKLPanel.signalSides[TKL] = tempSides.get(i);
                                        TKLPanel.signalIds[TKL] = tempSignalIds.get(i);
                                        TKLPanel.signalTypes[TKL] = tempTypes.get(i);
                                        TKLPanel.signalLocations[TKL] = tempLocations.get(i);
                                        TKLPanel.signalLeftOrRights[TKL] = tempLeftOrRights.get(i);
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISLPanel.signalLines[ISL] = tempLines.get(i);
                                        ISLPanel.signalStations[ISL] = tempStations.get(i);
                                        ISLPanel.signalSides[ISL] = tempSides.get(i);
                                        ISLPanel.signalIds[ISL] = tempSignalIds.get(i);
                                        ISLPanel.signalTypes[ISL] = tempTypes.get(i);
                                        ISLPanel.signalLocations[ISL] = tempLocations.get(i);
                                        ISLPanel.signalLeftOrRights[ISL] = tempLeftOrRights.get(i);
                                        ISL++;
                                    }
                                }

                                break;
                            case "Switch":
                                rowIterator = currentSheet.iterator();


                                tempLines = new ArrayList<>();
                                tempStations = new ArrayList<>();
                                tempSides = new ArrayList<>();
                                ArrayList<String> tempSwitchIds = new ArrayList<>();
                                tempLocations = new ArrayList<>();

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    while(cellIterator.hasNext()){
                                        Cell cell = cellIterator.next();
                                        if (row.getRowNum() == 0) {

                                        } else {
                                            if (cell.getColumnIndex() == 0) {
                                                tempLines.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 1) {
                                                tempStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 2) {
                                                tempSides.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 3) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    String tempString = Integer.toString((int)cell.getNumericCellValue());
                                                    tempSwitchIds.add(tempString);
                                                } else {
                                                    tempSwitchIds.add(cell.getStringCellValue());
                                                }
                                            } else if (cell.getColumnIndex() == 4) {
                                                tempLocations.add(cell.getNumericCellValue());
                                            }
                                        }
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.switchLines = new String[TWL];
                                TWLPanel.switchStations = new String[TWL];
                                TWLPanel.switchSides = new String[TWL];
                                TWLPanel.switchIds = new String[TWL];
                                TWLPanel.switchLocations = new double[TWL];

                                KTLPanel.switchLines = new String[KTL];
                                KTLPanel.switchStations = new String[KTL];
                                KTLPanel.switchSides = new String[KTL];
                                KTLPanel.switchIds = new String[KTL];
                                KTLPanel.switchLocations = new double[KTL];

                                TKLPanel.switchLines = new String[TKL];
                                TKLPanel.switchStations = new String[TKL];
                                TKLPanel.switchSides = new String[TKL];
                                TKLPanel.switchIds = new String[TKL];
                                TKLPanel.switchLocations = new double[TKL];

                                ISLPanel.switchLines = new String[ISL];
                                ISLPanel.switchStations = new String[ISL];
                                ISLPanel.switchSides = new String[ISL];
                                ISLPanel.switchIds = new String[ISL];
                                ISLPanel.switchLocations = new double[ISL];


                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.switchLines[TWL] = tempLines.get(i);
                                        TWLPanel.switchStations[TWL] = tempStations.get(i);
                                        TWLPanel.switchSides[TWL] = tempSides.get(i);
                                        TWLPanel.switchIds[TWL] = tempSwitchIds.get(i);
                                        TWLPanel.switchLocations[TWL] = tempLocations.get(i);
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTLPanel.switchLines[KTL] = tempLines.get(i);
                                        KTLPanel.switchStations[KTL] = tempStations.get(i);
                                        KTLPanel.switchSides[KTL] = tempSides.get(i);
                                        KTLPanel.switchIds[KTL] = tempSwitchIds.get(i);
                                        KTLPanel.switchLocations[KTL] = tempLocations.get(i);
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKLPanel.switchLines[TKL] = tempLines.get(i);
                                        TKLPanel.switchStations[TKL] = tempStations.get(i);
                                        TKLPanel.switchSides[TKL] = tempSides.get(i);
                                        TKLPanel.switchIds[TKL] = tempSwitchIds.get(i);
                                        TKLPanel.switchLocations[TKL] = tempLocations.get(i);
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISLPanel.switchLines[ISL] = tempLines.get(i);
                                        ISLPanel.switchStations[ISL] = tempStations.get(i);
                                        ISLPanel.switchSides[ISL] = tempSides.get(i);
                                        ISLPanel.switchIds[ISL] = tempSwitchIds.get(i);
                                        ISLPanel.switchLocations[ISL] = tempLocations.get(i);
                                        ISL++;
                                    }
                                }

                                break;
                            case "Crossing":

                                tempLines = new ArrayList<>();
                                tempStations = new ArrayList<>();
                                tempSides = new ArrayList<>();
                                ArrayList<String> tempCrossingIds = new ArrayList<>();
                                ArrayList<Double> tempInterceptsUp = new ArrayList<>();
                                ArrayList<Double> tempInterceptsDn = new ArrayList<>();
                                ArrayList<String> tempSwitchesConnected = new ArrayList<>();
                                ArrayList<Double> tempTopLeftUps = new ArrayList<>();
                                ArrayList<Double> tempTopRightUps = new ArrayList<>();
                                ArrayList<Double> tempBottomLeftUps = new ArrayList<>();
                                ArrayList<Double> tempBottomRightUps = new ArrayList<>();
                                ArrayList<Double> tempTopLeftDns = new ArrayList<>();
                                ArrayList<Double> tempTopRightDns = new ArrayList<>();
                                ArrayList<Double> tempBottomLeftDns = new ArrayList<>();
                                ArrayList<Double> tempBottomRightDns = new ArrayList<>();

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    while(cellIterator.hasNext()){
                                        Cell cell = cellIterator.next();
                                        if (row.getRowNum() == 0) {

                                        } else {
                                            if (cell.getColumnIndex() == 0) {
                                                tempLines.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 1) {
                                                tempStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 2) {
                                                tempSides.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 3) {
                                                tempCrossingIds.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 4) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    tempInterceptsUp.add(cell.getNumericCellValue());
                                                } else {
                                                    tempInterceptsUp.add(-1.0);
                                                }
                                            } else if (cell.getColumnIndex() == 5) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    tempInterceptsDn.add(cell.getNumericCellValue());
                                                } else {
                                                    tempInterceptsDn.add(-1.0);
                                                }
                                            } else if (cell.getColumnIndex() == 6) {
                                                tempSwitchesConnected.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 7) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    tempTopLeftUps.add(cell.getNumericCellValue());
                                                } else {
                                                    tempTopLeftUps.add(-1.0);
                                                }
                                            } else if (cell.getColumnIndex() == 8) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    tempTopRightUps.add(cell.getNumericCellValue());
                                                } else {
                                                    tempTopRightUps.add(-1.0);
                                                }
                                            } else if (cell.getColumnIndex() == 9) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    tempBottomLeftUps.add(cell.getNumericCellValue());
                                                } else {
                                                    tempBottomLeftUps.add(-1.0);
                                                }
                                            } else if (cell.getColumnIndex() == 10) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    tempBottomRightUps.add(cell.getNumericCellValue());
                                                } else {
                                                    tempBottomRightUps.add(-1.0);
                                                }
                                            } else if (cell.getColumnIndex() == 11) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    tempTopLeftDns.add(cell.getNumericCellValue());
                                                } else {
                                                    tempTopLeftDns.add(-1.0);
                                                }
                                            } else if (cell.getColumnIndex() == 12) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    tempTopRightDns.add(cell.getNumericCellValue());
                                                } else {
                                                    tempTopRightDns.add(-1.0);
                                                }
                                            } else if (cell.getColumnIndex() == 13) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    tempBottomLeftDns.add(cell.getNumericCellValue());
                                                } else {
                                                    tempBottomLeftDns.add(-1.0);
                                                }
                                            } else if (cell.getColumnIndex() == 14) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    tempBottomRightDns.add(cell.getNumericCellValue());
                                                } else {
                                                    tempBottomRightDns.add(-1.0);
                                                }
                                            }
                                        }
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.crossingLines = new String[TWL];
                                TWLPanel.crossingStations = new String[TWL];
                                TWLPanel.crossingSides = new String[TWL];
                                TWLPanel.crossingIds = new String[TWL];
                                TWLPanel.crossingInterceptsUp = new double[TWL];
                                TWLPanel.crossingInterceptsDn = new double[TWL];
                                TWLPanel.crossingSwitchesConnected = new String[TWL];
                                TWLPanel.crossingTopLeftUp = new double[TWL];
                                TWLPanel.crossingTopRightUp = new double[TWL];
                                TWLPanel.crossingBottomLeftUp = new double[TWL];
                                TWLPanel.crossingBottomRightUp = new double[TWL];
                                TWLPanel.crossingTopLeftDn = new double[TWL];
                                TWLPanel.crossingTopRightDn = new double[TWL];
                                TWLPanel.crossingBottomLeftDn = new double[TWL];
                                TWLPanel.crossingBottomRightDn = new double[TWL];

                                KTLPanel.crossingLines = new String[KTL];
                                KTLPanel.crossingStations = new String[KTL];
                                KTLPanel.crossingSides = new String[KTL];
                                KTLPanel.crossingIds = new String[KTL];
                                KTLPanel.crossingInterceptsUp = new double[KTL];
                                KTLPanel.crossingInterceptsDn = new double[KTL];
                                KTLPanel.crossingSwitchesConnected = new String[KTL];
                                KTLPanel.crossingTopLeftUp = new double[KTL];
                                KTLPanel.crossingTopRightUp = new double[KTL];
                                KTLPanel.crossingBottomLeftUp = new double[KTL];
                                KTLPanel.crossingBottomRightUp = new double[KTL];
                                KTLPanel.crossingTopLeftDn = new double[KTL];
                                KTLPanel.crossingTopRightDn = new double[KTL];
                                KTLPanel.crossingBottomLeftDn = new double[KTL];
                                KTLPanel.crossingBottomRightDn = new double[KTL];

                                TKLPanel.crossingLines = new String[TKL];
                                TKLPanel.crossingStations = new String[TKL];
                                TKLPanel.crossingSides = new String[TKL];
                                TKLPanel.crossingIds = new String[TKL];
                                TKLPanel.crossingInterceptsUp = new double[TKL];
                                TKLPanel.crossingInterceptsDn = new double[TKL];
                                TKLPanel.crossingSwitchesConnected = new String[TKL];
                                TKLPanel.crossingTopLeftUp = new double[TKL];
                                TKLPanel.crossingTopRightUp = new double[TKL];
                                TKLPanel.crossingBottomLeftUp = new double[TKL];
                                TKLPanel.crossingBottomRightUp = new double[TKL];
                                TKLPanel.crossingTopLeftDn = new double[TKL];
                                TKLPanel.crossingTopRightDn = new double[TKL];
                                TKLPanel.crossingBottomLeftDn = new double[TKL];
                                TKLPanel.crossingBottomRightDn = new double[TKL];

                                ISLPanel.crossingLines = new String[ISL];
                                ISLPanel.crossingStations = new String[ISL];
                                ISLPanel.crossingSides = new String[ISL];
                                ISLPanel.crossingIds = new String[ISL];
                                ISLPanel.crossingInterceptsUp = new double[ISL];
                                ISLPanel.crossingInterceptsDn = new double[ISL];
                                ISLPanel.crossingSwitchesConnected = new String[ISL];
                                ISLPanel.crossingTopLeftUp = new double[ISL];
                                ISLPanel.crossingTopRightUp = new double[ISL];
                                ISLPanel.crossingBottomLeftUp = new double[ISL];
                                ISLPanel.crossingBottomRightUp = new double[ISL];
                                ISLPanel.crossingTopLeftDn = new double[ISL];
                                ISLPanel.crossingTopRightDn = new double[ISL];
                                ISLPanel.crossingBottomLeftDn = new double[ISL];
                                ISLPanel.crossingBottomRightDn = new double[ISL];



                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.crossingLines[TWL] = tempLines.get(i);
                                        TWLPanel.crossingStations[TWL] = tempStations.get(i);
                                        TWLPanel.crossingSides[TWL] = tempSides.get(i);
                                        TWLPanel.crossingIds[TWL] = tempCrossingIds.get(i);
                                        TWLPanel.crossingInterceptsUp[TWL] = tempInterceptsUp.get(i);
                                        TWLPanel.crossingInterceptsDn[TWL] = tempInterceptsDn.get(i);
                                        TWLPanel.crossingSwitchesConnected[TWL] = tempSwitchesConnected.get(i);
                                        TWLPanel.crossingTopLeftUp[TWL] = tempTopLeftUps.get(i);
                                        TWLPanel.crossingTopRightUp[TWL] = tempTopRightUps.get(i);
                                        TWLPanel.crossingBottomLeftUp[TWL] = tempBottomLeftUps.get(i);
                                        TWLPanel.crossingBottomRightUp[TWL] = tempBottomRightUps.get(i);
                                        TWLPanel.crossingTopLeftDn[TWL] = tempTopLeftDns.get(i);
                                        TWLPanel.crossingTopRightDn[TWL] = tempTopRightDns.get(i);
                                        TWLPanel.crossingBottomLeftDn[TWL] = tempBottomLeftDns.get(i);
                                        TWLPanel.crossingBottomRightDn[TWL] = tempBottomRightDns.get(i);
                                        TWL++;

                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTLPanel.crossingLines[KTL] = tempLines.get(i);
                                        KTLPanel.crossingStations[KTL] = tempStations.get(i);
                                        KTLPanel.crossingSides[KTL] = tempSides.get(i);
                                        KTLPanel.crossingIds[KTL] = tempCrossingIds.get(i);
                                        KTLPanel.crossingInterceptsUp[KTL] = tempInterceptsUp.get(i);
                                        KTLPanel.crossingInterceptsDn[KTL] = tempInterceptsDn.get(i);
                                        KTLPanel.crossingSwitchesConnected[KTL] = tempSwitchesConnected.get(i);
                                        KTLPanel.crossingTopLeftUp[KTL] = tempTopLeftUps.get(i);
                                        KTLPanel.crossingTopRightUp[KTL] = tempTopRightUps.get(i);
                                        KTLPanel.crossingBottomLeftUp[KTL] = tempBottomLeftUps.get(i);
                                        KTLPanel.crossingBottomRightUp[KTL] = tempBottomRightUps.get(i);
                                        KTLPanel.crossingTopLeftDn[KTL] = tempTopLeftDns.get(i);
                                        KTLPanel.crossingTopRightDn[KTL] = tempTopRightDns.get(i);
                                        KTLPanel.crossingBottomLeftDn[KTL] = tempBottomLeftDns.get(i);
                                        KTLPanel.crossingBottomRightDn[KTL] = tempBottomRightDns.get(i);
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKLPanel.crossingLines[TKL] = tempLines.get(i);
                                        TKLPanel.crossingStations[TKL] = tempStations.get(i);
                                        TKLPanel.crossingSides[TKL] = tempSides.get(i);
                                        TKLPanel.crossingIds[TKL] = tempCrossingIds.get(i);
                                        TKLPanel.crossingInterceptsUp[TKL] = tempInterceptsUp.get(i);
                                        TKLPanel.crossingInterceptsDn[TKL] = tempInterceptsDn.get(i);
                                        TKLPanel.crossingSwitchesConnected[TKL] = tempSwitchesConnected.get(i);
                                        TKLPanel.crossingTopLeftUp[TKL] = tempTopLeftUps.get(i);
                                        TKLPanel.crossingTopRightUp[TKL] = tempTopRightUps.get(i);
                                        TKLPanel.crossingBottomLeftUp[TKL] = tempBottomLeftUps.get(i);
                                        TKLPanel.crossingBottomRightUp[TKL] = tempBottomRightUps.get(i);
                                        TKLPanel.crossingTopLeftDn[TKL] = tempTopLeftDns.get(i);
                                        TKLPanel.crossingTopRightDn[TKL] = tempTopRightDns.get(i);
                                        TKLPanel.crossingBottomLeftDn[TKL] = tempBottomLeftDns.get(i);
                                        TKLPanel.crossingBottomRightDn[TKL] = tempBottomRightDns.get(i);
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISLPanel.crossingLines[ISL] = tempLines.get(i);
                                        ISLPanel.crossingStations[ISL] = tempStations.get(i);
                                        ISLPanel.crossingSides[ISL] = tempSides.get(i);
                                        ISLPanel.crossingIds[ISL] = tempCrossingIds.get(i);
                                        ISLPanel.crossingInterceptsUp[ISL] = tempInterceptsUp.get(i);
                                        ISLPanel.crossingInterceptsDn[ISL] = tempInterceptsDn.get(i);
                                        ISLPanel.crossingSwitchesConnected[ISL] = tempSwitchesConnected.get(i);
                                        ISLPanel.crossingTopLeftUp[ISL] = tempTopLeftUps.get(i);
                                        ISLPanel.crossingTopRightUp[ISL] = tempTopRightUps.get(i);
                                        ISLPanel.crossingBottomLeftUp[ISL] = tempBottomLeftUps.get(i);
                                        ISLPanel.crossingBottomRightUp[ISL] = tempBottomRightUps.get(i);
                                        ISLPanel.crossingTopLeftDn[ISL] = tempTopLeftDns.get(i);
                                        ISLPanel.crossingTopRightDn[ISL] = tempTopRightDns.get(i);
                                        ISLPanel.crossingBottomLeftDn[ISL] = tempBottomLeftDns.get(i);
                                        ISLPanel.crossingBottomRightDn[ISL] = tempBottomRightDns.get(i);
                                        ISL++;
                                    }
                                }

                                TWLPanel.crossingInterceptForSearchX = new double[TWL];
                                TWLPanel.crossingInterceptForSearchY = new double[TWL];
                                TWLPanel.crossingSwitchCoordinates = new double[TWL][];

                                for (int i = 0; i < TWLPanel.crossingLines.length; i++) {
                                    String[] tempSwitchesNames = TWLPanel.crossingSwitchesConnected[i].split(",");
                                    double[] switches = new double[tempSwitchesNames.length];

                                    for (int j = 0; j < tempSwitchesNames.length; j++) {
                                        for (int k = 0; k < TWLPanel.switchLines.length; k++) {
                                            if (TWLPanel.switchLines[k].equals(TWLPanel.crossingLines[i]) && TWLPanel.switchStations[k].equals(TWLPanel.crossingStations[i]) && TWLPanel.switchIds[k].equals(tempSwitchesNames[j])) {
                                                switches[j] = TWLPanel.switchLocations[k];
                                                break;
                                            }
                                        }
                                        if (switches[j] == 0.0) {
                                            switch (j) {
                                                case 0:
                                                    switches[j] = switches[2];
                                                    break;
                                                case 1:
                                                    switches[j] = switches[3];
                                                    break;
                                                case 2:
                                                    switches[j] = switches[0];
                                                    break;
                                                case 3:
                                                    switches[j] = switches[1];
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                    }
                                    TWLPanel.crossingSwitchCoordinates[i] = switches;

                                    double interceptX = (switches[0] * switches[2] - switches[1] * switches[3]) / (switches[0] + switches[2] - switches[1] - switches[3]);
                                    TWLPanel.crossingInterceptForSearchX[i] = interceptX;

                                    double interceptY;
                                    switch (TWLPanel.crossingSides[i]) {
                                        case "UP":
                                            interceptY = ((TWLPanel.upTrack - 5) * (switches[2] - interceptX) - (TWLPanel.upTrack - TWLPanel.upDnDistance + 5) * (switches[1] - interceptX)) / (switches[2] - switches[1]);
                                            TWLPanel.crossingInterceptForSearchY[i] = interceptY;
                                            break;
                                        case "DN":
                                            interceptY = ((TWLPanel.dnTrack + TWLPanel.upDnDistance - 5) * (switches[0] - interceptX) - (TWLPanel.dnTrack + 5) * (switches[3] - interceptX)) / (switches[0] - switches[3]);
                                            TWLPanel.crossingInterceptForSearchY[i] = interceptY;
                                            break;
                                        case "UPDN":
                                            interceptY = ((TWLPanel.dnTrack - 5) * (switches[0] - interceptX) - (TWLPanel.upTrack + 5) * (switches[3] - interceptX)) / (switches[0] - switches[3]);
                                            TWLPanel.crossingInterceptForSearchY[i] = interceptY;
                                            break;
                                        default:
                                            break;
                                    }
                                }





                                KTLPanel.crossingInterceptForSearchX = new double[KTL];
                                KTLPanel.crossingInterceptForSearchY = new double[KTL];
                                KTLPanel.crossingSwitchCoordinates = new double[KTL][];

                                for (int i = 0; i < KTLPanel.crossingLines.length; i++) {
                                    String[] tempSwitchesNames = KTLPanel.crossingSwitchesConnected[i].split(",");
                                    double[] switches = new double[tempSwitchesNames.length];

                                    for (int j = 0; j < tempSwitchesNames.length; j++) {
                                        for (int k = 0; k < KTLPanel.switchLines.length; k++) {
                                            if (KTLPanel.switchLines[k].equals(KTLPanel.crossingLines[i]) && KTLPanel.switchStations[k].equals(KTLPanel.crossingStations[i]) && KTLPanel.switchIds[k].equals(tempSwitchesNames[j])) {
                                                switches[j] = KTLPanel.switchLocations[k];
                                                break;
                                            }
                                        }
                                        if (switches[j] == 0.0) {
                                            switch (j) {
                                                case 0:
                                                    switches[j] = switches[2];
                                                    break;
                                                case 1:
                                                    switches[j] = switches[3];
                                                    break;
                                                case 2:
                                                    switches[j] = switches[0];
                                                    break;
                                                case 3:
                                                    switches[j] = switches[1];
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                    }
                                    KTLPanel.crossingSwitchCoordinates[i] = switches;

                                    double interceptX = (switches[0] * switches[2] - switches[1] * switches[3]) / (switches[0] + switches[2] - switches[1] - switches[3]);
                                    KTLPanel.crossingInterceptForSearchX[i] = interceptX;

                                    double interceptY;
                                    switch (KTLPanel.crossingSides[i]) {
                                        case "UP":
                                            interceptY = ((KTLPanel.upTrack - 5) * (switches[2] - interceptX) - (KTLPanel.upTrack - KTLPanel.upDnDistance + 5) * (switches[1] - interceptX)) / (switches[2] - switches[1]);
                                            KTLPanel.crossingInterceptForSearchY[i] = interceptY;
                                            break;
                                        case "DN":
                                            interceptY = ((KTLPanel.dnTrack + KTLPanel.upDnDistance - 5) * (switches[0] - interceptX) - (KTLPanel.dnTrack + 5) * (switches[3] - interceptX)) / (switches[0] - switches[3]);
                                            KTLPanel.crossingInterceptForSearchY[i] = interceptY;
                                            break;
                                        case "UPDN":
                                            interceptY = ((KTLPanel.dnTrack - 5) * (switches[0] - interceptX) - (KTLPanel.upTrack + 5) * (switches[3] - interceptX)) / (switches[0] - switches[3]);
                                            KTLPanel.crossingInterceptForSearchY[i] = interceptY;
                                            break;
                                        default:
                                            break;
                                    }
                                }



                                TKLPanel.crossingInterceptForSearchX = new double[TKL];
                                TKLPanel.crossingInterceptForSearchY = new double[TKL];
                                TKLPanel.crossingSwitchCoordinates = new double[TKL][];

                                for (int i = 0; i < TKLPanel.crossingLines.length; i++) {
                                    String[] tempSwitchesNames = TKLPanel.crossingSwitchesConnected[i].split(",");
                                    double[] switches = new double[tempSwitchesNames.length];

                                    for (int j = 0; j < tempSwitchesNames.length; j++) {
                                        for (int k = 0; k < TKLPanel.switchLines.length; k++) {
                                            if (TKLPanel.switchLines[k].equals(TKLPanel.crossingLines[i]) && TKLPanel.switchStations[k].equals(TKLPanel.crossingStations[i]) && TKLPanel.switchIds[k].equals(tempSwitchesNames[j])) {
                                                switches[j] = TKLPanel.switchLocations[k];
                                                break;
                                            }
                                        }
                                        if (switches[j] == 0.0) {
                                            switch (j) {
                                                case 0:
                                                    switches[j] = switches[2];
                                                    break;
                                                case 1:
                                                    switches[j] = switches[3];
                                                    break;
                                                case 2:
                                                    switches[j] = switches[0];
                                                    break;
                                                case 3:
                                                    switches[j] = switches[1];
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                    }
                                    TKLPanel.crossingSwitchCoordinates[i] = switches;

                                    double interceptX = (switches[0] * switches[2] - switches[1] * switches[3]) / (switches[0] + switches[2] - switches[1] - switches[3]);
                                    TKLPanel.crossingInterceptForSearchX[i] = interceptX;

                                    double interceptY;
                                    switch (TKLPanel.crossingSides[i]) {
                                        case "UP":
                                            interceptY = ((TKLPanel.upTrack - 5) * (switches[2] - interceptX) - (TKLPanel.upTrack - TKLPanel.upDnDistance + 5) * (switches[1] - interceptX)) / (switches[2] - switches[1]);
                                            TKLPanel.crossingInterceptForSearchY[i] = interceptY;
                                            break;
                                        case "DN":
                                            interceptY = ((TKLPanel.dnTrack + TKLPanel.upDnDistance - 5) * (switches[0] - interceptX) - (TKLPanel.dnTrack + 5) * (switches[3] - interceptX)) / (switches[0] - switches[3]);
                                            TKLPanel.crossingInterceptForSearchY[i] = interceptY;
                                            break;
                                        case "UPDN":
                                            interceptY = ((TKLPanel.dnTrack - 5) * (switches[0] - interceptX) - (TKLPanel.upTrack + 5) * (switches[3] - interceptX)) / (switches[0] - switches[3]);
                                            TKLPanel.crossingInterceptForSearchY[i] = interceptY;
                                            break;
                                        default:
                                            break;
                                    }
                                }


                                ISLPanel.crossingInterceptForSearchX = new double[ISL];
                                ISLPanel.crossingInterceptForSearchY = new double[ISL];
                                ISLPanel.crossingSwitchCoordinates = new double[ISL][];

                                for (int i = 0; i < ISLPanel.crossingLines.length; i++) {
                                    String[] tempSwitchesNames = ISLPanel.crossingSwitchesConnected[i].split(",");
                                    double[] switches = new double[tempSwitchesNames.length];

                                    for (int j = 0; j < tempSwitchesNames.length; j++) {
                                        for (int k = 0; k < ISLPanel.switchLines.length; k++) {
                                            if (ISLPanel.switchLines[k].equals(ISLPanel.crossingLines[i]) && ISLPanel.switchStations[k].equals(ISLPanel.crossingStations[i]) && ISLPanel.switchIds[k].equals(tempSwitchesNames[j])) {
                                                switches[j] = ISLPanel.switchLocations[k];
                                                break;
                                            }
                                        }
                                        if (switches[j] == 0.0) {
                                            switch (j) {
                                                case 0:
                                                    switches[j] = switches[2];
                                                    break;
                                                case 1:
                                                    switches[j] = switches[3];
                                                    break;
                                                case 2:
                                                    switches[j] = switches[0];
                                                    break;
                                                case 3:
                                                    switches[j] = switches[1];
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                    }
                                    ISLPanel.crossingSwitchCoordinates[i] = switches;

                                    double interceptX = (switches[0] * switches[2] - switches[1] * switches[3]) / (switches[0] + switches[2] - switches[1] - switches[3]);
                                    ISLPanel.crossingInterceptForSearchX[i] = interceptX;

                                    double interceptY;
                                    switch (ISLPanel.crossingSides[i]) {
                                        case "UP":
                                            interceptY = ((ISLPanel.upTrack - 5) * (switches[2] - interceptX) - (ISLPanel.upTrack - ISLPanel.upDnDistance + 5) * (switches[1] - interceptX)) / (switches[2] - switches[1]);
                                            ISLPanel.crossingInterceptForSearchY[i] = interceptY;
                                            break;
                                        case "DN":
                                            interceptY = ((ISLPanel.dnTrack + ISLPanel.upDnDistance - 5) * (switches[0] - interceptX) - (ISLPanel.dnTrack + 5) * (switches[3] - interceptX)) / (switches[0] - switches[3]);
                                            ISLPanel.crossingInterceptForSearchY[i] = interceptY;
                                            break;
                                        case "UPDN":
                                            interceptY = ((ISLPanel.dnTrack - 5) * (switches[0] - interceptX) - (ISLPanel.upTrack + 5) * (switches[3] - interceptX)) / (switches[0] - switches[3]);
                                            ISLPanel.crossingInterceptForSearchY[i] = interceptY;
                                            break;
                                        default:
                                            break;
                                    }
                                }







                                break;
                            case "Switch Track":

                                tempLines = new ArrayList<>();
                                ArrayList<String> tempFromStations = new ArrayList<>();
                                ArrayList<String> tempToStations = new ArrayList<>();
                                ArrayList<String> tempFromSwitches = new ArrayList<>();
                                ArrayList<String> tempToSwitches = new ArrayList<>();
                                ArrayList<Double> tempBoundariesUp = new ArrayList<>();
                                ArrayList<Double> tempBoundariesDn = new ArrayList<>();

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    while(cellIterator.hasNext()){
                                        Cell cell = cellIterator.next();
                                        if (row.getRowNum() == 0) {

                                        } else {
                                            if (cell.getColumnIndex() == 0) {
                                                tempLines.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 1) {
                                                tempFromStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 2) {
                                                tempToStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 3) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    String tempString = Integer.toString((int)cell.getNumericCellValue());
                                                    tempFromSwitches.add(tempString);
                                                } else {
                                                    tempFromSwitches.add(cell.getStringCellValue());
                                                }
                                            } else if (cell.getColumnIndex() == 4) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    String tempString = Integer.toString((int)cell.getNumericCellValue());
                                                    tempToSwitches.add(tempString);
                                                } else {
                                                    tempToSwitches.add(cell.getStringCellValue());
                                                }
                                            } else if (cell.getColumnIndex() == 5) {
                                                tempBoundariesUp.add(cell.getNumericCellValue());
                                            } else if (cell.getColumnIndex() == 6) {
                                                tempBoundariesDn.add(cell.getNumericCellValue());
                                            }
                                        }
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.switchTrackLines = new String[TWL];
                                TWLPanel.switchTrackFromStations = new String[TWL];
                                TWLPanel.switchTrackToStations = new String[TWL];
                                TWLPanel.switchTrackFromSwitches = new String[TWL];
                                TWLPanel.switchTrackToSwitches = new String[TWL];
                                TWLPanel.switchTrackBoundaryUp = new double[TWL];
                                TWLPanel.switchTrackBoundaryDn = new double[TWL];

                                KTLPanel.switchTrackLines = new String[KTL];
                                KTLPanel.switchTrackFromStations = new String[KTL];
                                KTLPanel.switchTrackToStations = new String[KTL];
                                KTLPanel.switchTrackFromSwitches = new String[KTL];
                                KTLPanel.switchTrackToSwitches = new String[KTL];
                                KTLPanel.switchTrackBoundaryUp = new double[KTL];
                                KTLPanel.switchTrackBoundaryDn = new double[KTL];

                                TKLPanel.switchTrackLines = new String[TKL];
                                TKLPanel.switchTrackFromStations = new String[TKL];
                                TKLPanel.switchTrackToStations = new String[TKL];
                                TKLPanel.switchTrackFromSwitches = new String[TKL];
                                TKLPanel.switchTrackToSwitches = new String[TKL];
                                TKLPanel.switchTrackBoundaryUp = new double[TKL];
                                TKLPanel.switchTrackBoundaryDn = new double[TKL];

                                ISLPanel.switchTrackLines = new String[ISL];
                                ISLPanel.switchTrackFromStations = new String[ISL];
                                ISLPanel.switchTrackToStations = new String[ISL];
                                ISLPanel.switchTrackFromSwitches = new String[ISL];
                                ISLPanel.switchTrackToSwitches = new String[ISL];
                                ISLPanel.switchTrackBoundaryUp = new double[ISL];
                                ISLPanel.switchTrackBoundaryDn = new double[ISL];


                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.switchTrackLines[TWL] = tempLines.get(i);
                                        TWLPanel.switchTrackFromStations[TWL] = tempFromStations.get(i);
                                        TWLPanel.switchTrackToStations[TWL] = tempToStations.get(i);
                                        TWLPanel.switchTrackFromSwitches[TWL] = tempFromSwitches.get(i);
                                        TWLPanel.switchTrackToSwitches[TWL] = tempToSwitches.get(i);
                                        TWLPanel.switchTrackBoundaryUp[TWL] = tempBoundariesUp.get(i);
                                        TWLPanel.switchTrackBoundaryDn[TWL] = tempBoundariesDn.get(i);
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTLPanel.switchTrackLines[KTL] = tempLines.get(i);
                                        KTLPanel.switchTrackFromStations[KTL] = tempFromStations.get(i);
                                        KTLPanel.switchTrackToStations[KTL] = tempToStations.get(i);
                                        KTLPanel.switchTrackFromSwitches[KTL] = tempFromSwitches.get(i);
                                        KTLPanel.switchTrackToSwitches[KTL] = tempToSwitches.get(i);
                                        KTLPanel.switchTrackBoundaryUp[KTL] = tempBoundariesUp.get(i);
                                        KTLPanel.switchTrackBoundaryDn[KTL] = tempBoundariesDn.get(i);
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKLPanel.switchTrackLines[TKL] = tempLines.get(i);
                                        TKLPanel.switchTrackFromStations[TKL] = tempFromStations.get(i);
                                        TKLPanel.switchTrackToStations[TKL] = tempToStations.get(i);
                                        TKLPanel.switchTrackFromSwitches[TKL] = tempFromSwitches.get(i);
                                        TKLPanel.switchTrackToSwitches[TKL] = tempToSwitches.get(i);
                                        TKLPanel.switchTrackBoundaryUp[TKL] = tempBoundariesUp.get(i);
                                        TKLPanel.switchTrackBoundaryDn[TKL] = tempBoundariesDn.get(i);
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISLPanel.switchTrackLines[ISL] = tempLines.get(i);
                                        ISLPanel.switchTrackFromStations[ISL] = tempFromStations.get(i);
                                        ISLPanel.switchTrackToStations[ISL] = tempToStations.get(i);
                                        ISLPanel.switchTrackFromSwitches[ISL] = tempFromSwitches.get(i);
                                        ISLPanel.switchTrackToSwitches[ISL] = tempToSwitches.get(i);
                                        ISLPanel.switchTrackBoundaryUp[ISL] = tempBoundariesUp.get(i);
                                        ISLPanel.switchTrackBoundaryDn[ISL] = tempBoundariesDn.get(i);
                                        ISL++;
                                    }
                                }

                                break;
                            case "Floodgate":

                                tempLines = new ArrayList<>();
                                tempStations = new ArrayList<>();
                                tempSides = new ArrayList<>();
                                ArrayList<Double> tempLocationsUp = new ArrayList<>();
                                ArrayList<Double> tempLocationsDn = new ArrayList<>();
                                tempNames = new ArrayList<>();
                                ArrayList<String> tempNouthSouths = new ArrayList<>();

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    while(cellIterator.hasNext()){
                                        Cell cell = cellIterator.next();
                                        if (row.getRowNum() == 0) {

                                        } else {
                                            if (cell.getColumnIndex() == 0) {
                                                tempLines.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 1) {
                                                tempStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 2) {
                                                tempSides.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 3) {
                                                tempLocationsUp.add(cell.getNumericCellValue());
                                            } else if (cell.getColumnIndex() == 4) {
                                                tempLocationsDn.add(cell.getNumericCellValue());
                                            } else if (cell.getColumnIndex() == 5) {
                                                tempNames.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 6) {
                                                tempNouthSouths.add(cell.getStringCellValue());
                                            }
                                        }
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.floodgateLines = new String[TWL];
                                TWLPanel.floodgateStations = new String[TWL];
                                TWLPanel.floodgateSides = new String[TWL];
                                TWLPanel.floodgateLocationsUp = new double[TWL];
                                TWLPanel.floodgateLocationsDn = new double[TWL];
                                TWLPanel.floodgateNames = new String[TWL];
                                TWLPanel.floodgateNorthSouth = new String[TWL];

                                KTLPanel.floodgateLines = new String[KTL];
                                KTLPanel.floodgateStations = new String[KTL];
                                KTLPanel.floodgateSides = new String[KTL];
                                KTLPanel.floodgateLocationsUp = new double[KTL];
                                KTLPanel.floodgateLocationsDn = new double[KTL];
                                KTLPanel.floodgateNames = new String[KTL];
                                KTLPanel.floodgateNorthSouth = new String[KTL];

                                TKLPanel.floodgateLines = new String[TKL];
                                TKLPanel.floodgateStations = new String[TKL];
                                TKLPanel.floodgateSides = new String[TKL];
                                TKLPanel.floodgateLocationsUp = new double[TKL];
                                TKLPanel.floodgateLocationsDn = new double[TKL];
                                TKLPanel.floodgateNames = new String[TKL];
                                TKLPanel.floodgateNorthSouth = new String[TKL];

                                ISLPanel.floodgateLines = new String[ISL];
                                ISLPanel.floodgateStations = new String[ISL];
                                ISLPanel.floodgateSides = new String[ISL];
                                ISLPanel.floodgateLocationsUp = new double[ISL];
                                ISLPanel.floodgateLocationsDn = new double[ISL];
                                ISLPanel.floodgateNames = new String[ISL];
                                ISLPanel.floodgateNorthSouth = new String[ISL];



                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.floodgateLines[TWL] = tempLines.get(i);
                                        TWLPanel.floodgateStations[TWL] = tempStations.get(i);
                                        TWLPanel.floodgateSides[TWL] = tempSides.get(i);
                                        TWLPanel.floodgateLocationsUp[TWL] = tempLocationsUp.get(i);
                                        TWLPanel.floodgateLocationsDn[TWL] = tempLocationsDn.get(i);
                                        TWLPanel.floodgateNames[TWL] = tempNames.get(i);
                                        TWLPanel.floodgateNorthSouth[TWL] = tempNouthSouths.get(i);
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTLPanel.floodgateLines[KTL] = tempLines.get(i);
                                        KTLPanel.floodgateStations[KTL] = tempStations.get(i);
                                        KTLPanel.floodgateSides[KTL] = tempSides.get(i);
                                        KTLPanel.floodgateLocationsUp[KTL] = tempLocationsUp.get(i);
                                        KTLPanel.floodgateLocationsDn[KTL] = tempLocationsDn.get(i);
                                        KTLPanel.floodgateNames[KTL] = tempNames.get(i);
                                        KTLPanel.floodgateNorthSouth[KTL] = tempNouthSouths.get(i);
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKLPanel.floodgateLines[TKL] = tempLines.get(i);
                                        TKLPanel.floodgateStations[TKL] = tempStations.get(i);
                                        TKLPanel.floodgateSides[TKL] = tempSides.get(i);
                                        TKLPanel.floodgateLocationsUp[TKL] = tempLocationsUp.get(i);
                                        TKLPanel.floodgateLocationsDn[TKL] = tempLocationsDn.get(i);
                                        TKLPanel.floodgateNames[TKL] = tempNames.get(i);
                                        TKLPanel.floodgateNorthSouth[TKL] = tempNouthSouths.get(i);
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISLPanel.floodgateLines[ISL] = tempLines.get(i);
                                        ISLPanel.floodgateStations[ISL] = tempStations.get(i);
                                        ISLPanel.floodgateSides[ISL] = tempSides.get(i);
                                        ISLPanel.floodgateLocationsUp[ISL] = tempLocationsUp.get(i);
                                        ISLPanel.floodgateLocationsDn[ISL] = tempLocationsDn.get(i);
                                        ISLPanel.floodgateNames[ISL] = tempNames.get(i);
                                        ISLPanel.floodgateNorthSouth[ISL] = tempNouthSouths.get(i);
                                        ISL++;
                                    }
                                }

                                break;
                            case "Impedance Bond":

                                tempLines = new ArrayList<>();
                                tempStations = new ArrayList<>();
                                tempSides = new ArrayList<>();
                                ArrayList<String> tempBondIds = new ArrayList<>();
                                tempLocations = new ArrayList<>();
                                ArrayList<Integer> tempQuantities = new ArrayList<>();

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    while(cellIterator.hasNext()){
                                        Cell cell = cellIterator.next();
                                        if (row.getRowNum() == 0) {

                                        } else {
                                            if (cell.getColumnIndex() == 0) {
                                                tempLines.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 1) {
                                                tempStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 2) {
                                                tempSides.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 3) {
                                                tempBondIds.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 4) {
                                                tempLocations.add(cell.getNumericCellValue());
                                            } else if (cell.getColumnIndex() == 5) {
                                                tempQuantities.add((int)(cell.getNumericCellValue()));
                                            }
                                        }
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.impedanceBondLines = new String[TWL];
                                TWLPanel.impedanceBondStations = new String[TWL];
                                TWLPanel.impedanceBondSides = new String[TWL];
                                TWLPanel.impedanceBondIds = new String[TWL];
                                TWLPanel.impedanceBondLocations = new double[TWL];
                                TWLPanel.impedanceBondQuantities = new int[TWL];

                                KTLPanel.impedanceBondLines = new String[KTL];
                                KTLPanel.impedanceBondStations = new String[KTL];
                                KTLPanel.impedanceBondSides = new String[KTL];
                                KTLPanel.impedanceBondIds = new String[KTL];
                                KTLPanel.impedanceBondLocations = new double[KTL];
                                KTLPanel.impedanceBondQuantities = new int[KTL];

                                TKLPanel.impedanceBondLines = new String[TKL];
                                TKLPanel.impedanceBondStations = new String[TKL];
                                TKLPanel.impedanceBondSides = new String[TKL];
                                TKLPanel.impedanceBondIds = new String[TKL];
                                TKLPanel.impedanceBondLocations = new double[TKL];
                                TKLPanel.impedanceBondQuantities = new int[TKL];

                                ISLPanel.impedanceBondLines = new String[ISL];
                                ISLPanel.impedanceBondStations = new String[ISL];
                                ISLPanel.impedanceBondSides = new String[ISL];
                                ISLPanel.impedanceBondIds = new String[ISL];
                                ISLPanel.impedanceBondLocations = new double[ISL];
                                ISLPanel.impedanceBondQuantities = new int[ISL];


                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.impedanceBondLines[TWL] = tempLines.get(i);
                                        TWLPanel.impedanceBondStations[TWL] = tempStations.get(i);
                                        TWLPanel.impedanceBondSides[TWL] = tempSides.get(i);
                                        TWLPanel.impedanceBondIds[TWL] = tempBondIds.get(i);
                                        TWLPanel.impedanceBondLocations[TWL] = tempLocations.get(i);
                                        TWLPanel.impedanceBondQuantities[TWL] = tempQuantities.get(i);
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTLPanel.impedanceBondLines[KTL] = tempLines.get(i);
                                        KTLPanel.impedanceBondStations[KTL] = tempStations.get(i);
                                        KTLPanel.impedanceBondSides[KTL] = tempSides.get(i);
                                        KTLPanel.impedanceBondIds[KTL] = tempBondIds.get(i);
                                        KTLPanel.impedanceBondLocations[KTL] = tempLocations.get(i);
                                        KTLPanel.impedanceBondQuantities[KTL] = tempQuantities.get(i);
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKLPanel.impedanceBondLines[TKL] = tempLines.get(i);
                                        TKLPanel.impedanceBondStations[TKL] = tempStations.get(i);
                                        TKLPanel.impedanceBondSides[TKL] = tempSides.get(i);
                                        TKLPanel.impedanceBondIds[TKL] = tempBondIds.get(i);
                                        TKLPanel.impedanceBondLocations[TKL] = tempLocations.get(i);
                                        TKLPanel.impedanceBondQuantities[TKL] = tempQuantities.get(i);
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISLPanel.impedanceBondLines[ISL] = tempLines.get(i);
                                        ISLPanel.impedanceBondStations[ISL] = tempStations.get(i);
                                        ISLPanel.impedanceBondSides[ISL] = tempSides.get(i);
                                        ISLPanel.impedanceBondIds[ISL] = tempBondIds.get(i);
                                        ISLPanel.impedanceBondLocations[ISL] = tempLocations.get(i);
                                        ISLPanel.impedanceBondQuantities[ISL] = tempQuantities.get(i);
                                        ISL++;
                                    }
                                }


                                break;
                            case "Beacon":

                                tempLines = new ArrayList<>();
                                tempStations = new ArrayList<>();
                                tempSides = new ArrayList<>();
                                ArrayList<String> tempBeaconIds = new ArrayList<>();
                                tempTypes = new ArrayList<>();
                                tempLocations = new ArrayList<>();
                                ArrayList<String> tempLabels = new ArrayList<>();
                                ArrayList<String> tempReverses = new ArrayList<>();

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    while(cellIterator.hasNext()){
                                        Cell cell = cellIterator.next();
                                        if (row.getRowNum() == 0) {

                                        } else {
                                            if (cell.getColumnIndex() == 0) {
                                                tempLines.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 1) {
                                                tempStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 2) {
                                                tempSides.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 3) {
                                                tempBeaconIds.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 4) {
                                                tempTypes.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 5) {
                                                tempLocations.add(cell.getNumericCellValue());
                                            } else if (cell.getColumnIndex() == 6) {
                                                if (cell.getCellType() == CellType.NUMERIC) {
                                                    String tempString = Integer.toString((int)cell.getNumericCellValue());
                                                    tempLabels.add(tempString);
                                                } else {
                                                    tempLabels.add(cell.getStringCellValue());
                                                }
                                            } else if (cell.getColumnIndex() == 7) {
                                                tempReverses.add(cell.getStringCellValue());
                                            }
                                        }
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.beaconLines = new String[TWL];
                                TWLPanel.beaconStations = new String[TWL];
                                TWLPanel.beaconSides = new String[TWL];
                                TWLPanel.beaconIds = new String[TWL];
                                TWLPanel.beaconTypes = new String[TWL];
                                TWLPanel.beaconLocations = new double[TWL];
                                TWLPanel.beaconLabels = new String[TWL];
                                TWLPanel.beaconReverses = new String[TWL];

                                KTLPanel.beaconLines = new String[KTL];
                                KTLPanel.beaconStations = new String[KTL];
                                KTLPanel.beaconSides = new String[KTL];
                                KTLPanel.beaconIds = new String[KTL];
                                KTLPanel.beaconTypes = new String[KTL];
                                KTLPanel.beaconLocations = new double[KTL];
                                KTLPanel.beaconLabels = new String[KTL];
                                KTLPanel.beaconReverses = new String[KTL];

                                TKLPanel.beaconLines = new String[TKL];
                                TKLPanel.beaconStations = new String[TKL];
                                TKLPanel.beaconSides = new String[TKL];
                                TKLPanel.beaconIds = new String[TKL];
                                TKLPanel.beaconTypes = new String[TKL];
                                TKLPanel.beaconLocations = new double[TKL];
                                TKLPanel.beaconLabels = new String[TKL];
                                TKLPanel.beaconReverses = new String[TKL];

                                ISLPanel.beaconLines = new String[ISL];
                                ISLPanel.beaconStations = new String[ISL];
                                ISLPanel.beaconSides = new String[ISL];
                                ISLPanel.beaconIds = new String[ISL];
                                ISLPanel.beaconTypes = new String[ISL];
                                ISLPanel.beaconLocations = new double[ISL];
                                ISLPanel.beaconLabels = new String[ISL];
                                ISLPanel.beaconReverses = new String[ISL];


                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.beaconLines[TWL] = tempLines.get(i);
                                        TWLPanel.beaconStations[TWL] = tempStations.get(i);
                                        TWLPanel.beaconSides[TWL] = tempSides.get(i);
                                        TWLPanel.beaconIds[TWL] = tempBeaconIds.get(i);
                                        TWLPanel.beaconTypes[TWL] = tempTypes.get(i);
                                        TWLPanel.beaconLocations[TWL] = tempLocations.get(i);
                                        TWLPanel.beaconLabels[TWL] = tempLabels.get(i);
                                        TWLPanel.beaconReverses[TWL] = tempReverses.get(i);
                                        TWL++;

                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTLPanel.beaconLines[KTL] = tempLines.get(i);
                                        KTLPanel.beaconStations[KTL] = tempStations.get(i);
                                        KTLPanel.beaconSides[KTL] = tempSides.get(i);
                                        KTLPanel.beaconIds[KTL] = tempBeaconIds.get(i);
                                        KTLPanel.beaconTypes[KTL] = tempTypes.get(i);
                                        KTLPanel.beaconLocations[KTL] = tempLocations.get(i);
                                        KTLPanel.beaconLabels[KTL] = tempLabels.get(i);
                                        KTLPanel.beaconReverses[KTL] = tempReverses.get(i);
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKLPanel.beaconLines[TKL] = tempLines.get(i);
                                        TKLPanel.beaconStations[TKL] = tempStations.get(i);
                                        TKLPanel.beaconSides[TKL] = tempSides.get(i);
                                        TKLPanel.beaconIds[TKL] = tempBeaconIds.get(i);
                                        TKLPanel.beaconTypes[TKL] = tempTypes.get(i);
                                        TKLPanel.beaconLocations[TKL] = tempLocations.get(i);
                                        TKLPanel.beaconLabels[TKL] = tempLabels.get(i);
                                        TKLPanel.beaconReverses[TKL] = tempReverses.get(i);
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISLPanel.beaconLines[ISL] = tempLines.get(i);
                                        ISLPanel.beaconStations[ISL] = tempStations.get(i);
                                        ISLPanel.beaconSides[ISL] = tempSides.get(i);
                                        ISLPanel.beaconIds[ISL] = tempBeaconIds.get(i);
                                        ISLPanel.beaconTypes[ISL] = tempTypes.get(i);
                                        ISLPanel.beaconLocations[ISL] = tempLocations.get(i);
                                        ISLPanel.beaconLabels[ISL] = tempLabels.get(i);
                                        ISLPanel.beaconReverses[ISL] = tempReverses.get(i);
                                        ISL++;
                                    }
                                }

                                break;
                            case "Tag":

                                tempLines = new ArrayList<>();
                                tempStations = new ArrayList<>();
                                tempSides = new ArrayList<>();
                                ArrayList<String> tempTagIds = new ArrayList<>();
                                tempLocations = new ArrayList<>();

                                int sideColumn = 2;
                                int tagIdColumn = 3;
                                int kpLocationColumn = 4;

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    if (row.getRowNum() == 0) {
                                        for (int col = 0; col <= row.getLastCellNum(); col++) {
                                            Cell headerCell = row.getCell(col);
                                            if (headerCell == null) {
                                                continue;
                                            }
                                            String headerText = headerCell.toString().trim().toLowerCase();
                                            if (headerText.contains("side")) {
                                                sideColumn = col;
                                            }
                                            if (headerText.equals("tag id") || headerText.contains("tag id")) {
                                                tagIdColumn = col;
                                            }
                                            if (headerText.contains("kp")) {
                                                kpLocationColumn = col;
                                            }
                                        }
                                    } else {
                                        Cell lineCell = row.getCell(0);
                                        Cell stationCell = row.getCell(1);
                                        Cell sideCell = row.getCell(sideColumn);
                                        Cell tagIdCell = row.getCell(tagIdColumn);
                                        Cell kpLocationCell = row.getCell(kpLocationColumn);

                                        if (lineCell == null || stationCell == null || sideCell == null || tagIdCell == null || kpLocationCell == null) {
                                            continue;
                                        }

                                        tempLines.add(lineCell.toString().trim());
                                        tempStations.add(stationCell.toString().trim());
                                        tempSides.add(sideCell.toString().trim());
                                        if (tagIdCell.getCellType() == CellType.NUMERIC) {
                                            double rawTagId = tagIdCell.getNumericCellValue();
                                            if (Math.floor(rawTagId) == rawTagId) {
                                                tempTagIds.add(String.valueOf((int) rawTagId));
                                            } else {
                                                tempTagIds.add(String.valueOf(rawTagId));
                                            }
                                        } else {
                                            tempTagIds.add(tagIdCell.toString().trim());
                                        }
                                        tempLocations.add(kpLocationCell.getNumericCellValue());
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.tagLines = new String[TWL];
                                TWLPanel.tagStations = new String[TWL];
                                TWLPanel.tagSides = new String[TWL];
                                TWLPanel.tagIds = new String[TWL];
                                TWLPanel.tagLocations = new double[TWL];

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.tagLines[TWL] = tempLines.get(i);
                                        TWLPanel.tagStations[TWL] = tempStations.get(i);
                                        TWLPanel.tagSides[TWL] = tempSides.get(i);
                                        TWLPanel.tagIds[TWL] = tempTagIds.get(i);
                                        TWLPanel.tagLocations[TWL] = tempLocations.get(i);
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                break;
                            case "AP":

                                tempLines = new ArrayList<>();
                                tempStations = new ArrayList<>();
                                tempSides = new ArrayList<>();
                                ArrayList<String> tempApIds = new ArrayList<>();
                                tempLocations = new ArrayList<>();
                                ArrayList<String> tempApLeftOrRights = new ArrayList<>();

                                int antennaIdColumn = 2;
                                int apSideColumn = 3;
                                int apLocationColumn = 4;
                                int apLeftRightColumn = 5;

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    if (row.getRowNum() == 0) {
                                        for (int col = 0; col <= row.getLastCellNum(); col++) {
                                            Cell headerCell = row.getCell(col);
                                            if (headerCell == null) {
                                                continue;
                                            }
                                            String headerText = headerCell.toString().trim().toLowerCase();
                                            if (headerText.contains("antenna")) {
                                                antennaIdColumn = col;
                                            }
                                            if (headerText.contains("side")) {
                                                apSideColumn = col;
                                            }
                                            if (headerText.contains("location")) {
                                                apLocationColumn = col;
                                            }
                                            if (headerText.contains("left") || headerText.contains("right")) {
                                                apLeftRightColumn = col;
                                            }
                                        }
                                    } else {
                                        Cell lineCell = row.getCell(0);
                                        Cell stationCell = row.getCell(1);
                                        Cell idCell = row.getCell(antennaIdColumn);
                                        Cell sideCell = row.getCell(apSideColumn);
                                        Cell locationCell = row.getCell(apLocationColumn);
                                        Cell leftRightCell = row.getCell(apLeftRightColumn);

                                        if (lineCell == null || stationCell == null || idCell == null || sideCell == null || locationCell == null || leftRightCell == null) {
                                            continue;
                                        }

                                        String apSide = sideCell.toString().trim().toUpperCase();
                                        if (!(apSide.equals("UP") || apSide.equals("DN"))) {
                                            continue;
                                        }

                                        double apLocation;
                                        try {
                                            if (locationCell.getCellType() == CellType.NUMERIC) {
                                                apLocation = locationCell.getNumericCellValue();
                                            } else {
                                                apLocation = Double.parseDouble(locationCell.toString().trim());
                                            }
                                        } catch (Exception parseException) {
                                            continue;
                                        }

                                        tempLines.add(lineCell.toString().trim());
                                        tempStations.add(stationCell.toString().trim());
                                        tempApIds.add(idCell.toString().trim());
                                        tempSides.add(apSide);
                                        tempLocations.add(apLocation);
                                        tempApLeftOrRights.add(leftRightCell.toString().trim().toUpperCase());
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.apLines = new String[TWL];
                                TWLPanel.apStations = new String[TWL];
                                TWLPanel.apSides = new String[TWL];
                                TWLPanel.apIds = new String[TWL];
                                TWLPanel.apLocations = new double[TWL];
                                TWLPanel.apLeftOrRights = new String[TWL];

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.apLines[TWL] = tempLines.get(i);
                                        TWLPanel.apStations[TWL] = tempStations.get(i);
                                        TWLPanel.apSides[TWL] = tempSides.get(i);
                                        TWLPanel.apIds[TWL] = tempApIds.get(i);
                                        TWLPanel.apLocations[TWL] = tempLocations.get(i);
                                        TWLPanel.apLeftOrRights[TWL] = tempApLeftOrRights.get(i);
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                break;
                            case "SAB":

                                tempLines = new ArrayList<>();
                                tempStations = new ArrayList<>();
                                tempSides = new ArrayList<>();
                                ArrayList<String> tempIds = new ArrayList<>();
                                tempLocations = new ArrayList<>();

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    while(cellIterator.hasNext()){
                                        Cell cell = cellIterator.next();
                                        if (row.getRowNum() == 0) {

                                        } else {
                                            if (cell.getColumnIndex() == 0) {
                                                tempLines.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 1) {
                                                tempStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 2) {
                                                tempSides.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 3) {
                                                tempIds.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 4) {
                                                tempLocations.add(cell.getNumericCellValue());
                                            }
                                        }
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.sabLines = new String[TWL];
                                TWLPanel.sabStations = new String[TWL];
                                TWLPanel.sabSides = new String[TWL];
                                TWLPanel.sabIds = new String[TWL];
                                TWLPanel.sabLocations = new double[TWL];

                                KTLPanel.sabLines = new String[KTL];
                                KTLPanel.sabStations = new String[KTL];
                                KTLPanel.sabSides = new String[KTL];
                                KTLPanel.sabIds = new String[KTL];
                                KTLPanel.sabLocations = new double[KTL];

                                TKLPanel.sabLines = new String[TKL];
                                TKLPanel.sabStations = new String[TKL];
                                TKLPanel.sabSides = new String[TKL];
                                TKLPanel.sabIds = new String[TKL];
                                TKLPanel.sabLocations = new double[TKL];

                                ISLPanel.sabLines = new String[ISL];
                                ISLPanel.sabStations = new String[ISL];
                                ISLPanel.sabSides = new String[ISL];
                                ISLPanel.sabIds = new String[ISL];
                                ISLPanel.sabLocations = new double[ISL];


                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.sabLines[TWL] = tempLines.get(i);
                                        TWLPanel.sabStations[TWL] = tempStations.get(i);
                                        TWLPanel.sabSides[TWL] = tempSides.get(i);
                                        TWLPanel.sabIds[TWL] = tempIds.get(i);
                                        TWLPanel.sabLocations[TWL] = tempLocations.get(i);
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTLPanel.sabLines[KTL] = tempLines.get(i);
                                        KTLPanel.sabStations[KTL] = tempStations.get(i);
                                        KTLPanel.sabSides[KTL] = tempSides.get(i);
                                        KTLPanel.sabIds[KTL] = tempIds.get(i);
                                        KTLPanel.sabLocations[KTL] = tempLocations.get(i);
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKLPanel.sabLines[TKL] = tempLines.get(i);
                                        TKLPanel.sabStations[TKL] = tempStations.get(i);
                                        TKLPanel.sabSides[TKL] = tempSides.get(i);
                                        TKLPanel.sabIds[TKL] = tempIds.get(i);
                                        TKLPanel.sabLocations[TKL] = tempLocations.get(i);
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISLPanel.sabLines[ISL] = tempLines.get(i);
                                        ISLPanel.sabStations[ISL] = tempStations.get(i);
                                        ISLPanel.sabSides[ISL] = tempSides.get(i);
                                        ISLPanel.sabIds[ISL] = tempIds.get(i);
                                        ISLPanel.sabLocations[ISL] = tempLocations.get(i);
                                        ISL++;
                                    }
                                }


                                break;
                            case "Box":

                                tempLines = new ArrayList<>();
                                tempStations = new ArrayList<>();
                                tempSides = new ArrayList<>();
                                tempIds = new ArrayList<>();
                                tempLocations = new ArrayList<>();
                                tempTypes = new ArrayList<>();

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    while(cellIterator.hasNext()){
                                        Cell cell = cellIterator.next();
                                        if (row.getRowNum() == 0) {

                                        } else {
                                            if (cell.getColumnIndex() == 0) {
                                                tempLines.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 1) {
                                                tempStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 2) {
                                                tempSides.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 3) {
                                                tempIds.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 4) {
                                                tempLocations.add(cell.getNumericCellValue());
                                            } else if (cell.getColumnIndex() == 5) {
                                                tempTypes.add(cell.getStringCellValue());
                                            }
                                        }
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.boxLines = new String[TWL];
                                TWLPanel.boxStations = new String[TWL];
                                TWLPanel.boxSides = new String[TWL];
                                TWLPanel.boxIds = new String[TWL];
                                TWLPanel.boxLocations = new double[TWL];
                                TWLPanel.boxTypes = new String[TWL];

                                KTLPanel.boxLines = new String[KTL];
                                KTLPanel.boxStations = new String[KTL];
                                KTLPanel.boxSides = new String[KTL];
                                KTLPanel.boxIds = new String[KTL];
                                KTLPanel.boxLocations = new double[KTL];
                                KTLPanel.boxTypes = new String[KTL];

                                TKLPanel.boxLines = new String[TKL];
                                TKLPanel.boxStations = new String[TKL];
                                TKLPanel.boxSides = new String[TKL];
                                TKLPanel.boxIds = new String[TKL];
                                TKLPanel.boxLocations = new double[TKL];
                                TKLPanel.boxTypes = new String[TKL];

                                ISLPanel.boxLines = new String[ISL];
                                ISLPanel.boxStations = new String[ISL];
                                ISLPanel.boxSides = new String[ISL];
                                ISLPanel.boxIds = new String[ISL];
                                ISLPanel.boxLocations = new double[ISL];
                                ISLPanel.boxTypes = new String[ISL];


                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.boxLines[TWL] = tempLines.get(i);
                                        TWLPanel.boxStations[TWL] = tempStations.get(i);
                                        TWLPanel.boxSides[TWL] = tempSides.get(i);
                                        TWLPanel.boxIds[TWL] = tempIds.get(i);
                                        TWLPanel.boxLocations[TWL] = tempLocations.get(i);
                                        TWLPanel.boxTypes[TWL] = tempTypes.get(i);
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTLPanel.boxLines[KTL] = tempLines.get(i);
                                        KTLPanel.boxStations[KTL] = tempStations.get(i);
                                        KTLPanel.boxSides[KTL] = tempSides.get(i);
                                        KTLPanel.boxIds[KTL] = tempIds.get(i);
                                        KTLPanel.boxLocations[KTL] = tempLocations.get(i);
                                        KTLPanel.boxTypes[KTL] = tempTypes.get(i);
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKLPanel.boxLines[TKL] = tempLines.get(i);
                                        TKLPanel.boxStations[TKL] = tempStations.get(i);
                                        TKLPanel.boxSides[TKL] = tempSides.get(i);
                                        TKLPanel.boxIds[TKL] = tempIds.get(i);
                                        TKLPanel.boxLocations[TKL] = tempLocations.get(i);
                                        TKLPanel.boxTypes[TKL] = tempTypes.get(i);
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISLPanel.boxLines[ISL] = tempLines.get(i);
                                        ISLPanel.boxStations[ISL] = tempStations.get(i);
                                        ISLPanel.boxSides[ISL] = tempSides.get(i);
                                        ISLPanel.boxIds[ISL] = tempIds.get(i);
                                        ISLPanel.boxLocations[ISL] = tempLocations.get(i);
                                        ISLPanel.boxTypes[ISL] = tempTypes.get(i);
                                        ISL++;
                                    }
                                }


                                break;
                            case "Additional":

                                tempLines = new ArrayList<>();
                                tempStations = new ArrayList<>();
                                tempSides = new ArrayList<>();
                                tempLocations = new ArrayList<>();
                                tempNames = new ArrayList<>();

                                while(rowIterator.hasNext()){
                                    Row row = rowIterator.next();
                                    Iterator<Cell> cellIterator = row.cellIterator();
                                    while(cellIterator.hasNext()){
                                        Cell cell = cellIterator.next();
                                        if (row.getRowNum() == 0) {

                                        } else {
                                            if (cell.getColumnIndex() == 0) {
                                                tempLines.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 1) {
                                                tempStations.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 2) {
                                                tempSides.add(cell.getStringCellValue());
                                            } else if (cell.getColumnIndex() == 3) {
                                                tempLocations.add(cell.getNumericCellValue());
                                            } else if (cell.getColumnIndex() == 4) {
                                                tempNames.add(cell.getStringCellValue());
                                            }
                                        }
                                    }
                                }

                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWL++;
                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISL++;
                                    }
                                }

                                TWLPanel.additionalLines = new String[TWL];
                                TWLPanel.additionalStations = new String[TWL];
                                TWLPanel.additionalSides = new String[TWL];
                                TWLPanel.additionalLocations = new double[TWL];
                                TWLPanel.additionalNames = new String[TWL];

                                KTLPanel.additionalLines = new String[KTL];
                                KTLPanel.additionalStations = new String[KTL];
                                KTLPanel.additionalSides = new String[KTL];
                                KTLPanel.additionalLocations = new double[KTL];
                                KTLPanel.additionalNames = new String[KTL];

                                TKLPanel.additionalLines = new String[TKL];
                                TKLPanel.additionalStations = new String[TKL];
                                TKLPanel.additionalSides = new String[TKL];
                                TKLPanel.additionalLocations = new double[TKL];
                                TKLPanel.additionalNames = new String[TKL];

                                ISLPanel.additionalLines = new String[ISL];
                                ISLPanel.additionalStations = new String[ISL];
                                ISLPanel.additionalSides = new String[ISL];
                                ISLPanel.additionalLocations = new double[ISL];
                                ISLPanel.additionalNames = new String[ISL];


                                TWL = 0; KTL = 0; TKL = 0; ISL = 0;
                                for (int i = 0; i < tempLines.size(); i++) {
                                    if (tempLines.get(i).equals("TWL")) {
                                        TWLPanel.additionalLines[TWL] = tempLines.get(i);
                                        TWLPanel.additionalStations[TWL] = tempStations.get(i);
                                        TWLPanel.additionalSides[TWL] = tempSides.get(i);
                                        TWLPanel.additionalLocations[TWL] = tempLocations.get(i);
                                        TWLPanel.additionalNames[TWL] = tempNames.get(i);
                                        TWL++;

                                    }
                                    if (tempLines.get(i).equals("KTL")) {
                                        KTLPanel.additionalLines[KTL] = tempLines.get(i);
                                        KTLPanel.additionalStations[KTL] = tempStations.get(i);
                                        KTLPanel.additionalSides[KTL] = tempSides.get(i);
                                        KTLPanel.additionalLocations[KTL] = tempLocations.get(i);
                                        KTLPanel.additionalNames[KTL] = tempNames.get(i);
                                        KTL++;
                                    }
                                    if (tempLines.get(i).equals("TKL")) {
                                        TKLPanel.additionalLines[TKL] = tempLines.get(i);
                                        TKLPanel.additionalStations[TKL] = tempStations.get(i);
                                        TKLPanel.additionalSides[TKL] = tempSides.get(i);
                                        TKLPanel.additionalLocations[TKL] = tempLocations.get(i);
                                        TKLPanel.additionalNames[TKL] = tempNames.get(i);
                                        TKL++;
                                    }
                                    if (tempLines.get(i).equals("ISL")) {
                                        ISLPanel.additionalLines[ISL] = tempLines.get(i);
                                        ISLPanel.additionalStations[ISL] = tempStations.get(i);
                                        ISLPanel.additionalSides[ISL] = tempSides.get(i);
                                        ISLPanel.additionalLocations[ISL] = tempLocations.get(i);
                                        ISLPanel.additionalNames[ISL] = tempNames.get(i);
                                        ISL++;
                                    }
                                }

                                break;
                            default:
                                break;
                        }
                    }


                    in.close();

                    LinePanel.TWLButton.setEnabled(true);
                    LinePanel.KTLButton.setEnabled(true);
                    LinePanel.TKLButton.setEnabled(true);
                    LinePanel.ISLButton.setEnabled(true);

                    ControlPanel.signalButton.setEnabled(true);
                    ControlPanel.apButton.setEnabled(true);
                    ControlPanel.impedanceBondButton.setEnabled(true);
                    ControlPanel.beaconButton.setEnabled(true);
                    ControlPanel.tagButton.setEnabled(true);
                    ControlPanel.sabButton.setEnabled(true);
                    ControlPanel.boxButton.setEnabled(true);
                    ControlPanel.otherButton.setEnabled(true);

                    ControlPanel.crossingDataButton.setEnabled(true);
                    ControlPanel.switchDataButton.setEnabled(true);
                    ControlPanel.floodgateDataButton.setEnabled(true);
                    importedFile.close();
                    
                } catch (LinkageError ex) {
                    JOptionPane.showMessageDialog(null, "Failed to load Excel library dependency: " + ex.getClass().getSimpleName() + "\n" + ex.getMessage() + "\nPlease ensure Apache POI and XMLBeans versions match in your Eclipse classpath.", "Excel Library Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        }
    }
}
