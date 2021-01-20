package example;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {


    public static void main(String[] args) {
        String urlAddress = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl";
        String soapXml = getMobileXml("13781695457","");
        String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";

        String response = doSoapWsdl(urlAddress,soapXml,soapAction);
        System.out.println("======response=" + response);


//        String urlAddress = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
//        String soapXml = getWeatherXml("2015","");
//        String soapAction = "http://WebXml.com.cn/getWeather";
//
//        String response = doSoapWsdl(urlAddress,soapXml,soapAction);
//        System.out.println("======response=" + response);
    }






    public static String doSoapWsdl(String urlAddress,String xml,String soapAction){
        URL url;
        HttpURLConnection uRLConnection;
        try {
            url = new URL(urlAddress);
            uRLConnection = (HttpURLConnection)url.openConnection();
            uRLConnection.setDoInput(true);
            uRLConnection.setDoOutput(true);
            uRLConnection.setRequestMethod("POST");
            uRLConnection.setUseCaches(false);
            uRLConnection.setInstanceFollowRedirects(false);
            uRLConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            uRLConnection.setRequestProperty("SOAPAction", soapAction);
            uRLConnection.connect();

            DataOutputStream out = new DataOutputStream(uRLConnection.getOutputStream());
            byte[] content = xml.getBytes("UTF-8");
            out.write(content);
            out.flush();
            out.close();


            int responseCode = uRLConnection.getResponseCode();
            System.out.println("----responseCode=" + responseCode);

            InputStream is = uRLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String response = "";
            String readLine = null;
            while((readLine =br.readLine()) != null){
                //response = br.readLine();
                response = response + readLine;
            }

//            SAXReader reader  = new SAXReader();
//            String result = "";
//            Document doc = reader.read(in);
//            result = doc.getRootElement().element("Body").element("statusWriteBackResponse").element("return").getText();
//            logger.debug("result:"+result);

            is.close();
            br.close();
            uRLConnection.disconnect();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }





    public static String getMobileXml(String phone,String userId){
        String xml ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">\n" +
                "      <mobileCode>"+ phone +"</mobileCode>\n" +
                "      <userID>"+ userId +"</userID>\n" +
                "    </getMobileCodeInfo>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        return xml;
    }


    public static String getWeatherXml(String cityCode,String userId){
        String xml ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getWeather xmlns=\"http://WebXml.com.cn/\">\n" +
                "      <theCityCode>"+ cityCode +"</theCityCode>\n" +
                "      <theUserID>"+ userId +"</theUserID>\n" +
                "    </getWeather>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        return xml;
    }






}
