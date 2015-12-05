package com.team4.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpPressTest {
	
	
	public  static String parameterData="{\"id\": \"sdsadasdadadsa\"," +
				"\"imp\": " +
				"	[{\"id\": \"944B7ED8EDF5422BA00E6D060C896160\"," +
				"		\"banner\": {" +
				"			\"w\": 640," +
				"			\"h\":100," +
				"			\"btype\": [6]," +
				"			\"api\": [5]," +
				"			\"pos\":2}," +
				"			\"bidfloor\": 1," +
				"			\"bidfloorcur\": \"CNY\"," +
				"			\"instl\": 1," +
				"			\"ext\": {\"splash\": 0}" +
				"			}" +
				"	]," +
				"\"app\": {" +
				"	\"id\": \"LWJTEST201510153b452c45506c08672\"," +
				"	\"name\": \"wj测试全插屏640*1136\"," +
				"	\"cat\": [\"60009\"]," +
				"	\"ver\": \"4.1.0\"," +
				"	\"bundle\": \"养生\"," +
				"	\"paid\": 0," +
				"	\"storeurl\": \"http://www.baidu.com\"," +
				"	\"keywords\": \"健康\"," +
				"	\"pub\": \"zplay\"" +
				"}," +
				"\"device\": {" +
				"	\"did\": \"83917FACD2D2EC1089B0FFFF169B01F65FCD934A\"," +
				"	\"dpid\": \"c4bf4275\"," +
				"	\"mac\": \"DPkuRuOtjLEIQYnKJvccrnA4kC8Ccv7xiiwqwQwn\"," +
				"	\"ua\": \"Mozilla%2F5.0+%28Linux%3B+U%3B+Android+4.0.3%3B+zh-cn%3B+GT-I9100+Build%2FIML74K%29+AppleWebKit%2F534.30+%28KHTML%2C+like+Gecko%29+Version%2F4.0+Mobile+Safari%2F534.30\",\"dnt\": 0," +
				"	\"ip\": \"60.1.130.77\"," +
				"	\"devicetype\": 2," +
				"	\"make\": \"Xiaomi\"," +
				"	\"model\": \"MI+3\"," +
				"	\"os\": \"Android\"," +
				"	\"osv\": \"4.4.4\"," +
				"	\"connectiontype\": 1," +
				"	\"carrier\": \"46000\"," +
				"	\"language\": \"zh\"," +
				"	\"w\": 728," +
				"	\"h\": 90," +
				"	\"ppi\": 1024," +
				"	\"pxratio\": 1.0," +
				"   \"ext\": {" +
				"   \"orientation\": 1" +
				"        }" +
				"   }" +
				"}";
	
	public static void main(String[] args) throws MalformedURLException {
		//URL url = new URL("http://localhost:8082/RTB/index.html");
		
		Thread  t1=new Thread(){
				@Override
				public void run() {
					URL url=null;
					try {
						url = new URL("http://36.110.85.1:8180/");
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					ExecutorService exec = Executors.newFixedThreadPool(300000);
					int NO = 0;
					try {
					while(NO<1000) {
						RequestThread  run=new RequestThread(NO,parameterData,url);
						exec.execute(run);
						NO++;
						System.out.println("NO-----:"+NO);
						if(NO==1000){
							System.out.println("-------------over------------");
						}
						
							Thread.sleep(2);
					}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
		};

		t1.start();
		

		Thread  t2=new Thread(){
			@Override
			public void run() {
				URL url=null;
				try {
					url = new URL("http://36.110.85.2:8180/");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ExecutorService exec = Executors.newFixedThreadPool(3000000);
				int NO = 0;
				try {
				while(NO<1000) {
					RequestThread  run=new RequestThread(NO,parameterData,url);
					exec.execute(run);
					NO++;
					Thread.sleep(2);
				}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
	};

	t2.start();
		
	Thread  t3=new Thread(){
		@Override
		public void run() {
			URL url=null;
			try {
				url = new URL("http://36.110.85.3:8180/");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ExecutorService exec = Executors.newFixedThreadPool(3000000);
			int NO = 0;
			try {
			while(NO<1000) {
				RequestThread  run=new RequestThread(NO,parameterData,url);
				exec.execute(run);
				NO++;
				Thread.sleep(2);
			}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	};
	
	t3.start();
	
	// 退出线程池

		//exec.shutdown();

	}

}

class RequestThread implements Runnable{
	
	private URL url;
	private String paramData;
	private int no=0;
	public RequestThread(int no,String paramData,URL url){
		this.url=url;
		this.paramData=paramData;
		this.no=no;
	}
	
	
	public void run() {
		
		try {
			
			URLRequest( url,paramData);
			if(no==999){
				System.out.println("no==999");
			}
			System.out.println(no);
		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	
	
	public static String  URLRequest(URL localURL,String parameterData) throws Exception{
		 
		URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
        
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
     
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameterData.length()));
        
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        
        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            
            outputStreamWriter.write(parameterData.toString());
            outputStreamWriter.flush();
            
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }
            
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            
        } finally {
            
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            
            if (outputStream != null) {
                outputStream.close();
            }
            
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }
            
        }

        return resultBuffer.toString();
	}
}