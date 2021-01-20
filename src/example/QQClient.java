package example;

import qqcheck.QqCheckOnlineResponse;
import qqcheck.QqOnlineWebService;
import qqcheck.QqOnlineWebServiceSoap;


import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class QQClient {

    public static void main(String[] args) throws MalformedURLException {
        URL wsdlLocation = new URL("http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx?wsdl");
        QqOnlineWebService service = new QqOnlineWebService(wsdlLocation);
        QqOnlineWebServiceSoap serviceSoap = service.getQqOnlineWebServiceSoap();
        String result = serviceSoap.qqCheckOnline("956433146");

        System.out.println("------result=" + result);

        String result5 = serviceSoap.qqCheckOnline("52635336512");

        System.out.println("------526353365result=" + result5);


        String result2 = serviceSoap.qqCheckOnline("1829646048");

        System.out.println("------1829646048result=" + result2);






    }


    public static String checkQQ(String code) throws MalformedURLException {
        URL url = new URL("http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx?wsdl");
        QName qname = new QName("http://WebXml.com.cn/","qqOnlineWebService");
        Service service = Service.create(url,qname);
        Iterator<QName> qNames = service.getPorts();
        while(qNames.hasNext()){
            QName tem = qNames.next();
            System.out.println("======tem =====" + tem.toString());
        }
        QqOnlineWebServiceSoap soap = service.getPort(new QName("http://WebXml.com.cn/","qqOnlineWebServiceSoap"), QqOnlineWebServiceSoap.class);
        String a = soap.qqCheckOnline("1212121212");


        return "";
    }




}
