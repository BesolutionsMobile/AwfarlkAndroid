package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioPersonalInfo.Model;//
//  ModelUser.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on March 26, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelUser{

	@SerializedName("id")
	private String id;
	@SerializedName("image")
	private String image;
	@SerializedName("mail")
	private String mail;
	@SerializedName("name")
	private String name;
	@SerializedName("phone")
	private String phone;

	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
	}
	public void setMail(String mail){
		this.mail = mail;
	}
	public String getMail(){
		return this.mail;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelUser(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		id = (String) jsonObject.opt("id");
		image = (String) jsonObject.opt("image");
		mail = (String) jsonObject.opt("mail");
		name = (String) jsonObject.opt("name");
		phone = (String) jsonObject.opt("phone");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("mail", mail);
			jsonObject.put("name", name);
			jsonObject.put("phone", phone);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}