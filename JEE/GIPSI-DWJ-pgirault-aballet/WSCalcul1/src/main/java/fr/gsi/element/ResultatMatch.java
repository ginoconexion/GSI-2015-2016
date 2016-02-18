package fr.gsi.element;

public class ResultatMatch {
	private int score1;
	private int score2;
	
	public ResultatMatch(int score1, int score2) {
		super();
		this.score1 = score1;
		this.score2 = score2;
	}

	public int getScore1() {
		return score1;
	}

	public int getScore2() {
		return score2;
	}
	
	public Result getResult(){
		if (score1>score2)
			return Result.VICTOIRE1;
		else if (score2>score1)
			return Result.VICTOIRE2;
		else
			return Result.NUL;
	}
	
	public boolean equals(ResultatMatch res){
		if (this.score1 == res.getScore1() && this.score2 == res.getScore2())
			return true;
		else 
			return false;
	}
}
