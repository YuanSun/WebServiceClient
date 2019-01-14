package com.ryan.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2018-12-28T16:43:24.868-05:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://ws.ryan.com/", name = "SumWs")
@XmlSeeAlso({ObjectFactory.class})
public interface SumWs {

    @WebMethod
    @RequestWrapper(localName = "calculateSum", targetNamespace = "http://ws.ryan.com/", className = "com.ryan.ws.CalculateSum")
    @ResponseWrapper(localName = "calculateSumResponse", targetNamespace = "http://ws.ryan.com/", className = "com.ryan.ws.CalculateSumResponse")
    @WebResult(name = "response", targetNamespace = "")
    public com.ryan.ws.SumResponse calculateSum(
        @WebParam(name = "arg0", targetNamespace = "")
        com.ryan.ws.SumRequest arg0
    );
}