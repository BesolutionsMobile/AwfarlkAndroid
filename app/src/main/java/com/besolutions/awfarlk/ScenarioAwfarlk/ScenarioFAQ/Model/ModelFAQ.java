package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioFAQ.Model;//
//  ModelFAQ.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on March 26, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelFAQ{

	@SerializedName("questions")
	private ModelQuestion[] questions;
	@SerializedName("status")
	private int status;

	public void setQuestions(ModelQuestion[] questions){
		this.questions = questions;
	}
	public ModelQuestion[] getQuestions(){
		return this.questions;
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
	public ModelFAQ(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		status = jsonObject.optInt("status");
		JSONArray questionsJsonArray = jsonObject.optJSONArray("questions");
		if(questionsJsonArray != null){
			ArrayList<ModelQuestion> questionsArrayList = new ArrayList<>();
			for (int i = 0; i < questionsJsonArray.length(); i++) {
				JSONObject questionsObject = questionsJsonArray.optJSONObject(i);
				questionsArrayList.add(new ModelQuestion(questionsObject));
			}
			questions = (ModelQuestion[]) questionsArrayList.toArray();
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
			if(questions != null && questions.length > 0){
				JSONArray questionsJsonArray = new JSONArray();
				for(ModelQuestion questionsElement : questions){
					questionsJsonArray.put(questionsElement.toJsonObject());
				}
				jsonObject.put("questions", questionsJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}