package cc.xpbootcamp.warmup.cashier.model;


import java.math.BigDecimal;
import java.math.RoundingMode;

import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.COMMA;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.MULTIPLY;
import static cc.xpbootcamp.warmup.cashier.constant.SpecialCharacter.SPACE;

public class ProductItem {
	private String description;
	private BigDecimal price;
	private int quantity;

	public ProductItem(String description, double price, int quantity) {
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

    public String generateItemLine(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(price.setScale(2, RoundingMode.HALF_UP))
				.append(SPACE).append(MULTIPLY).append(SPACE)
				.append(quantity).append(COMMA)
				.append(totalAmount().setScale(2, RoundingMode.HALF_UP));
		return stringBuilder.toString();
	}

}