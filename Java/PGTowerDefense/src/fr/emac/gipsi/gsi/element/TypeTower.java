/**
 * 
 */
package fr.emac.gipsi.gsi.element;

/**
 * @author truptil
 *
 */
public enum TypeTower {

	Feu("feu", FireTower.class),
	Glace("Glace", GlaceTower.class);
	
	private String name;
	private Class<? extends AbstractTower> type;

	/**
	 * @param name
	 */
	private TypeTower(String name,Class<? extends AbstractTower> type) {
		this.name = name;
		this.type=type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public Class<? extends AbstractTower> getType() {
		return type;
	}
	
	
}
