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

/**
 *
 * @author Антон
 */
public class Client {

    @SerializedName("IdClient")
    private final String IdClient;
    @SerializedName("IdDocument")
    private final String IdDocument;
    @SerializedName("Name")
    private final String Name;
    @SerializedName("Description")
    private final String Description;
    @SerializedName("Type")
    private final String Type;
    @SerializedName("Address")
    private final String Address;
    @SerializedName("Phone")
    private final String Phone;
    @SerializedName("INN")
    private final String INN;
    @SerializedName("RS")
    private final String RS;
    @SerializedName("AcountBank")
    private final String AcountBank;
    @SerializedName("KS")
    private final String KS;
    @SerializedName("BIK")
    private final String BIK;
    @SerializedName("OKONH")
    private final String OKONH;
    @SerializedName("OKPO")
    private final String OKPO;
    @SerializedName("SecAddress")
    private final String SecAddress;
    @SerializedName("City")
    private final String City;
    @SerializedName("Email")
    private final String Email;
    @SerializedName("Fax")
    private final String Fax;
    @SerializedName("FirstName")
    private final String FirstName;
    @SerializedName("Patronymic")
    private final String Patronymic;
    @SerializedName("AddString")
    private final String AddString;
    @SerializedName("IsStopped")
    private final String IsStopped;
    @SerializedName("IsHidden")
    private final String IsHidden;
    @SerializedName("IdCurInvoice")
    private final String IdCurInvoice;
    @SerializedName("Cellular")
    private final String Cellular;
    @SerializedName("OrderNumber")
    private final String OrderNumber;
    @SerializedName("InstId")
    private final String InstId;
    @SerializedName("IdExtClient")
    private final String IdExtClient;
    @SerializedName("Foto")
    private final String Foto;
    @SerializedName("CardNumber")
    private final String CardNumber;
    @SerializedName("EndTime")
    private final String EndTime;
    @SerializedName("BeginTime")
    private final String BeginTime;
    @SerializedName("CardState")
    private final String CardState;
    @SerializedName("PrevIdClient")
    private final String PrevIdClient;
    @SerializedName("Initiator")
    private final String Initiator;
    @SerializedName("CardDescription")
    private final String CardDescription;
    @SerializedName("CounterTSTransfer")
    private final String CounterTSTransfer;
    @SerializedName("BarCode")
    private final String BarCode;
    @SerializedName("Balans")
    private final String Balans;
    @SerializedName("AvansFromZU")
    private final String AvansFromZU;
    @SerializedName("AccSkidka")
    private final String AccSkidka;
    @SerializedName("PName")
    private final String PName;
    @SerializedName("Description2")
    private final String Description2;
    @SerializedName("Description3")
    private final String Description3;
    @SerializedName("Description4")
    private final String Description4;
    @SerializedName("kpid")
    private final String kpid;
    @SerializedName("Birthday")
    private final String Birthday;
    @SerializedName("CouldSaleAlienTickets")
    private final String CouldSaleAlienTickets;
    @SerializedName("SecretKey")
    private final String SecretKey;
    @SerializedName("IdUserCardOperation")
    private final String IdUserCardOperation;
    @SerializedName("DataTimeCardOper")
    private final String DataTimeCardOper;
    @SerializedName("NoteCauseOperCard")
    private final String NoteCauseOperCard;
    @SerializedName("Login")
    private final String Login;

    public Client() {
        IdClient = "";
        IdDocument = "";
        Name = "";
        Description = "";
        Type = "";
        Address = "";
        Phone = "";
        INN = "";
        RS = "";
        AcountBank = "";
        KS = "";
        BIK = "";
        OKONH = "";
        OKPO = "";
        SecAddress = "";
        City = "";
        Email = "";
        Fax = "";
        FirstName = "";
        Patronymic = "";
        AddString = "";
        IsStopped = "";
        IsHidden = "";
        IdCurInvoice = "";
        Cellular = "";
        OrderNumber = "";
        InstId = "";
        IdExtClient = "";
        Foto = "";
        CardNumber = "";
        EndTime = "";
        BeginTime = "";
        CardState = "";
        PrevIdClient = "";
        Initiator = "";
        CardDescription = "";
        CounterTSTransfer = "";
        BarCode = "";
        Balans = "";
        AvansFromZU = "";
        AccSkidka = "";
        PName = "";
        Description2 = "";
        Description3 = "";
        Description4 = "";
        kpid = "";
        Birthday = "";
        CouldSaleAlienTickets = "";
        SecretKey = "";
        IdUserCardOperation = "";
        DataTimeCardOper = "";
        NoteCauseOperCard = "";
        Login = "";

    }

