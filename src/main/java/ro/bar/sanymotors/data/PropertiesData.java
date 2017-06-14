package ro.bar.sanymotors.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesData {
	private boolean loggedIn;

	@Value("${addressValue}")
	private String addressValue;

	@Value("${pageItems}")
	private String pageItems;

	private int lastPage;
	
	public String getAddressValue() {
		return addressValue;
	}

	public void setAddressValue(String addressValue) {
		this.addressValue = addressValue;
	}

	public String getPageItems() {
		return pageItems;
	}

	public void setPageItems(String pageItems) {
		this.pageItems = pageItems;
	}

	public boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.loggedIn = isLoggedIn;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
}
