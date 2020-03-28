package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model;//
//  ModelProduct.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on March 25, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelProduct{

	@SerializedName("discount")
	private String discount;
	@SerializedName("favorite")
	private int favorite;
	@SerializedName("id")
	private String id;
	@SerializedName("image")
	private String image;
	@SerializedName("price_after_discount")
	private String priceAfterDiscount;
	@SerializedName("price_before_discount")
	private String priceBeforeDiscount;
	@SerializedName("rating")
	private String rating;
	@SerializedName("title")
	private String title;

	public void setDiscount(String discount){
		this.discount = discount;
	}
	public String getDiscount(){
		return this.discount;
	}
	public void setFavorite(int favorite){
		this.favorite = favorite;
	}
	public int getFavorite(){
		return this.favorite;
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
	public void setPriceAfterDiscount(String priceAfterDiscount){
		this.priceAfterDiscount = priceAfterDiscount;
	}
	public String getPriceAfterDiscount(){
		return this.priceAfterDiscount;
	}
	public void setPriceBeforeDiscount(String priceBeforeDiscount){
		this.priceBeforeDiscount = priceBeforeDiscount;
	}
	public String getPriceBeforeDiscount(){
		return this.priceBeforeDiscount;
	}
	public void setRating(String rating){
		this.rating = rating;
	}
	public String getRating(){
		return this.rating;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}


	public ModelProduct() {
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelProduct(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		discount = (String) jsonObject.opt("discount");
		id = (String) jsonObject.opt("id");
		image = (String) jsonObject.opt("image");
		priceAfterDiscount = (String) jsonObject.opt("price_after_discount");
		priceBeforeDiscount = (String) jsonObject.opt("price_before_discount");
		rating = (String) jsonObject.opt("rating");
		title = (String) jsonObject.opt("title");
		favorite = jsonObject.optInt("favorite");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("discount", discount);
			jsonObject.put("favorite", favorite);
			jsonObject.put("id", id);
			jsonObject.put("image", image);
			jsonObject.put("price_after_discount", priceAfterDiscount);
			jsonObject.put("price_before_discount", priceBeforeDiscount);
			jsonObject.put("rating", rating);
			jsonObject.put("title", title);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}