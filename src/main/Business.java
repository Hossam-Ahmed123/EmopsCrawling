package main;

import Connect.DBUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Model.DataModel;
import Model.ExtractData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class Business {
    
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Business.class.getName());
    
    private static Connection con;
    private static ArrayList<String> balanceSheet;
    private static ArrayList<String> statementsOfCashFlows;
    private static ArrayList<String> statementOfComprehensiveIncome;
    
    static {
        if (con == null) {
            try {
                con = DBUtil.connectDataBase();
            } catch (IOException ex) {
                log.error("Error", ex);
            }
        }
        
    }

//    public static void main(String[] args) {
//        BufferedReader br = null;
//        String fileName = args[0];
//        String fileOutPut = args[1];
//        String fileOutPutError = args[2];
//        try {
//
//            String sCurrentLine;
//            br = new BufferedReader(new FileReader(fileName));
//
//            int i = 0;
//            DataModel dataModel = new DataModel();
//            ExtractData.writeToFile(dataModel.toString(), fileOutPut);
//            log.info("Done Write Header");
//            log.info("Read From File Running...");
//            while ((sCurrentLine = br.readLine()) != null) {
//                ExtractData.getChildUrls(sCurrentLine, "http://emops.twse.com.tw/server-java/t164sb03_e?step=historical&caption_id=000001&co_id=" + sCurrentLine + "&ifrs=&TYPEK=otc&caption_id=000001&YEAR=", "Balance Sheet", fileOutPut, fileOutPutError);
//                ExtractData.getChildUrls(sCurrentLine, "http://emops.twse.com.tw/server-java/t164sb05_e?step=historical&caption_id=000001&co_id=" + sCurrentLine + "&ifrs=&TYPEK=otc&caption_id=000001&YEAR=", "Statements of Cash Flows", fileOutPut, fileOutPutError);
//                ExtractData.getChildUrls(sCurrentLine, "http://emops.twse.com.tw/server-java/t164sb04_e?step=historical&caption_id=000001&co_id=" + sCurrentLine + "&ifrs=&TYPEK=otc&caption_id=000001&YEAR=", "Statement of Comprehensive Income", fileOutPut, fileOutPutError);
//            }
//
//        } catch (IOException e) {
//            log.error("Error" + e);
//        } finally {
//            try {
//                if (br != null) {
//                    br.close();
//                }
//            } catch (IOException ex) {
//                log.error("Error" + ex);
//            }
//        }
//    }
    public static void main(String[] args) {
        String fileOutPut = null;// args[0];
        DataModel dataModel = new DataModel();
        ArrayList<String> balanceSheet = new ArrayList();
        ArrayList<String> statementsOfCashFlows = new ArrayList();
        ArrayList<String> statementOfComprehensiveIncome = new ArrayList();
//        ExtractData.writeToFile(dataModel.toString(), fileOutPut);
        while (true) {
            try {
                
                // Get Company Code && Company ID For BalanceSheet
                for (String companycode : getCodeOfBalanceSheet()) {
                    
                    String companyCodeNew = companycode.substring(0, companycode.indexOf("Balance"));
                    String companyid = companycode.substring(companycode.indexOf("Balance") + 7, companycode.length());
                    System.out.println(companyid);
                    try {
                        String value = ExtractData.getChildUrls(companyCodeNew, "http://emops.twse.com.tw/server-java/t164sb03_e?step=historical&caption_id=000001&co_id=" + companyCodeNew + "&ifrs=&TYPEK=otc&caption_id=000001&YEAR=", "Balance Sheet", fileOutPut, companyid, con);
                        if (value.equals("done")) {
                            balanceSheet.add(companyCodeNew);
//                        updateMopsTable("[Balance_Sheet] = 1 where companycode='" + companycode + "'");
                        }
                        
                    } catch (IOException ex) {
                        log.error("error", ex);
                    }
                    
                }
                
                updateMopsTable(balanceSheet, "[Balance_Sheet] = 1 where companycode=?");
                
                
                
                // Get Company Code && Company ID For Statements Of CashFlows
                for (String companycode : getCodeOfStatementsOfCashFlows()) {
                    String companyCodeNew = companycode.substring(0, companycode.indexOf("Balance"));
                    String companyid = companycode.substring(companycode.indexOf("Balance") + 7, companycode.length());
                    System.out.println(companyid);
                    try {
                        String value = ExtractData.getChildUrls(companyCodeNew, "http://emops.twse.com.tw/server-java/t164sb05_e?step=historical&caption_id=000001&co_id=" + companyCodeNew + "&ifrs=&TYPEK=otc&caption_id=000001&YEAR=", "Statements of Cash Flows", fileOutPut, companyid, con);
                        if (value.equals("done")) {
                            statementsOfCashFlows.add(companyCodeNew);
                        }
                    } catch (IOException ex) {
                        log.error("error", ex);
                    }
                }
                updateMopsTable(statementsOfCashFlows, "[Statements_of_Cash_Flows] = 1 where companycode=?");
               // Get Company Code && Company ID For Statement Of Comprehensive Income
                for (String companycode : getCodeOfStatementOfComprehensiveIncome()) {
                    String companyCodeNew = companycode.substring(0, companycode.indexOf("Balance"));
                    String companyid = companycode.substring(companycode.indexOf("Balance") + 7, companycode.length());
                    System.out.println(companyid);
                    try {
                        String value = ExtractData.getChildUrls(companyCodeNew, "http://emops.twse.com.tw/server-java/t164sb04_e?step=historical&caption_id=000001&co_id=" + companyCodeNew + "&ifrs=&TYPEK=otc&caption_id=000001&YEAR=", "Statement of Comprehensive Income", fileOutPut, companyid, con);
                        if (value.equals("done")) {
                            statementOfComprehensiveIncome.add(companyCodeNew);
                            
                        }
                    } catch (IOException ex) {
                        log.error("error", ex);
                    }
                }
                updateMopsTable(statementOfComprehensiveIncome, "[Statement_of_Comprehensive_Income] = 1 where companycode=?");
//        }
                Thread.sleep(3600000 * 168);
            } catch (InterruptedException ex) {
                log.info("Error",ex);
            }
        }
        
    }
    
    public static ArrayList<String> getCodeOfBalanceSheet() {
        log.info("get Company Code Of BalanceSheet runing");
        balanceSheet = new ArrayList();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT TOP 1000 [companycode],[companyId] FROM [Sourcing].[src].[Emops] where [Balance_Sheet]=0 ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String resulet = rs.getString(1) + "Balance" + rs.getString(2);
                balanceSheet.add(resulet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Business.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return balanceSheet;
    }
    
    public static ArrayList<String> getCodeOfStatementsOfCashFlows() {
        log.info("get Company Code Of Statements Of CashFlows runing");
        statementsOfCashFlows = new ArrayList();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT TOP 1000[companycode] ,[companyId] FROM [Sourcing].[src].[Emops] where [Statements_of_Cash_Flows]=0";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String resulet = rs.getString(1) + "Balance" + rs.getString(2);
                statementsOfCashFlows.add(resulet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Business.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return statementsOfCashFlows;
    }
    
    public static ArrayList<String> getCodeOfStatementOfComprehensiveIncome() {
        log.info("get Company Code Of Statement Of Comprehensive Income runing");
        statementOfComprehensiveIncome = new ArrayList();
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT TOP 1000 [companycode],[companyId] FROM [Sourcing].[src].[Emops] where [Statement_of_Comprehensive_Income]=0 ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String resulet = rs.getString(1) + "Balance" + rs.getString(2);
                statementOfComprehensiveIncome.add(resulet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Business.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return statementOfComprehensiveIncome;
    }
    
    private static void updateMopsTable(ArrayList<String> state, String statement) {
        
        PreparedStatement ps = null;
        try {
            log.info(" update mops Table Running.....");
            
            String sql = "   update  [Sourcing].[src].[Emops] set " + statement;
            
            ps = con.prepareStatement(sql);
            for (String code : state) {
                ps.setString(1, code);
                ps.addBatch();
            }
            if (ps != null) {
                int[] arr = ps.executeBatch();
                ps.executeBatch();
                ps.clearBatch();
                log.info("Number Of updated Rows:" + arr.length);
                con.commit();
            }
            
        } catch (SQLException ex) {
            log.error("Error", ex);
        }
        
    }
}
