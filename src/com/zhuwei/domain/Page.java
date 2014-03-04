package com.zhuwei.domain;

import java.util.List;

public class Page {
	
	private int totalpage;//total pages
	private int pagesize = 3;//a page size
	
	private int totalrecord;// total record books
	private int pagenum;
	
	private List<Book> list; //page data
	
	private int startpage;//
	private int endpage;
	
	private int startindex;
	
	public Page(int pagenum, int totalrecord) {
		this.pagenum = pagenum;
		this.totalrecord = totalrecord;
		
		this.totalpage = (this.totalrecord + this.pagesize - 1)/this.pagesize;
		this.startindex = (this.pagenum -1 )*this.pagesize;
		
		if(this.totalpage<=10){
			this.startpage = 1;
			this.endpage = this.totalpage;
		}else {
			this.startpage = this.pagenum - 4;
			this.endpage = this.pagenum + 5;
			
			if(this.startpage < 1){
				this.startpage = 1;
				this.endpage = 10;
			}
			
			if(this.endpage > this.totalpage){
				this.endpage = this.totalpage;
				this.startpage = this.totalpage - 9;
			}
		}
				
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public List<Book> getList() {
		return list;
	}

	public void setList(List<Book> list) {
		this.list = list;
	}

	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	public int getStartindex() {
		return startindex;
	}

	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}
		
	
}
