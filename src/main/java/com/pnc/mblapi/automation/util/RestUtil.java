package com.pnc.mblapi.automation.util;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.SSLConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.FileInputStream;
import java.security.*;

import static io.restassured.config.HttpClientConfig.httpClientConfig;

public class RestUtil {
    //Global Setup Variables
    public static String path; //Rest request path

    public static RequestSpecBuilder builder;
    public static RequestSpecification requestSpec;


    public static RequestSpecification https() {

//        KeyStore keyStore = null;
//        SSLConfig config = null;
//
//                try {
//                    keyStore = KeyStore.getInstance("PKCS12");
//                    keyStore.load(new FileInputStream("datapower.p12"),
//                    "datapower".toCharArray());
//
//        } catch (Exception ex) {
//            throw new RuntimeException("Unable to load the P12 file for datapower");
//        }
//
//        try {
//            SSLSocketFactory clientAuthFactory = new SSLSocketFactory(keyStore);
//            // set the config in rest assured
//            config = new SSLConfig().with().sslSocketFactory(clientAuthFactory).and().allowAllHostnames();
//            RestAssured.config = RestAssured.config().sslConfig(config);
//        } catch (Exception e) {
//            throw new RuntimeException("Unable to initialize SSL Socket Factory");
//        }

        final HttpClient httpClient;
        try {
            httpClient = SslTrustContext.getClient();
        } catch (Exception e) {
            throw new RuntimeException("Client initialziation failed");
        }

        RestAssured.config().
                httpClient(httpClientConfig().httpClientFactory(
                        new HttpClientConfig.HttpClientFactory() {

                            @Override
                            public HttpClient createHttpClient() {
                                return httpClient;
                            }
                        }));

        return RestAssured.given();
    }

    /*
    ***Sets Base URI***
    Before starting the test, we should set the RestAssured.baseURI
    */
    public static String getBaseURlForDataPower() {

        return RestAssured.baseURI = "https://servicelink1qa.pnc.com:443/mbl/massmarket";
    }

    public static String getBaseURlForPnc() {

        return RestAssured.baseURI = "https://mobile-qa2.pnc.com";
    }

    /*
    ***Sets base path***
    Before starting the test, we should set the RestAssured.basePath
    */
    public static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;
    }

    /*
    ***Reset Base URI (after test)***
    After the test, we should reset the RestAssured.baseURI
    */
    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }

    /*
    ***Reset base path (after test)***
    After the test, we should reset the RestAssured.basePath
    */
    public static void resetBasePath() {
        RestAssured.basePath = null;
    }
 
    /*
    ***Sets ContentType***
    We should set content type as JSON or XML before starting the test
    */

    //  public static RequestSpecification getequestSpecification(){
    //   return given().spec(getRequestSpecBuilder());
    // }
 
    /*
    ***search query path of first example***
    It is  equal to "barack obama/videos.json?num_of_videos=4"
    */
 
    /*
    ***Returns response***
    We send "path" as a parameter to the Rest Assured'a "get" method
    and "get" method returns response of API
    */
    // public static Response getResponse(String path,RequestSpecification rs){
    //System.out.print("path: " + path +"\n");
    //  return rs.post(path);
    // }


    /*
    ***Returns JsonPath object***
    * First convert the API's response to String type with "asString()" method.
    * Then, send this String formatted json response to the JsonPath class and return the JsonPath
    */
    public JsonPath getJsonPath(Response res) {
        String json = res.asString();
        System.out.print("returned json: " + json + "\n");
        return new JsonPath(json);
    }


}

