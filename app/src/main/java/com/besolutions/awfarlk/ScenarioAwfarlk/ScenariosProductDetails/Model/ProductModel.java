package com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model;//


import com.google.gson.annotations.SerializedName;


public class ProductModel{

	@SerializedName("album")
	private String[] album;
	@SerializedName("colors")
	private String[] colors;
	@SerializedName("comments")
	private ProductComment[] comments;
	@SerializedName("description")
	private String description;
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
	@SerializedName("quantity")
	private int quantity;
	@SerializedName("rating")
	private int rating;
	@SerializedName("slug")
	private String slug;
	@SerializedName("status")
	private int status;
	@SerializedName("title")
	private String title;

	public void setAlbum(String[] album){
		this.album = album;
	}
	public String[] getAlbum(){
		return this.album;
	}
	public void setColors(String[] colors){
		this.colors = colors;
	}
	public String[] getColors(){
		return this.colors;
	}
	public void setComments(ProductComment[] comments){
		this.comments = comments;
	}
	public ProductComment[] getComments(){
		return this.comments;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
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
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	public int getQuantity(){
		return this.quantity;
	}
	public void setRating(int rating){
		this.rating = rating;
	}
	public int getRating(){
		return this.rating;
	}
	public void setSlug(String slug){
		this.slug = slug;
	}
	public String getSlug(){
		return this.slug;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}


}