package Model;

public class DataModel {

    private static String projectID = "project_ID";
    private static String dSName = " DS_Name";
    private static String dSUrl = "DS_Url";
    private static String dSFiling_Date = " DS_Filing_Date";
    private static String dSFilings = " DS_Filings";
    private static String dSFilingsType = " DS_Filings_Type";
    private static String dSType = " DS_type";
    private static String pah1 = "Path1";
    private static String dSFocusYear = " DS_FocusYear";
    private static String dSParent_URL = "DS_Parent_URL";
    private static String dSSend = "DS_Send";
    private static String dSQuarter = "DS_Quarter";
    private static String companyName = "Company Name";
    private static String cIK = "CIK";
    private static String path1Offline = "OffLine_Path";

    public static String getPath1Offline() {
        return path1Offline;
    }

    public static void setPath1Offline(String path1Offline) {
        DataModel.path1Offline = path1Offline;
    }

    public static String getdSUrl() {
        return dSUrl;
    }

    public static void setdSUrl(String dSUrl) {
        DataModel.dSUrl = dSUrl;
    }

    public static String getProjectID() {
        return projectID;
    }

    public static void setProjectID(String projectID) {
        DataModel.projectID = projectID;
    }

    public static String getdSName() {
        return dSName;
    }

    public static void setdSName(String dSName) {
        DataModel.dSName = dSName;
    }

    public static String getdSFiling_Date() {
        return dSFiling_Date;
    }

    public static void setdSFiling_Date(String dSFiling_Date) {
        DataModel.dSFiling_Date = dSFiling_Date;
    }

    public static String getdSFilings() {
        return dSFilings;
    }

    public static void setdSFilings(String dSFilings) {
        DataModel.dSFilings = dSFilings;
    }

    public static String getdSFilingsType() {
        return dSFilingsType;
    }

    public static void setdSFilingsType(String dSFilingsType) {
        DataModel.dSFilingsType = dSFilingsType;
    }

    public static String getdSType() {
        return dSType;
    }

    public static void setdSType(String dSType) {
        DataModel.dSType = dSType;
    }

    public static String getPah1() {
        return pah1;
    }

    public static void setPah1(String pah1) {
        DataModel.pah1 = pah1;
    }

    public static String getdSFocusYear() {
        return dSFocusYear;
    }

    public static void setdSFocusYear(String dSFocusYear) {
        DataModel.dSFocusYear = dSFocusYear;
    }

    public static String getdSParent_URL() {
        return dSParent_URL;
    }

    public static void setdSParent_URL(String dSParent_URL) {
        DataModel.dSParent_URL = dSParent_URL;
    }

    public static String getdSSend() {
        return dSSend;
    }

    public static void setdSSend(String dSSend) {
        DataModel.dSSend = dSSend;
    }

    public static String getdSQuarter() {
        return dSQuarter;
    }

    public static void setdSQuarter(String dSQuarter) {
        DataModel.dSQuarter = dSQuarter;
    }

    public static String getCompanyName() {
        return companyName;
    }

    public static void setCompanyName(String companyName) {
        DataModel.companyName = companyName;
    }

    public static String getcIK() {
        return cIK;
    }

    public static void setcIK(String cIK) {
        DataModel.cIK = cIK;
    }

    @Override
    public String toString() {
//     saveInDataSheet(String projectID, String dSName, String dSUrl, String dSFiling_Date, String dSFilings, String dSFilingsType, String dSType, String pah1, String dSFocusYear, String dSParent_URL, String dSSend, String dSQuarter, String companyName, String cIK, String path1Offline)
//                DataSheet.saveInDataSheet(projectID, dSName, dSUrl, dSFiling_Date, dSFilings, dSFilingsType, dSType, pah1, dSFocusYear, dSParent_URL, dSSend, dSQuarter, path1Offline);
        return projectID + "\t" + dSName + "\t" + dSUrl + "\t" + dSFiling_Date + "\t" + dSFilings + "\t" + dSFilingsType + "\t" + dSType + "\t" + pah1 + "\t" + dSFocusYear + "\t" + dSParent_URL + "\t" + dSSend + "\t" + path1Offline + "\t" + dSQuarter + "\t" + companyName + "\t" + cIK + "\r\n";

    }

}
