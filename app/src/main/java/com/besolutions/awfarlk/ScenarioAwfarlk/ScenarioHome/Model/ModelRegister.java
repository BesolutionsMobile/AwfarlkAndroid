package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Model;//
//  ModelRegister.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on March 24, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelRegister{

	@SerializedName("id_user")
	private String idUser;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}
	public String getIdUser(){
		return this.idUser;
	}
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

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelRegister(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		idUser = (String) jsonObject.opt("id_user");
		message = (String) jsonObject.opt("message");
		status = jsonObject.optInt("status");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id_user", idUser);
			jsonObject.put("message", message);
			jsonObject.put("status", status);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}