package com.twomonkeys.mobilemiddlewareproject;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

/**
 * Created by Simo on 3/20/14.
 */
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

        InputStream inputStream = null;

        try {
            httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // make a GET call to the server, which returns all measurements as json
    public Measurement[] getAllMeasurements() {
        HttpGet httpGet = new HttpGet("http://mysterious-anchorage-1011.herokuapp.com");
        InputStream inputStream = null;
        String result = null;
        Measurement[] measurements;

        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = null;

            while((line = reader.readLine()) != null) { // read json data into object
                gson.fromJson(line, Measurement.class);
            }
        } catch(Exception e) {

        }
        return null;
    }
}
