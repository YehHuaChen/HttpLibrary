package com.example.cyh.httplibrary;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YehHua.C on 15/4/13.
 */
public class HttpLibrary {

    public String httpByPost(String url, String queryKey, String queryValue){

        String result = "";
        HttpClient client = new DefaultHttpClient();;

        try {

            HttpPost post = new HttpPost(url);

            //params
            if (queryKey != ""){
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair(queryKey, queryValue));
                UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
                post.setEntity(ent);
            }

            HttpResponse responsePOST = client.execute(post);

            HttpEntity resEntity = responsePOST.getEntity();

            if (resEntity != null) {
                result = EntityUtils.toString(resEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.getConnectionManager().shutdown();
        }


        return result;
    }


    public String httpByGet(String url){

        String result = "";
        HttpClient client = new DefaultHttpClient();

        try {

            HttpGet get = new HttpGet(url);

            HttpResponse response = client.execute(get);

            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {
                result = EntityUtils.toString(resEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.getConnectionManager().shutdown();
        }


        return result;

    }
}