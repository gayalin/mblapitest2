package com.pnc.mblapi.automation.util;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.*;


public class RestUtil {
	 //Global Setup Variables
    public static String path; //Rest request path
    
    public static RequestSpecBuilder builder;
    public static RequestSpecification requestSpec;
 
    /*
    ***Sets Base URI***
    Before starting the test, we should set the RestAssured.baseURI
    */
    public static void setBaseURI (String baseURI){
        RestAssured.baseURI = baseURI;
    }
 
    /*
    ***Sets base path***
    Before starting the test, we should set the RestAssured.basePath
    */
    public static void setBasePath(String basePathTerm){
        RestAssured.basePath = basePathTerm;
    }
 
    /*
    ***Reset Base URI (after test)***
    After the test, we should reset the RestAssured.baseURI
    */
    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }
 
    /*
    ***Reset base path (after test)***
    After the test, we should reset the RestAssured.basePath
    */
    public static void resetBasePath(){
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
    public JsonPath getJsonPath (Response res) {
        String json = res.asString();
        System.out.print("returned json: " + json +"\n");
        return new JsonPath(json);
    }


}

