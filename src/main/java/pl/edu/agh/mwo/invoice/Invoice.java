package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    
	private Map<Product,Integer> products = new LinkedHashMap<>();

	public void addProduct(Product product) {
        this.products.put(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
    	this.products.put(product, quantity);
    	 if (quantity==null || quantity<=0) {
         	throw new IllegalArgumentException("You cannot create products with null or zero quantity.");
         }
    }

    public BigDecimal getSubtotal() {
    	BigDecimal sum = BigDecimal.ZERO;
    	for (Product product: this.products.keySet()) {
    		Integer quantity = this.products.get(product);
    		sum = sum.add(product.getPrice().multiply(new BigDecimal(quantity)));
    	} 
    	
    	return sum;
    }

    public BigDecimal getTax() {
    	BigDecimal tax = BigDecimal.ZERO;
    	tax = getTotal().subtract(getSubtotal());
    	return tax;
    }

    public BigDecimal getTotal() {
    	BigDecimal sum = BigDecimal.ZERO;
    	for (Product product: this.products.keySet()) {
    		Integer quantity = this.products.get(product);
    		sum = sum.add(product.getPriceWithTax().multiply(new BigDecimal(quantity)));
    	} 
    	
    	return sum;
    }
}
