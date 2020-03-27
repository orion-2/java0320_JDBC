package Basket;

public class BasketDTO {
	private String no;
	private String id;
	private String name;
	private String item;
	private String quantity;
	private String price;
	private String total;
	
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
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String[] getArray() {
		String[] returnData = new String[4];
		returnData[0]=this.no;
		returnData[1]=this.id;
		returnData[2]=this.name;
		returnData[3]=this.item;
		returnData[4]=this.quantity;
		returnData[5]=this.price;
//		returnData[6]=this.total;
		
		return returnData;
	}
	
}
