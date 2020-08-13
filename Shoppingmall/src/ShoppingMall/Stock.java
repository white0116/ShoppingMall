package ShoppingMall;

public class Stock {
	protected String inventoryname; // 카테고리 이름
	protected String fullname; // 아이템 이름
	protected int price; // 가격
	protected int stocknumber; // 남은 재고
	
	public Stock(String fullname, int price, int stocknumber) {
		this.fullname = fullname;
		this.price = price;
		this.stocknumber = stocknumber;
	}

	public String getInventoryname() {
		return inventoryname;
	}

	public void setInventoryname(String inventoryname) {
		this.inventoryname = inventoryname;
	}

	public String getFullname() {
		return fullname;
	}

	public int getPrice() {
		return price;
	}

	public int getStocknumber() {
		return stocknumber;
	}

	@Override
	public String toString() {
		return "상품명 : " + fullname + "가격 : " + price + "재고 : " + stocknumber;
	}
}