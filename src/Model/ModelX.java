/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author hossam.ahmed
 */
public class ModelX {

    private  String projectID ;
    private  String dSName ;
    private  String dSUrl;
    private  String dSFiling_Date ;
    private  String dSFilings ;
    private  String dSFilingsType ;
    private  String dSType ;
    private  String pah1 ;
    private  String dSFocusYear ;
    private  String dSParent_URL ;
    private  String dSSend ;
    private  String dSQuarter ;
    private  String companyName;
    private  String cIK ;
    private  String path1Offline ;

    public ModelX(String projectID, String dSName, String dSUrl, String dSFiling_Date, String dSFilings, String dSFilingsType, String dSType, String pah1, String dSFocusYear, String dSParent_URL, String dSSend, String dSQuarter, String companyName, String cIK, String path1Offline) {
        this.projectID = projectID;
        this.dSName = dSName;
        this.dSUrl = dSUrl;
        this.dSFiling_Date = dSFiling_Date;
        this.dSFilings = dSFilings;
        this.dSFilingsType = dSFilingsType;
        this.dSType = dSType;
        this.pah1 = pah1;
        this.dSFocusYear = dSFocusYear;
        this.dSParent_URL = dSParent_URL;
        this.dSSend = dSSend;
        this.dSQuarter = dSQuarter;
        this.companyName = companyName;
        this.cIK = cIK;
        this.path1Offline = path1Offline;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getdSName() {
        return dSName;
    }

    public void setdSName(String dSName) {
        this.dSName = dSName;
    }

    public String getdSUrl() {
        return dSUrl;
    }

    public void setdSUrl(String dSUrl) {
        this.dSUrl = dSUrl;
    }

    public String getdSFiling_Date() {
        return dSFiling_Date;
    }

    public void setdSFiling_Date(String dSFiling_Date) {
        this.dSFiling_Date = dSFiling_Date;
    }

    public String getdSFilings() {
        return dSFilings;
    }

    public void setdSFilings(String dSFilings) {
        this.dSFilings = dSFilings;
    }

    public String getdSFilingsType() {
        return dSFilingsType;
    }

    public void setdSFilingsType(String dSFilingsType) {
        this.dSFilingsType = dSFilingsType;
    }

    public String getdSType() {
        return dSType;
    }

    public void setdSType(String dSType) {
        this.dSType = dSType;
    }

    public String getPah1() {
        return pah1;
    }

    public void setPah1(String pah1) {
        this.pah1 = pah1;
    }

    public String getdSFocusYear() {
        return dSFocusYear;
    }

    public void setdSFocusYear(String dSFocusYear) {
        this.dSFocusYear = dSFocusYear;
    }

    public String getdSParent_URL() {
        return dSParent_URL;
    }

    public void setdSParent_URL(String dSParent_URL) {
        this.dSParent_URL = dSParent_URL;
    }

    public String getdSSend() {
        return dSSend;
    }

    public void setdSSend(String dSSend) {
        this.dSSend = dSSend;
    }

    public String getdSQuarter() {
        return dSQuarter;
    }

    public void setdSQuarter(String dSQuarter) {
        this.dSQuarter = dSQuarter;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getcIK() {
        return cIK;
    }

    public void setcIK(String cIK) {
        this.cIK = cIK;
    }

    public String getPath1Offline() {
        return path1Offline;
    }

    public void setPath1Offline(String path1Offline) {
        this.path1Offline = path1Offline;
    }

   

}
