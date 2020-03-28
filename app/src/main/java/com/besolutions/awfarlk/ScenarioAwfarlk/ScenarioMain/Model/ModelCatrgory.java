package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model;//
//  ModelCatrgory.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on March 24, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelCatrgory{

	@SerializedName("adv")
	private String adv;
	@SerializedName("id")
	private String id;
	@SerializedName("image")
	private String image;
	@SerializedName("name")
	private String name;

	public void setAdv(String adv){
		this.adv = adv;
	}
	public String getAdv(){
		return this.adv;
	}
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
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}


	public ModelCatrgory() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelCatrgory(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		adv = (String) jsonObject.opt("adv");
		id = (String) jsonObject.opt("id");
		image = (String) jsonObject.opt("image");
		name = (String) jsonObject.opt("name");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("adv", adv);
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("name", name);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}