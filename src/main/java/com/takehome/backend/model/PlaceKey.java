package com.takehome.backend.model;

public class PlaceKey {

    private String query_id;
	private String placekey;
	private String error;

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getQuery_id() {
		return this.query_id;
	}

	public void setQuery_id(String query_id) {
		this.query_id = query_id;
	}

	public String getPlacekey() {
		return this.placekey;
	}

	public void setPlacekey(String placekey) {
		this.placekey = placekey;
	}

   
}
