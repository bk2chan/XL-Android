package com.example.twitterquery;

public class SearchOption {

	
	public int pageNumber = 0;
	public int results = 0;
	public String search = "";
	
	public SearchOption(String _search, int _pageNum, int _results) {
		search = _search;
		pageNumber = _pageNum;
		results = _results;
	}
	
}
