package ro.bar.sanymotors.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesData {
	private boolean loggedIn;

	@Value("${addressValue}")
	private String addressValue;

	@Value("${admin.pageItems}")
	private String adminPageItems;
	 
	@Value("${client.pageItems}")
	private String clientPageItems;

	private int lastPage;
	private int lastMotorcyclePage;
	private int lastPiecesPage;
	private int lastRentPage;
	
	public String getAddressValue() {
		return addressValue;
	}

	public void setAddressValue(String addressValue) {
		this.addressValue = addressValue;
	}

	public String getAdminPageItems() {
		return adminPageItems;
	}

	public void setAdminPageItems(String adminPageItems) {
		this.adminPageItems = adminPageItems;
	}

	public String getClientPageItems() {
		return clientPageItems;
	}

	public void setClientPageItems(String clientPageItems) {
		this.clientPageItems = clientPageItems;
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

	public int getLastMotorcyclePage() {
		return lastMotorcyclePage;
	}

	public void setLastMotorcyclePage(int lastMotorcyclePage) {
		this.lastMotorcyclePage = lastMotorcyclePage;
	}

	public int getLastPiecesPage() {
		return lastPiecesPage;
	}

	public void setLastPiecesPage(int lastPiecesPage) {
		this.lastPiecesPage = lastPiecesPage;
	}

	public int getLastRentPage() {
		return lastRentPage;
	}

	public void setLastRentPage(int lastRentPage) {
		this.lastRentPage = lastRentPage;
	}
	
}
