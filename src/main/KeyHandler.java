package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;

	//DEBUG
	boolean showDebugText = false;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	public void characterState(int code) {

		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_W) {
			if(gp.ui.slotRow != 0) {
				gp.ui.slotRow--;
				gp.playSE(6);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.slotCol != 0) {
				gp.ui.slotCol--;
				gp.playSE(6);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.slotRow != 3) {
				gp.ui.slotRow++;
				gp.playSE(6);
			};
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.slotCol != 4) {
				gp.ui.slotCol++;
				gp.playSE(6);
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
		}
	}
	public void Controlstate(int code) {

		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

		int maxcommandnum = 0;
		switch(gp.ui.subState) {
		case 0: maxcommandnum = 4; break;
		case 2: maxcommandnum = 0; break;
		}
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(6);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxcommandnum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(6);
			if(gp.ui.commandNum > maxcommandnum) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(6);
				}
			}
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playSE(6);
				}
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(6);
				}
			}
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
					gp.playSE(6);
				}
			}
		}
	}
	public void creditState(int code) {

		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;

			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(6);
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;

			if(gp.ui.commandNum > 0) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(6);
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.titleState = 0;
			}
		}
	}
	public void dialogueState(int code) {

		if(code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
		}
	}
	public void finishState(int code) {

		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(6);
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 1;
			}
			gp.playSE(6);
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				gp.restart();
			}
		}

	}
	public void gameOverState(int code) {

		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
			gp.playSE(6);
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(6);
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.restart();
			}
			else if(gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				System.exit(0);
			}
		}
	}
	public void gameOvertime(int code) {

		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
			gp.playSE(6);
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(6);
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.restart();
			}
			else if(gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				System.exit(0);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		//Title State
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}
		//PLAY STATE
		else if(gp.gameState == gp.playState) {
			playState(code);
		}
		//PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		//DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
		//Character State
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
		//OPTION STATE
		else if(gp.gameState == gp.optionsState) {
			optionsState(code);
		}
		//GAMEOVER STATE
		else if(gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		else if(gp.gameState == gp.gameOvertime) {
			gameOvertime(code);
		}
		else if(gp.gameState == gp.finishState) {
			finishState(code);
		}
		else if(gp.titleState == gp.ControlState) {
			optionTitle(code);
		}
		else if(gp.titleState == gp.CreditState) {
			creditState(code);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_U) {
			shotKeyPressed = false;
		}

	}
	@Override
	public void keyTyped(KeyEvent e) {

	}
	public void optionsState(int code) {

		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

		int maxCommandNum = 0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 5; break;
		case 3: maxCommandNum = 1; break;
		}
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(6);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(6);
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(6);
				}
				if(gp.ui.subState == 0) {
					if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
						gp.se.volumeScale--;
						gp.playSE(6);
					}
				}
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(6);
				}
				if(gp.ui.subState == 0) {
					if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
						gp.se.volumeScale++;
						gp.playSE(6);
					}
				}
			}
		}
	}
	public void optionTitle(int code) {

		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

		int maxcommandnum = 0;
		switch(gp.ui.subState) {
		case 0: maxcommandnum = 4; break;
		case 2: maxcommandnum = 0; break;
		}
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(6);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxcommandnum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(6);
			if(gp.ui.commandNum > maxcommandnum) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(6);
				}
			}
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playSE(6);
				}
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(6);
				}
			}
			if(gp.ui.subState == 0) {
				if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
					gp.playSE(6);
				}
			}
		}
	}

	public void pauseState(int code) {

		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.playState;
		}
	}
	public void playState(int code) {

		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
		}
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if(code == KeyEvent.VK_U) {
			shotKeyPressed = true;
		}
		if(code == KeyEvent.VK_U) {
			shotKeyPressed = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionsState;
		}


		//dEBUG
		if(code == KeyEvent.VK_T) {
			if(showDebugText == false) {
				showDebugText = true;
			}
			else if(showDebugText == true) {
				showDebugText = false;
			}
		}
		if(code == KeyEvent.VK_R) {
			switch(gp.currentmap) {
			case 0: gp.tileM.loadMap("/maps/final.txt", 0); break;
			}
		}

	}
	public void titleState(int code) {

		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(6);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 3;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(6);
			if(gp.ui.commandNum > 3) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.playMusic(0);
			}
			if(gp.ui.commandNum == 1) {
				gp.titleState = gp.ControlState;
			}
			if(gp.ui.commandNum == 2) {
				gp.titleState = gp.CreditState;
			}
			if(gp.ui.commandNum == 3) {
				gp.gameState = gp.playState;
				System.exit(0);
			}

		}
	}

}
