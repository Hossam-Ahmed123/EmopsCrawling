package Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

public class Downloader {

    private static final String path1 = "D:/SEC/mops/";
    private static final String path2 = "http://source.z2data.com/";
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Downloader.class.getName());

    public static String savePage(String url, String taxonomy, String CompanyName, String pageName) {
          String save = null;
        log.info("Start Download...");
        DataModel dataModel = new DataModel();
        File file = null;
        String taxonomy0 = null;
        try {

            taxonomy = taxonomy.replaceAll(" ", "");
            pageName = pageName.replaceAll(" ", "");
            String datePath = createPathWithFullDate();
            int random = (int) (Math.random() * 12754);
            file = new File(path1 + datePath + "/" + random + "/" + pageName + "/");
            file.mkdirs();

            URL url1 = new URL(url);
            URLConnection connection = url1.openConnection();
            connection.connect();
            InputStream in = connection.getInputStream();
            log.info("Taxonmy ------" + taxonomy);
//            if (taxonomy.endsWith("BalanceSheet")) {
//                taxonomy0 = taxonomy.replaceAll("FinancialStatement?BalanceSheet", "FinancialStatement─BalanceSheet");
//                
//                System.out.println("taxonomy0 "+ taxonomy0);
//            } else if (taxonomy.endsWith("StatementsofCashFlows")) {
//                taxonomy0 = taxonomy.replaceAll("FinancialStatement?StatementsofCashFlows","FinancialStatement─StatementsofCashFlows");
//
//            } else if (taxonomy.endsWith("IncomeStatement")) {
//                taxonomy0 = taxonomy.replaceAll("FinancialStatement?IncomeStatement","FinancialStatement─IncomeStatement");
//
//            }

           
             save = path1 + datePath + "/" + random + "/" + pageName + "/" + taxonomy +pageName+ ".html";
//            dataModel.setPah1(save);
            String offlinex = path1.replaceAll("W:/", "");
//            dataModel.setPath1Offline(path2 + offlinex + datePath + "/" + random + "/" + pageName + "/" + taxonomy + ".html");
            FileOutputStream fos = new FileOutputStream(new File(save));
            int length = -1;
            byte[] buffer = new byte[1024];
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            in.close();
            log.info("file is downloaded");
        } catch (Exception e) {

            log.error("Error" + e);
        }
      

        return save;

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

}
