package fr.emac.gipsi.gsi.element;

public class FireTower extends AbstractTower {
	
	private int costUpgrade;
	private String colorName;
	private int range;
	private String type;
	private int power;
	

	public FireTower() {
		this.type = "feu";
		this.costUpgrade = 10;
		this.colorName = "DarkRed";
		this.power = 3;
		this.range = 3;
		
	}

	public FireTower(Position pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCostUpgrade() {
		// TODO Auto-generated method stub
		return costUpgrade;
	}

	@Override
	public String getColorName() {
		// TODO Auto-generated method stub
		return colorName;
	}

	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return range;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public int getPower() {
		// TODO Auto-generated method stub
		return power;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		this.power += 5;
	}

}
