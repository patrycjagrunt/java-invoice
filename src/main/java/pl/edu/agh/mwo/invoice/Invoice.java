package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private final int invoiceNumber = Math.abs(new Random().nextInt());
	private Map<Product, Integer> products = new HashMap<Product, Integer>();

    public void addProduct(Product product) {
    	addProduct(product,1);
    }
 
    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        Integer addedProducts = products.get(product);
        if (addedProducts != null) {
            quantity = addedProducts + quantity;
        }
        products.put(product, quantity);
    }
    
    public Map<Product, Integer> getProduct() {
        return products;
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

	public int getNumber() {
		return invoiceNumber;
	}

	public int getNumberOfProduct() {
		return  products.size();
	}

	public boolean PrintInvoice() {
		
		System.out.println("Nr. faktury: " + getNumber());
		System.out.println("Nazwa produktu,   Cena,    Ilość");
		
		Map<Product, Integer> products = new HashMap<Product, Integer>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
			System.out.printf(entry.getKey().getName(), entry.getValue(), entry.getKey().getPrice());
        }

        System.out.println("Liczba pozycji: " + products.size());
        
		return true;
	}
	
	
	
}
