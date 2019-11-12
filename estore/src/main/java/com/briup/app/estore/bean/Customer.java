package com.briup.app.estore.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private String password;

    private String zip;

    private String address;

    private String telephone;

    private String email;
    
    private List<Order> orders;
    
    

    public Customer(String name, String password, String zip, String address, String telephone, String email) {
		super();
		this.name = name;
		this.password = password;
		this.zip = zip;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
	}

	public Customer(String name, String password, String zip, String address, String telephone, String email,
			List<Order> orders) {
		super();
		this.name = name;
		this.password = password;
		this.zip = zip;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.orders = orders;
	}

	public Customer(Integer id, String name, String password, String zip, String address, String telephone,
			String email, List<Order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.zip = zip;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.orders = orders;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Order> getOrder() {
    	if(orders == null) {
    		orders = new ArrayList<>();
    	}
		return orders;
	}

	public void setOrder(List<Order> order) {
		this.orders = order;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", password=" + password + ", zip=" + zip + ", address="
				+ address + ", telephone=" + telephone + ", email=" + email + "]";
	}
    
}