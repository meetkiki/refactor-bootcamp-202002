package cc.xpbootcamp.warmup.cashier.model;


import java.math.BigDecimal;

public class LineItem {
	private String description;
	private BigDecimal price;
	private int quantity;

	public LineItem(String description, double price, int quantity) {
		this.description = description;
		this.price = new BigDecimal(Double.toString(price));
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public BigDecimal totalAmount() {
        return price.multiply(new BigDecimal(quantity));
    }
}