package fr.emac.gipsi.gsi.element;

public class GlaceTower extends AbstractTower {

	private int costUpgrade;
	private String colorName;
	private int range;
	private String type;
	private int power;
	

	public GlaceTower() {
		this.type = "Glace";
		this.costUpgrade = 15;
		this.colorName = "LightSkyBlue";
		this.power = 1;
		this.range = 4;
		
	}

	public GlaceTower(Position pos) {
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
