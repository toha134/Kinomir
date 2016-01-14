/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.kinomir.datalayer.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author Антон
 */
@XmlRootElement(name = "data")
@XmlSeeAlso({ClientInfoData.class, OrderInfoData.class})
public class DataNode {
    
}
