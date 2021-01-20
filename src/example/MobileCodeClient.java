package example;

import mobilecode.ArrayOfString;
import mobilecode.MobileCodeWS;
import mobilecode.MobileCodeWSSoap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MobileCodeClient {
    public static void main(String[] args) throws MalformedURLException {

        URL wsdlLocation = new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl");
        MobileCodeWS mobileCodeWS = new MobileCodeWS(wsdlLocation);
        MobileCodeWSSoap soap = mobileCodeWS.getMobileCodeWSSoap();

        ArrayOfString arrayOfString = soap.getDatabaseInfo();
        List<String> list = arrayOfString.getString();
        if(list != null && list.size() > 0){
            System.out.println("----list.size()=" + list.size());
            StringBuilder sb = new StringBuilder("数据为：");
            for(String tem : list){
                sb.append(tem).append("/");
            }
            System.out.println("-----tem-----" + sb.toString());
        }

        System.out.println("**********************************************************");
        String result = soap.getMobileCodeInfo("17688542670","");
        System.out.println("====result====" + result);




    }
}
