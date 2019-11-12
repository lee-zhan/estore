package com.briup.app.estore.bean;

import java.io.Serializable;

public class Orderline implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer num;

    private Order order;

    private Book book;

	public Orderline() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orderline(Integer id, Integer num, Order order, Book book) {
		super();
		this.id = id;
		this.num = num;
		this.order = order;
		this.book = book;
	}

	public Orderline(Integer num, Order order, Book book) {
		super();
		this.num = num;
		this.order = order;
		this.book = book;
	}

	public Orderline(Integer num) {
		super();
		this.num = num;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Orderline [id=" + id + ", num=" + num + ", book=" + book + "]";
	}
    
    

}