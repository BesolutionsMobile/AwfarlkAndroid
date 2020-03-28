package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model;//
//  ModelAllGategorey.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on March 24, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelAllGategorey{

	@SerializedName("catrgories")
	private ModelCatrgory[] catrgories;
	@SerializedName("status")
	private int status;

	public void setCatrgories(ModelCatrgory[] catrgories){
		this.catrgories = catrgories;
	}
	public ModelCatrgory[] getCatrgories(){
		return this.catrgories;
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
	public ModelAllGategorey(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		JSONArray catrgoriesJsonArray = jsonObject.optJSONArray("catrgories");
		if(catrgoriesJsonArray != null){
			ArrayList<ModelCatrgory> catrgoriesArrayList = new ArrayList<>();
			for (int i = 0; i < catrgoriesJsonArray.length(); i++) {
				JSONObject catrgoriesObject = catrgoriesJsonArray.optJSONObject(i);
				catrgoriesArrayList.add(new ModelCatrgory(catrgoriesObject));
			}
			catrgories = (ModelCatrgory[]) catrgoriesArrayList.toArray();
		}
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", status);
			if(catrgories != null && catrgories.length > 0){
				JSONArray catrgoriesJsonArray = new JSONArray();
				for(ModelCatrgory catrgoriesElement : catrgories){
					catrgoriesJsonArray.put(catrgoriesElement.toJsonObject());
				}
				jsonObject.put("catrgories", catrgoriesJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}