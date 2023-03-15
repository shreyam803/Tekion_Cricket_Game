package com.CricketGame.Cricket;

import Controller.MatchController;

public class CricketApplication {
	public static void main(String[] args) {
		try{
			MatchController matchController = new MatchController();
			matchController.gameLauncher();
		}
		catch(Exception e){
			System.out.println(e);
		}

	}

}
