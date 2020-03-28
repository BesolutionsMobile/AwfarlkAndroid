package com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model;//
//  ProductComment.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on March 26, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ProductComment{

	@SerializedName("comment")
	private String comment;
	@SerializedName("date")
	private String date;
	@SerializedName("id")
	private String id;
	@SerializedName("user_name")
	private String userName;
	@SerializedName("user_photo")
	private String userPhoto;

	public void setComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		return this.comment;
	}
	public void setDate(String date){
		this.date = date;
	}
	public String getDate(){
		return this.date;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return this.userName;
	}
	public void setUserPhoto(String userPhoto){
		this.userPhoto = userPhoto;
	}
	public String getUserPhoto(){
		return this.userPhoto;
	}

	public ProductComment() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ProductComment(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		comment = (String) jsonObject.opt("comment");
		date = (String) jsonObject.opt("date");
		id = (String) jsonObject.opt("id");
		userName = (String) jsonObject.opt("user_name");
		userPhoto = (String) jsonObject.opt("user_photo");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("comment", comment);
			jsonObject.put("date", date);
			jsonObject.put("id", id);
			jsonObject.put("user_name", userName);
			jsonObject.put("user_photo", userPhoto);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}