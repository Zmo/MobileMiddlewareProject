package com.twomonkeys.mobilemiddlewareproject;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;

/**
 * Created by Simo on 3/20/14.
 */

// A class to communicate with the rails app that can be found at http://mysterious-anchorage-1011.herokuapp.com
public class AsyncDatabaseCommunicator extends AsyncTask<String, String, String> {

    private DefaultHttpClient httpClient;

    private Gson gson;

    public AsyncDatabaseCommunicator() {
        httpClient = new DefaultHttpClient();
        gson = new Gson();
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    // make a POST call to the server with all the required measurement attributes as json
    public void addMeasurement(Measurement measurement) {
        HttpPost httpPost = new HttpPost("http://mysterious-anchorage-1011.herokuapp.com/measurements.json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        try {
            StringEntity stringEntity = new StringEntity("JSON: " + gson.toJson(measurement));
            stringEntity.setContentEncoding((new BasicHeader(HTTP.CONTENT_TYPE, "application/json")));
            httpPost.setEntity(stringEntity);
            httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Makes a GET call to the server, which returns all measurements as json.
    // Then converts the json into Measurement objects and returns them in an arraylist
    public ArrayList<Measurement> getAllMeasurements() {
        HttpGet httpGet = new HttpGet("http://mysterious-anchorage-1011.herokuapp.com");
        InputStream inputStream = null;
        String result = null;
        ArrayList<Measurement> measurements = new ArrayList<Measurement>();

        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = null;

            while((line = reader.readLine()) != null) { // read json data into measurement objects
                measurements.add(gson.fromJson(line, Measurement.class));
            }
        } catch(Exception e) {

        }
        return measurements;
    }
}