    public Client(ResultSet rs) throws SQLException {
        IdClient = rs.getString("IdClient");
        IdDocument = rs.getString("IdDocument");
        Name = rs.getString("Name");
        Description = rs.getString("Description");
        Type = rs.getString("Type");
        Address = rs.getString("Address");
        Phone = rs.getString("Phone");
        INN = rs.getString("INN");
        RS = rs.getString("RS");
        AcountBank = rs.getString("AcountBank");
        KS = rs.getString("KS");
        BIK = rs.getString("BIK");
        OKONH = rs.getString("OKONH");
        OKPO = rs.getString("OKPO");
        SecAddress = rs.getString("SecAddress");
        City = rs.getString("City");
        Email = rs.getString("Email");
        Fax = rs.getString("Fax");
        FirstName = rs.getString("FirstName");
        Patronymic = rs.getString("Patronymic");
        AddString = rs.getString("AddString");
        IsStopped = rs.getString("IsStopped");
        IsHidden = rs.getString("IsHidden");
        IdCurInvoice = rs.getString("IdCurInvoice");
        Cellular = rs.getString("Cellular");
        OrderNumber = rs.getString("OrderNumber");
        InstId = rs.getString("InstId");
        IdExtClient = rs.getString("IdExtClient");
        Foto = rs.getString("Foto");
        CardNumber = rs.getString("CardNumber");
        EndTime = rs.getString("EndTime");
        BeginTime = rs.getString("BeginTime");
        CardState = rs.getString("CardState");
        PrevIdClient = rs.getString("PrevIdClient");
        Initiator = rs.getString("Initiator");
        CardDescription = rs.getString("CardDescription");
        CounterTSTransfer = rs.getString("CounterTSTransfer");
        BarCode = rs.getString("BarCode");
        Balans = rs.getString("Balans");
        AvansFromZU = rs.getString("AvansFromZU");
        AccSkidka = rs.getString("AccSkidka");
        PName = rs.getString("PName");
        Description2 = rs.getString("Description2");
        Description3 = rs.getString("Description3");
        Description4 = rs.getString("Description4");
        kpid = rs.getString("kpid");
        Birthday = rs.getString("Birthday");
        CouldSaleAlienTickets = rs.getString("CouldSaleAlienTickets");
        SecretKey = rs.getString("SecretKey");
        IdUserCardOperation = rs.getString("IdUserCardOperation");
        DataTimeCardOper = rs.getString("DataTimeCardOper");
        NoteCauseOperCard = rs.getString("NoteCauseOperCard");
        Login = rs.getString("Login");
    }

    @XmlElement(name = "IdClient")
    public String getIdClient() {
        return IdClient;
    }

    @XmlElement(name = "Phone")
    public String getPhone() {
        return Phone;
    }

    @XmlElement(name = "INN")
    public String getINN() {
        return INN;
    }

    @XmlElement(name = "RS")
    public String getRS() {
        return RS;
    }

    @XmlElement(name = "AcountBank")
    public String getAcountBank() {
        return AcountBank;
    }

    @XmlElement(name = "KS")
    public String getKS() {
        return KS;
    }

    @XmlElement(name = "BIK")
    public String getBIK() {
        return BIK;
    }

    @XmlElement(name = "OKONH")
    public String getOKONH() {
        return OKONH;
    }

    @XmlElement(name = "OKPO")
    public String getOKPO() {
        return OKPO;
    }

    @XmlElement(name = "SecAddress")
    public String getSecAddress() {
        return SecAddress;
    }

    @XmlElement(name = "City")
    public String getCity() {
        return City;
    }

    @XmlElement(name = "Email")
    public String getEmail() {
        return Email;
    }

