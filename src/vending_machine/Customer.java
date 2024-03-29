package vending_machine;

public class Customer {

	/**
	 * 고객이 가진 돈
	 */
	// 멤버변수 wallet 생성
	private int wallet;
	
	/**
	 * 고객이 가진 상품의 수량
	 */
//	int stock;
	private Product[] productArray;
	
	public int getWallet() {
		return this.wallet;
	}
	
	public Product[] getProductArray() {
		return this.productArray;
	}
	
	/**
	 * 생성자
	 */
//	public Customer(int wallet, int stock) {
	public Customer(int wallet) {
		this.wallet = wallet;
//		this.stock = stock;
		this.productArray = new Product[3];
	}
	
	/**
	 * 지출한다.
	 */
	public void pay(int price) {
		if (this.wallet - price <= 0) {
			return; // 메소드 즉시 종료
		}
		this.wallet -= price;
	}
	
	/**
	 * 환불 받는다.
	 * @param money 환불 받은 금액
	 */
	public void addMoney(int money) {
		this.wallet += money;
	}
	
	/**
	 * 상품이 하나 증가한다.
	 */
	public void addStock(String name, int price, int productCount) {
		
		// 고객이 방금 구매한 제품이 고객의 제품목록(this.productArray)에 있는지 확인한다.
		// 있다면, productCount만큼 수량만 증가시킨다.
		// 없다면, 비어있는 인덱스를 찾아서 새롭게 할당해 준다.
		Product product = this.getProductByName(name);
		
		if (product != null) {
			int quantity = product.getQuantity();
			quantity += productCount;
			product.setQuantity(quantity);
		}
		else {
			int nullIndex = this.getNullIndex();
			if (nullIndex >= 0) {
				this.productArray[nullIndex] = new Product();
				this.productArray[nullIndex].setName(name);
				this.productArray[nullIndex].setPrice(price);
				this.productArray[nullIndex].setQuantity(productCount);
			}
			
		}
	}
	
	private Product getProductByName(String name) {
		
		for (Product product : this.productArray) {
			if (product != null && product.getName().equals(name)) {
				return product;
			}
		}
		
		return null;
	}
	
	private int getNullIndex() {
		for (int i = 0; i < this.productArray.length; i++) {
			if (this.productArray[i] == null) {
				return i;
			}
		}
		
		return -1;
	}
	
	public void printProducts() {
		System.out.println("고객의 잔액: " + this.wallet);
		for (Product product : this.productArray) {
			if (product != null) {
				System.out.println("고객의 상품 수량: " + product.getQuantity());
				System.out.println("고객의 상품 이름: " + product.getName());				
			}
		}
	}
	
}
