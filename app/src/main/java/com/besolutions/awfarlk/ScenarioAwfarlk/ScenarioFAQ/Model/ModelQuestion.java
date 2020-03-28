package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioFAQ.Model;//
//  ModelQuestion.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on March 26, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelQuestion{

	@SerializedName("description")
	private String description;
	@SerializedName("id")
	private String id;
	@SerializedName("title")
	private String title;

	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}


	public ModelQuestion() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelQuestion(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		description = (String) jsonObject.opt("description");
		id = (String) jsonObject.opt("id");
		title = (String) jsonObject.opt("title");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("description", description);
			jsonObject.put("id", id);
			jsonObject.put("title", title);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}