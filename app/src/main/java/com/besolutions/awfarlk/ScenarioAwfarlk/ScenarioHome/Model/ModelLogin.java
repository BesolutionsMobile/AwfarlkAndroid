package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Model;//
//  ModelLogin.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on March 24, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelLogin{

	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;
	@SerializedName("user_data")
	private ModelUserDatum userData;

	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	public void setUserData(ModelUserDatum userData){
		this.userData = userData;
	}
	public ModelUserDatum getUserData(){
		return this.userData;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelLogin(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = (String) jsonObject.opt("message");
		status = jsonObject.optInt("status");
		userData = new ModelUserDatum(jsonObject.optJSONObject("user_data"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("message", message);
			jsonObject.put("status", status);
			jsonObject.put("userData", userData.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}