package com.briup.app.estore.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Double cost;

    private Date orderdate;

    private Customer cutormer;

    private List<Orderline> orderlines;
    
	public List<Orderline> getOrderlines() {
		if( orderlines == null) {
			orderlines=new ArrayList<>();
		}
		return orderlines;
	}

	
	public Order(Double cost, Date orderdate, Customer cutormer, List<Orderline> orderlines) {
		super();
		this.cost = cost;
		this.orderdate = orderdate;
		this.cutormer = cutormer;
		this.orderlines = orderlines;
	}


	public void setOrderlines(List<Orderline> orderlines) {
		this.orderlines = orderlines;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Integer id, Double cost, Date orderdate, Customer cutormer) {
		super();
		this.id = id;
		this.cost = cost;
		this.orderdate = orderdate;
		this.cutormer = cutormer;
	}

	public Order(Double cost, Date orderdate, Customer cutormer) {
		super();
		this.cost = cost;
		this.orderdate = orderdate;
		this.cutormer = cutormer;
	}

	public Order(Double cost, Date orderdate) {
		super();
		this.cost = cost;
		this.orderdate = orderdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public Customer getCutormer() {
		return cutormer;
	}

	public void setCutormer(Customer cutormer) {
		this.cutormer = cutormer;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", cost=" + cost + ", orderdate=" + orderdate + ", cutormer=" + cutormer
				+ ", orderlines=" + orderlines + "]";
	}
    
    
}