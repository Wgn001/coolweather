package gn.example.coolweather.util;

import android.text.TextUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import gn.example.coolweather.db.City;
import gn.example.coolweather.db.County;
import gn.example.coolweather.db.Province;

public class Utility {
    /**
     * 解析处理服务器返回的省级数据
     *
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces=new JSONArray(response);
                for(int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject=allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.setProvinceName(provinceObject.getString("name"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities=new JSONArray(response);
                for(int i=0;i<allCities.length();i++){
                    JSONObject cityObject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }


        }
        return false;
    }

    /**
     * 解析处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response,int cityId){
            if(!TextUtils.isEmpty(response)){
                try {
                    JSONArray allCounties=new JSONArray(response);
                    for(int i=0;i<allCounties.length();i++){
                        JSONObject countyObject=allCounties.getJSONObject(i);
                        County county= new County();
                        county.setWeatherId(countyObject.getString("weather_id"));
                        county.setCountyName(countyObject.getString("name"));
                        county.setCityId(cityId);
                    }
                    return true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        return false;
    }
}