package main.menu.shop;

import java.util.Set;

import main.infinite_map.Background;
import main.utilities.Skin;

public class Shop {
	private Integer coins;
	private Set<PurchaseStatus<Skin>> skins;
	private Set<PurchaseStatus<Background>> sceneries;
	
	public Shop(Integer coins, Set<PurchaseStatus<Skin>> skins, Set<PurchaseStatus<Background>> sceneries) {
		this.coins = coins;
		this.skins = skins;
		this.sceneries = sceneries;
	}
	
}