    @XmlElement(name = "Fax")
    public String getFax() {
        return Fax;
    }

    @XmlElement(name = "FirstName")
    public String getFirstName() {
        return FirstName;
    }

    @XmlElement(name = "Patronymic")
    public String getPatronymic() {
        return Patronymic;
    }

    @XmlElement(name = "AddString")
    public String getAddString() {
        return AddString;
    }

    @XmlElement(name = "IsStopped")
    public String getIsStopped() {
        return IsStopped;
    }

    @XmlElement(name = "IsHidden")
    public String getIsHidden() {
        return IsHidden;
    }

    @XmlElement(name = "IdCurInvoice")
    public String getIdCurInvoice() {
        return IdCurInvoice;
    }

    @XmlElement(name = "Cellular")
    public String getCellular() {
        return Cellular;
    }

    @XmlElement(name = "OrderNumber")
    public String getOrderNumber() {
        return OrderNumber;
    }

    @XmlElement(name = "InstId")
    public String getInstId() {
        return InstId;
    }

    @XmlElement(name = "IdExtClient")
    public String getIdExtClient() {
        return IdExtClient;
    }

    @XmlElement(name = "Foto")
    public String getFoto() {
        return Foto;
    }

    @XmlElement(name = "CardNumber")
    public String getCardNumber() {
        return CardNumber;
    }

    @XmlElement(name = "EndTime")
    public String getEndTime() {
        return EndTime;
    }

    @XmlElement(name = "BeginTime")
    public String getBeginTime() {
        return BeginTime;
    }

    @XmlElement(name = "CardState")
    public String getCardState() {
        return CardState;
    }

    @XmlElement(name = "PrevIdClient")
    public String getPrevIdClient() {
        return PrevIdClient;
    }

    @XmlElement(name = "Initiator")
    public String getInitiator() {
        return Initiator;
    }

    @XmlElement(name = "CardDescription")
    public String getCardDescription() {
        return CardDescription;
    }

    @XmlElement(name = "CounterTSTransfer")
    public String getCounterTSTransfer() {
        return CounterTSTransfer;
    }

    @XmlElement(name = "BarCode")
    public String getBarCode() {
        return BarCode;
    }

    @XmlElement(name = "Balans")
    public String getBalans() {
        return Balans;
    }

    @XmlElement(name = "AvansFromZU")
    public String getAvansFromZU() {
        return AvansFromZU;
    }

    @XmlElement(name = "AccSkidka")
    public String getAccSkidka() {
        return AccSkidka;
    }

    @XmlElement(name = "PName")
    public String getPName() {
        return PName;
    }

    @XmlElement(name = "Description2")
    public String getDescription2() {
        return Description2;
    }

    @XmlElement(name = "Description3")
    public String getDescription3() {
        return Description3;
    }

    @XmlElement(name = "Description4")
    public String getDescription4() {
        return Description4;
    }

    @XmlElement(name = "kpid")
    public String getkpid() {
        return kpid;
    }

    @XmlElement(name = "Birthday")
    public String getBirthday() {
        return Birthday;
    }

    @XmlElement(name = "CouldSaleAlienTickets")
    public String getCouldSaleAlienTickets() {
        return CouldSaleAlienTickets;
    }

    @XmlElement(name = "SecretKey")
    public String getSecretKey() {
        return SecretKey;
    }

    @XmlElement(name = "IdUserCardOperation")
    public String getIdUserCardOperation() {
        return IdUserCardOperation;
    }

    @XmlElement(name = "DataTimeCardOper")
    public String getDataTimeCardOper() {
        return DataTimeCardOper;
    }

    @XmlElement(name = "NoteCauseOperCard")
    public String getNoteCauseOperCard() {
        return NoteCauseOperCard;
    }

    @XmlElement(name = "Login")
    public String getLogin() {
        return Login;
    }

    @XmlElement(name = "IdDocument")
    public String getIdDocument() {
        return IdDocument;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return Name;
    }

    @XmlElement(name = "Description")
    public String getDescription() {
        return Description;
    }

    @XmlElement(name = "Type")
    public String getType() {
        return Type;
    }

    @XmlElement(name = "Address")
    public String getAddress() {
        return Address;
    }

}
