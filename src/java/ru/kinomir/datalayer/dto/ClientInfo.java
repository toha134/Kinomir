/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import com.google.gson.annotations.SerializedName;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "client")
public class ClientInfo {

    @SerializedName("idclient")
    String idclient;
    @SerializedName("iddocument")
    String iddocument;
    @SerializedName("F")
    String F;
    @SerializedName("I")
    String I;
    @SerializedName("O")
    String O;
    @SerializedName("address")
    String address;
    @SerializedName("phone")
    String phone;
    @SerializedName("secaddress")
    String secaddress;
    @SerializedName("city")
    String city;
    @SerializedName("email")
    String email;
    @SerializedName("fax")
    String fax;
    @SerializedName("addstring")
    String addstring;
    @SerializedName("Cellular")
    String Cellular;
    @SerializedName("BeginTime")
    String BeginTime;
    @SerializedName("BarCode")
    String BarCode;
    @SerializedName("AccScidka")
    String AccSkidka;
    @SerializedName("Description")
    String Description;
    @SerializedName("Description2")
    String Description2;
    @SerializedName("Description3")
    String Description3;
    @SerializedName("Birthday")
    String Birthday;
    @SerializedName("Login")
    String Login;
    @SerializedName("DocName")
    String DocName;
    @SerializedName("OperationLimit")
    String OperationLimit;
    @SerializedName("OrderLife")
    String OrderLife;
    @SerializedName("OrderLifeBeforePerformance")
    String OrderLifeBeforePerformance;
    @SerializedName("IdSchBA")
    String IdSchBA;
    @SerializedName("ShowLimit")
    String ShowLimit;
    @SerializedName("PerformanceLimit")
    String PerformanceLimit;
    @SerializedName("UseAccSkidka")
    String UseAccSkidka;
    @SerializedName("DiskountName")
    String DiskountName;
    @SerializedName("Percent")
    String Percent;
    @SerializedName("isBlocFastSale")
    String isBlocFastSale;

    public ClientInfo() {

    }

    public ClientInfo(ResultSet rs) throws SQLException {
        idclient = rs.getString("idclient");
        iddocument = rs.getString("iddocument");
        F = rs.getString("F");
        I = rs.getString("I");
        O = rs.getString("O");
        address = rs.getString("address");
        phone = rs.getString("phone");
        secaddress = rs.getString("secaddress");
        city = rs.getString("city");
        email = rs.getString("email");
        fax = rs.getString("fax");
        addstring = rs.getString("addstring");
        Cellular = rs.getString("Cellular");
        BeginTime = rs.getString("BeginTime");
        BarCode = rs.getString("BarCode");
        AccSkidka = rs.getString("AccSkidka");
        Description = rs.getString("Description");
        Description2 = rs.getString("Description2");
        Description3 = rs.getString("Description3");
        Birthday = rs.getString("Birthday");
        Login = rs.getString("Login");
        DocName = rs.getString("DocName");
        OperationLimit = rs.getString("OperationLimit");
        OrderLife = rs.getString("OrderLife");
        OrderLifeBeforePerformance = rs.getString("OrderLifeBeforePerformance");
        IdSchBA = rs.getString("IdSchBA");
        ShowLimit = rs.getString("ShowLimit");
        PerformanceLimit = rs.getString("PerformanceLimit");
        UseAccSkidka = rs.getString("UseAccSkidka");
        DiskountName = rs.getString("DiskountName");
        Percent = rs.getString("Percent");
        isBlocFastSale = rs.getString("isBlocFastSale");
    }

    @XmlElement(name = "idclient", required = true)
    public String getIdclient() {
        return idclient;
    }

    @XmlElement(name = "iddocument", required = true)
    public String getIddocument() {
        return iddocument;
    }

    @XmlElement(name = "F", required = true)
    public String getF() {
        return F;
    }

    @XmlElement(name = "I", required = true)
    public String getI() {
        return I;
    }

    @XmlElement(name = "O", required = true)
    public String getO() {
        return O;
    }

    @XmlElement(name = "address", required = true)
    public String getAddress() {
        return address;
    }

    @XmlElement(name = "phone", required = true)
    public String getPhone() {
        return phone;
    }

    @XmlElement(name = "secaddress", required = true)
    public String getSecaddress() {
        return secaddress;
    }

    @XmlElement(name = "city", required = true)
    public String getCity() {
        return city;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    @XmlElement(name = "fax")
    public String getFax() {
        return fax;
    }

    @XmlElement(name = "addstring")
    public String getAddstring() {
        return addstring;
    }

    @XmlElement(name = "Cellular")
    public String getCellular() {
        return Cellular;
    }

    @XmlElement(name = "BeginTime")
    public String getBeginTime() {
        return BeginTime;
    }

    @XmlElement(name = "BarCode")
    public String getBarCode() {
        return BarCode;
    }

    @XmlElement(name = "AccSkidka")
    public String getAccSkidka() {
        return AccSkidka;
    }

    @XmlElement(name = "Description")
    public String getDescription() {
        return Description;
    }

    @XmlElement(name = "Description2")
    public String getDescription2() {
        return Description2;
    }

    @XmlElement(name = "Description3")
    public String getDescription3() {
        return Description3;
    }

    @XmlElement(name = "Birthday")
    public String getBirthday() {
        return Birthday;
    }

    @XmlElement(name = "Login")
    public String getLogin() {
        return Login;
    }

    @XmlElement(name = "DocName")
    public String getDocName() {
        return DocName;
    }

    @XmlElement(name = "OperationLimit")
    public String getOperationLimit() {
        return OperationLimit;
    }

    @XmlElement(name = "OrderLife")
    public String getOrderLife() {
        return OrderLife;
    }

    @XmlElement(name = "OrderLifeBeforePerformance")
    public String getOrderLifeBeforePerformance() {
        return OrderLifeBeforePerformance;
    }

    @XmlElement(name = "IdSchBA")
    public String getIdSchBA() {
        return IdSchBA;
    }

    @XmlElement(name = "ShowLimit")
    public String getShowLimit() {
        return ShowLimit;
    }

    @XmlElement(name = "PerformanceLimit")
    public String getPerformanceLimit() {
        return PerformanceLimit;
    }

    @XmlElement(name = "UseAccSkidka")
    public String getUseAccSkidka() {
        return UseAccSkidka;
    }

    @XmlElement(name = "DiskountName")
    public String getDiskountName() {
        return DiskountName;
    }

    @XmlElement(name = "Percent")
    public String getPercent() {
        return Percent;
    }

    @XmlElement(name = "isBlocFastSale")
    public String getIsBlocFastSale() {
        return isBlocFastSale;
    }
}
