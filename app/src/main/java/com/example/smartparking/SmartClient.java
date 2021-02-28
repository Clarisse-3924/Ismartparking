//package com.example.smartparking;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;import java.io.IOException;
//import java.util.ArrayList;import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.HttpUrl;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class SmartClient {
//    public static void findBlocks(String block_code, Callback callback){
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constant.SMART_BASE_URL).newBuilder();
////        urlBuilder.addQueryParameter(Constant.SMART_LOCATION_QUERY_PARAMETER, block_code);
//        String url = urlBuilder.build().toString();
//        Request request = new Request.Builder()
//                .url(url)
//                .header("Authorization", Constant.SMART_API_KEY)
//                .build();
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//}
//    public ArrayList<Modal> processResults(Response response){
//        ArrayList<Modal> modals = new ArrayList<>();
//        try{
//            String jsonData = response.body().string();
//            JSONObject smartJSON= new JSONObject(jsonData);
//            JSONArray smartsJSON = smartJSON.getJSONArray("smarts");
//            if (response.isSuccessful()){
//                for (int i = 0; i < smartsJSON.length(); i++){
//                    JSONObject sJSON = smartsJSON.getJSONObject(i);
//                    String block_code = sJSON.getString("block_code");
//                    String block_photo = sJSON.getString("block_photo");
//                    int location = sJSON.getInt("location");
//                    Boolean is_block_full = sJSON.getBoolean("isblockfull");
//                    Boolean is_accessible = sJSON.getBoolean("isaccessible");
//                    int number_of_slots = sJSON.getInt("numberofslots");
//                    Modal modal = new Modal(block_code, block_photo, location, is_block_full,is_accessible, number_of_slots);
//                    modals.add(modal);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return modals;
//    }
//}