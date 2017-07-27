/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.z2data.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.Business;

/**
 *
 * @author hossam.ahmed
 */
public class GetCompanyId {

    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Business.class.getName());

    public static void main(String[] args) throws SQLException {
        BufferedReader br = null;

        try {

            String sCurrentLine;
            br = new BufferedReader(new FileReader("D:\\Emops\\in.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                String[] splited = sCurrentLine.split("\t");
                String emopsCompanyName = splited[0];
                String zCompanyName = splited[1];
                System.out.println(zCompanyName);

                String id = new GetCompanyId().getCompanyIdx(zCompanyName);
                writeToFile(toString(id, emopsCompanyName, zCompanyName), "D:\\Emops\\newout.txt");

            }

        } catch (IOException e) {
            log.error("Error" + e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                log.error("Error" + ex);
            }
        }
    }

    public String getCompanyIdx(String name) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String id = null;

        if (con == null) {

            try {
                con = Connect.DBUtil.connectDataBase();
            } catch (IOException ex) {
                log.error("Error", ex);
            }
        }
        String sql = "select top 1 [CompanyComID] from [Sourcing].[dbo].[core_company] where [Name] like'%" + name + "%'";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getString(1);
            System.out.println(id);
        }

        return id;

    }

    public static String toString(String id, String emopsName, String ZName) {
        return id + "\t" + emopsName + "\t" + ZName;
    }

    public static void writeToFile(String x, String fileName) {
        File file = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out1 = null;
        try {

            fw = new FileWriter(fileName, true);
            log.info("Done Write in File");
            bw = new BufferedWriter(fw);
            out1 = new PrintWriter(bw);
            out1.println(x);

            out1.close();
        } catch (IOException e) {
            log.error("Error" + e);
        } finally {
            if (out1 != null) {
                out1.close();
            }
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                log.error("Error" + e);
            }
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                log.error("Error" + e);
            }
        }

    }
}
