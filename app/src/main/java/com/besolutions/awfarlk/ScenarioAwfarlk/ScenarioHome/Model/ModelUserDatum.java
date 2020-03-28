package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Model;//
//  ModelUserDatum.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on March 24, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class ModelUserDatum{

	@SerializedName("address")
	private String address;
	@SerializedName("city_id")
	private String cityId;
	@SerializedName("created")
	private String created;
	@SerializedName("created_by")
	private Object createdBy;
	@SerializedName("date")
	private String date;
	@SerializedName("deleted")
	private String deleted;
	@SerializedName("gender")
	private Object gender;
	@SerializedName("id")
	private String id;
	@SerializedName("job_type")
	private String jobType;
	@SerializedName("last_updated_by")
	private String lastUpdatedBy;
	@SerializedName("mail")
	private String mail;
	@SerializedName("name")
	private String name;
	@SerializedName("password")
	private String password;
	@SerializedName("phone")
	private String phone;
	@SerializedName("photo")
	private String photo;
	@SerializedName("status")
	private String status;
	@SerializedName("user_language")
	private String userLanguage;
	@SerializedName("verified")
	private String verified;

	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setCityId(String cityId){
		this.cityId = cityId;
	}
	public String getCityId(){
		return this.cityId;
	}
	public void setCreated(String created){
		this.created = created;
	}
	public String getCreated(){
		return this.created;
	}
	public void setCreatedBy(Object createdBy){
		this.createdBy = createdBy;
	}
	public Object getCreatedBy(){
		return this.createdBy;
	}
	public void setDate(String date){
		this.date = date;
	}
	public String getDate(){
		return this.date;
	}
	public void setDeleted(String deleted){
		this.deleted = deleted;
	}
	public String getDeleted(){
		return this.deleted;
	}
	public void setGender(Object gender){
		this.gender = gender;
	}
	public Object getGender(){
		return this.gender;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setJobType(String jobType){
		this.jobType = jobType;
	}
	public String getJobType(){
		return this.jobType;
	}
	public void setLastUpdatedBy(String lastUpdatedBy){
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getLastUpdatedBy(){
		return this.lastUpdatedBy;
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
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setPhoto(String photo){
		this.photo = photo;
	}
	public String getPhoto(){
		return this.photo;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}
	public void setUserLanguage(String userLanguage){
		this.userLanguage = userLanguage;
	}
	public String getUserLanguage(){
		return this.userLanguage;
	}
	public void setVerified(String verified){
		this.verified = verified;
	}
	public String getVerified(){
		return this.verified;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public ModelUserDatum(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		address = (String) jsonObject.opt("address");
		cityId = (String) jsonObject.opt("city_id");
		created = (String) jsonObject.opt("created");
		date = (String) jsonObject.opt("date");
		deleted = (String) jsonObject.opt("deleted");
		id = (String) jsonObject.opt("id");
		jobType = (String) jsonObject.opt("job_type");
		lastUpdatedBy = (String) jsonObject.opt("last_updated_by");
		mail = (String) jsonObject.opt("mail");
		name = (String) jsonObject.opt("name");
		password = (String) jsonObject.opt("password");
		phone = (String) jsonObject.opt("phone");
		photo = (String) jsonObject.opt("photo");
		status = (String) jsonObject.opt("status");
		userLanguage = (String) jsonObject.opt("user_language");
		verified = (String) jsonObject.opt("verified");
		createdBy = (String)jsonObject.opt("created_by");
		gender = (String)jsonObject.opt("gender");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("address", address);
			jsonObject.put("city_id", cityId);
			jsonObject.put("created", created);
			jsonObject.put("created_by", createdBy);
			jsonObject.put("date", date);
			jsonObject.put("deleted", deleted);
			jsonObject.put("gender", gender);
			jsonObject.put("id", id);
			jsonObject.put("job_type", jobType);
			jsonObject.put("last_updated_by", lastUpdatedBy);
			jsonObject.put("mail", mail);
			jsonObject.put("name", name);
			jsonObject.put("password", password);
			jsonObject.put("phone", phone);
			jsonObject.put("photo", photo);
			jsonObject.put("status", status);
			jsonObject.put("user_language", userLanguage);
			jsonObject.put("verified", verified);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}