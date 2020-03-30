package Basket;

public class BDTO {
	private String id;
	private String name;
	private String item;
	private String quantity;
	private String price;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String[] getArray() {
		String[] returnData = new String[5];
		returnData[0]=this.id;
		returnData[1]=this.name;
		returnData[2]=this.item;
		returnData[3]=this.quantity;
		returnData[4]=this.price;
		
		return returnData;
	}
	
}
