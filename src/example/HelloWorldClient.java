package example;

import weatherPackage.GetRegionDatasetResponseGetRegionDatasetResult;
import weatherPackage.WeatherWSLocator;
import weatherPackage.WeatherWSSoap12Stub;
import weatherPackage.WeatherWSSoap_BindingStub;

import java.rmi.activation.Activator;
import java.sql.SQLOutput;

public class HelloWorldClient {

  public static void main(String[] argv) {
    try {
      WeatherWSLocator locator = new WeatherWSLocator();
      //Activator service = locator.getPort();
      // If authorization is required
      //((WeatherWSSoap12Stub)service).setUsername("user3");
      //((WeatherWSSoap12Stub)service).setPassword("pass3");
      // invoke business method
      //service.businessMethod();


      WeatherWSSoap12Stub stub = (WeatherWSSoap12Stub) locator.getPort(WeatherWSSoap12Stub.class);
      String [] regionCountry = stub.getRegionCountry();
      for(String tem : regionCountry){
        System.out.println("-----tem--- =" + tem + "------");
      }

      GetRegionDatasetResponseGetRegionDatasetResult result = stub.getRegionDataset();
      result.get_any();
      System.out.println("-----resul =" + result.toString());


     String[] weather = stub.getWeather("上海","");
     for(String temW : weather){
       System.out.println("-----temWeather =" + temW);
     }


    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
