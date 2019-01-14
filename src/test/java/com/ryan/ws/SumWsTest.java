package com.ryan.ws;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class SumWsTest {

    @Test
    public void calculateSumShouldReturnAValidResult() {
        try {
            SumWSService service = new SumWSService(
                    new URL("http://localhost:8080/sumws/services/sumService?wsdl"));
            SumWs sumWsPort = service.getSumWsPort();

            Client client = ClientProxy.getClient(sumWsPort);
            Endpoint endpoint = client.getEndpoint();

            HashMap<String, Object> outProps = new HashMap<>();
            outProps.put(WSHandlerConstants.ACTION, "UsernameToken");
            outProps.put(WSHandlerConstants.USER, "sumuser");
            outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
            outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, PasswordCallbackHandler.class.getName());

            WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);

            endpoint.getOutInterceptors().add(wssOut);

            SumRequest sumRequest = new SumRequest();
            sumRequest.setNum1(10);
            sumRequest.setNum2(20);

            SumResponse sumResponse = sumWsPort.calculateSum(sumRequest);

            assertNotNull(sumResponse);
            assertEquals(30, sumResponse.getResult());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
}
