package com.example.model;

public class Transfer {

	private int id;
	private Double amount;
	private String fromAcc;
	private String toAcc;

	public Transfer(Double amount, String fromAcc, String toAcc) {
		super();
		this.amount = amount;
		this.fromAcc = fromAcc;
		this.toAcc = toAcc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getFromAcc() {
		return fromAcc;
	}

	public void setFromAcc(String fromAcc) {
		this.fromAcc = fromAcc;
	}

	public String getToAcc() {
		return toAcc;
	}

	public void setToAcc(String toAcc) {
		this.toAcc = toAcc;
	}

	@Override
	public String toString() {
		return "Transfer [id=" + id + ", amount=" + amount + ", fromAcc=" + fromAcc + ", toAcc=" + toAcc + "]";
	}

	public Transfer() {
		super();
	}

}
