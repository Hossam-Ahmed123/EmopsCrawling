package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExtractData {

    static String domain = "http://emops.twse.com.tw";
    private static final String path2 = "http://source.z2data.com/";
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ExtractData.class.getName());

    public static String getChildUrls(String companyCode, String urlParnt, String taxonomy, String fileOutPut, String companyid, Connection con) throws IOException {
        log.info("Start Extract....");
        int random = (int) (Math.random() * 12754);

        ArrayList<ModelX> mops = new ArrayList<>();
        String dType = null;
        String newTitle = null;
        try {
            Document doc = Jsoup.connect(urlParnt).timeout(1000 * 60).get();
            Element table = doc.select("table").get(1);
            Elements trs = table.select("tr");
            Element table2 = doc.select("table").get(0);
            Elements trss = table2.select("tr");
            Elements myin = doc.getElementsByClass("in-w-10");
            String companyName = "";
            String Titel = "";
            for (Element trs1 : trss) {
                Titel = trs1.text();

            }
            Titel = Titel.replaceAll(" ", "");
            newTitle = Titel.replaceAll("─", "-");
            for (Element e : myin) {
                companyName = e.text();

            }
            companyName = companyName.replaceAll("Provided by: ", "");
            String pageName = "";
            String childUrl = "";
            String date1 = "";
            String pageName0 = "";
            for (Element element1 : trs) {

                Elements tds = element1.select("td");

                Elements link = tds.select("a");

                for (Element element : link) {
                    childUrl = element.attr("href");
                    log.info("Child URL..." + childUrl);
                    pageName = tds.first().text();
                    date1 = tds.last().text();
                    pageName0 = pageName.replaceAll("/", "-");

                    companyName = companyName.replaceAll("Provided by:", "");

                    String dsfDate = date1.replaceAll("/", "-");

                    pageName = pageName.replaceAll("\t", "");
                    String datee = null;
                    String datee2 = null;
                    if (pageName.lastIndexOf('/') == -1) {
                        log.error("Error");
                    } else {
                        datee = pageName.substring(0, pageName.lastIndexOf('/'));

                    }

//                    dataModel.setdSSend("0");
                    if (pageName.lastIndexOf('/') == -1) {
                        log.error("Error");
                    } else {
                        datee2 = pageName.substring(pageName.lastIndexOf('/') + 2);

                    }
                    if (datee2.equals("4")) {
                        dType = "Annual";
                    } else {
                        dType = "Quarter";
                    }

                    if (datee2 != null) {
                        String path1 = Downloader.savePage(domain + childUrl, taxonomy, companyName, pageName0);
                        System.out.println(path1);
                        String offlinex = path1.replaceAll("D:/", "");
                        pageName0 = pageName0.replaceAll(" ", "");
                        String datePath = createPathWithFullDate();
                        String offline = path2 + offlinex ;
//                        dataModel.setPath1Offline();
//                        dataModel.setPah1(path1);

//  String projectID, String dSName, String dSUrl, String dSFiling_Date, String dSFilings, String dSFilingsType, String dSType, String pah1, String dSFocusYear, String dSParent_URL, String dSSend, String dSQuarter, String companyName, String cIK, String path1Offline) {
                        mops.add(new ModelX(companyid, taxonomy + "  -  " + pageName0, domain + childUrl, getDate(dsfDate), "TWSE", "TWSE", dType, path1, datee, urlParnt, "0", "Q" + datee2, "", "", offline));
//                        writeToFile(dataModel.toString(), fileOutPut);

                    }

                }

            }
            saveAllData(mops, con);
        } catch (Exception e) {
            log.error("Error" + e);
            return "error";

        }
        return "done";
    }

    public static String createPathWithFullDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int mSecond = calendar.get(Calendar.MILLISECOND);

        String fullDateTime = year + "/" + month + "/" + day + "/" + hour + "/" + minute + "/" + second + "/" + mSecond;

        return fullDateTime;
    }

    private static void saveAllData(ArrayList<ModelX> sec, Connection con) {

        PreparedStatement st = null;
        try {
            log.info(" insert into Data Sheet Table Running.....");

            String sql
                    = "IF NOT EXISTS(SELECT top 1 [DS_ID] FROM [src].[DataSheetsForMops] WHERE [Companyid] = ? and [DS_URL]= ? )"
                    + "insert into [src].[DataSheetsForMops] ([Companyid],DS_Name,DS_URL,DS_Interactive_URL,DS_Filing_Date,DS_PeriodDate,[DS_Quarterold],DS_Filings,"
                    + "DS_Filings_Type,ds_type,DS_FocusYear,DS_Parent_URL,Date_Insert,[DS_Send],[path1_offline],[Path1]) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            st = con.prepareStatement(sql);
            int x = 0;
            for (ModelX dataModel : sec) {
                st.setInt(1, Integer.valueOf(dataModel.getProjectID()));
                st.setString(2, dataModel.getdSUrl());
                st.setInt(3, Integer.valueOf(dataModel.getProjectID()));
                st.setString(4, dataModel.getdSName());
                st.setString(5, dataModel.getdSUrl());
                st.setString(6, "N/A");
                st.setString(7, dataModel.getdSFiling_Date());
                st.setString(8, "1900-01-01");
                st.setString(9, dataModel.getdSQuarter());
                st.setString(10, "Fillings " + dataModel.getdSFilings());
                st.setString(11, dataModel.getdSFilingsType());
                st.setString(12, getDsType("Fillings " + dataModel.getdSFilings()));
                st.setString(13, dataModel.getdSFocusYear());
                log.info("FocusYear -->" + dataModel.getdSFocusYear());

                st.setString(14, dataModel.getdSParent_URL());
                st.setString(15, currentDateTime());

                st.setInt(16, 0);
                st.setString(17, dataModel.getPath1Offline());
                st.setString(18, dataModel.getPah1());
//                st.setInt(15, 0);
                st.addBatch();
//                sec.remove(x);
//                x++;
            }
            if (st != null) {
//                int[] arr = st.executeBatch();
                int[] arr = st.executeBatch();
                st.executeUpdate();
                st.clearBatch();
                log.info("Number Of inserted Rows:" + arr.length);
                con.commit();

            }

            log.info("Done insert into Data Sheets");

        } catch (SQLException ex) {
            log.error("Error", ex);
        }

    }

    private static String getDsType(String filling) {

        String dsType = "";
        if (filling != null) {
            switch (filling) {

                case "Fillings 10-Q":
                    dsType = "Quarter";
                    break;
                case "Fillings 10-K":
                case "Fillings DEF 14A":
                case "Fillings ex-13":
                case "Fillings 20-F":
                case "Fillings 40-F":
                    dsType = "Annual";
                    break;
                case "Fillings 8-K":
                    dsType = "Current report";
                    break;

            }
        }

        return dsType;
    }

    public static String currentDateTime() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();

        return dateFormat.format(date);
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
            out1.print(x);

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

    private static String getDate(String oldDate) {
        String formatDate = "";
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // use SimpleDateFormat to define how to PARSE the INPUT
            Date date = sdf.parse(oldDate);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatDate = formatter.format(date);

        } catch (ParseException ex) {
            log.error("", ex);
        }
        return formatDate;
    }
}
