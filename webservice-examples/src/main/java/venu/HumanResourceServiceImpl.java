package venu;

import com.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceClient;
import java.math.BigInteger;
import java.net.URL;
import java.util.GregorianCalendar;

/**
 * Created by venusurampudi on 6/1/16.
 */
 @WebService(name = "HumanResource", targetNamespace = "http://mycompany.com/hr/definitions")
public class HumanResourceServiceImpl implements HumanResource {


    @WebMethod
    public void holiday(HolidayRequest holidayRequest) {

        System.out.println("first name is  is " + holidayRequest.getEmployee().getFirstName());

    }

    public static void main(String[] args) {

        try {


           URL wsdlURL = new URL("http://localhost:8080/webservice-examples-1.0-SNAPSHOT/HumanResourceServiceImpl?wsdl");
           QName SERVICE_NAME = new  QName("http://mycompany.com/hr/definitions", "HumanResourceServiceImplService");

            HumanResourceService service = new HumanResourceService(wsdlURL,SERVICE_NAME);

            HolidayRequest hr = new HolidayRequest();


            HolidayType ht = new HolidayType();

            GregorianCalendar gcal = new GregorianCalendar();
            XMLGregorianCalendar xgcal = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(gcal);

            ht.setEndDate(xgcal);
            ht.setEndDate(xgcal);

            hr.setHoliday(ht);

            EmployeeType et = new EmployeeType();

            et.setFirstName("venu");
            et.setLastName("surampudi");
            et.setNumber(BigInteger.valueOf(10));

            hr.setEmployee(et);

            String endpointURL = "http://localhost:8080/webservice-examples-1.0-SNAPSHOT/HumanResourceServiceImpl";

            BindingProvider bp = (BindingProvider) service.getHumanResourcePort();

            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

            System.out.println("before calling" + bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));

            service.getHumanResourcePort().holiday(hr);

        }

        catch(Exception e){

            e.printStackTrace();
        }

    }


}
