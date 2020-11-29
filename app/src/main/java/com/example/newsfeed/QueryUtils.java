package com.example.newsfeed;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    private static URL createUrl(String sampleUrl){
        URL url;
        if (!sampleUrl.isEmpty()){
            try {
                url = new URL(sampleUrl);
                return url;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String makeHttpRequest(String sampleUrl) throws IOException {
        URL url = createUrl(sampleUrl);

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        String responseJson = "";

        if (url == null){
            return responseJson;
        }else {
            url = createUrl(sampleUrl);
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(15000);
                urlConnection.connect();
                if (urlConnection.getResponseCode() == urlConnection.HTTP_OK){
                    inputStream = urlConnection.getInputStream();
                    responseJson = readResponseStream(inputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
                if (inputStream != null){
                    inputStream.close();
                }
            }
        }
        return responseJson;
    }

    private static String readResponseStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String readLine = reader.readLine();
        while (readLine != null){
            stringBuilder.append(readLine);
            readLine = reader.readLine();
        }
        return stringBuilder.toString();
    }

    public static List<Fields> parseResponse(String response){
        List<Fields> list = new ArrayList<>();
//        Fields fields = null;
            try {
                JSONObject root = new JSONObject(response);
                JSONObject objJSON_response = root.getJSONObject("response");
                JSONArray arrJSON_results = objJSON_response.optJSONArray("results");
                for (int i = 0; i < arrJSON_results.length(); i++) {
                    JSONObject objJSON_i = arrJSON_results.optJSONObject(i);
                    String title = objJSON_i.optString("webTitle");
                    String date = objJSON_i.optString("webPublicationDate");
                    String section = objJSON_i.optString("pillarName");
                    String url = objJSON_i.optString("webUrl");
//                    fields = new Fields(title, date, section, url);
                    list.add(new Fields(title, date, section, url));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        return list;
    }

//    public static List<Fields> callAll(String url) throws IOException {
//        List<Fields> fieldsList = new ArrayList<>();
//        String response = makeHttpRequest(url);
//        fieldsList = parseResponse(response);
//        return fieldsList;
//    }


//    public static List<Fields> sendRequestByVolley(String url, Context context){
//        final List<Fields> fieldsList = new ArrayList<>();
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
////                Fields fields = parseResponse(response);
////                fieldsList.add(fields);
//                fieldsList.add(parseResponse(response));
//                Log.e("All News", String.valueOf(fieldsList.get(0)));
//            }
//        },
//                new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("All News", error.getMessage());
//            }
//        }
//        );
//        requestQueue.add(stringRequest);
//        return fieldsList;
//    }




    /*
    public static class NewsAsyncTask extends AsyncTaskLoader<List<Fields>> {
        private String url;
        public NewsAsyncTask(@NonNull Context context, String url) {
            super(context);
            this.url = url;
        }

        @Nullable
        @Override
        public List<Fields> loadInBackground() {
            if (!url.isEmpty()){
                try {
                    List<Fields> fields = QueryUtils.callAll(url);
                    return fields;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
*/


}
