package progprak.gruppe53.levelEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import progprak.gruppe53.game.EnemyGhost;
import progprak.gruppe53.game.FireballTrap;
import progprak.gruppe53.game.GamePanel;
import progprak.gruppe53.game.Goal;
import progprak.gruppe53.game.GroundTrap;
import progprak.gruppe53.game.ImageLoader;
import progprak.gruppe53.game.Jacket;
import progprak.gruppe53.game.LevelSwitch;
import progprak.gruppe53.game.PortalEntrance;
import progprak.gruppe53.game.Sprite;
import progprak.gruppe53.game.Sword;
import progprak.gruppe53.game.Wall;

public class LevelEditor extends JFrame implements ActionListener,
		MouseListener {
	private static final long serialVersionUID = 1;

	private static final String WINDOW_NAME = "LevelEditor";
	private static final int DEFAULT_WINDOW_WIDTH = 900;
	private static final int DEFAULT_WINDOW_HEIGHT = 830;
	private static final int GAMEPANEL_WIDTH = 800;
	private static final int GAMEPANEL_HEIGHT = 640;
	private static final int SEPARATOR_WIDTH = 20;
	private static final int ATTRIBUTEBAR_HEIGHT = 30;
	private static final String OBJECT_WALL = "wall";
	private static final String ENEMY_GHOST = "enemyGhost";
	private static final String TRAP_SPEARS = "trapSpears";
	private static final String TRAP_FIREBALL = "trapFireball";
	private static final String OBJECT_PORTAL = "portal";
	private static final String OBJECT_LEVELSWITCH = "levelSwitch";
	private static final String OBJECT_GOAL = "goal";
	private static final String OBJECT_SPAWN = "spawn";
	private static final String OBJECT_SWORD = "sword";
	private static final String OBJECT_JACKET = "jacket";
	private static final String SAVE = "save";
	
	private String labelString1 = "X: ";
	private String labelString2 = "Y: ";
	private String labelString3 = "XSpeed: ";
	private String labelString4 = "YSpeed: ";
	private String labelString5 = "XSpawn: ";
	private String labelString6 = "YSpawn: ";
	private String labelString7 = "TeleportLocation: ";
	private String labelString8 = "NextLevelPath: ";
	
	public static void main(String[] args) {
		new LevelEditor();

	}
	
	private JPanel tools, level, separator, attributeBar;
	private JTextField attribute1, attribute2, attribute3, attribute4, attribute5, attribute6;
	private JLabel attributeLabel1, attributeLabel2, attributeLabel3, attributeLabel4, attributeLabel5, attributeLabel6;

	private Vector<Sprite> sprites;
	private String currentSprite = "";
	private String fileName = "";

	public LevelEditor() {
		super(WINDOW_NAME);
		setupEditor();
		setVisible(true);
	}

	private void setupEditor() {
		sprites = new Vector<Sprite>();
		setTitle(WINDOW_NAME);
		setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		getContentPane().setLayout(new BorderLayout());
		level = new GamePanel();
		separator = new JPanel();
		tools = new JPanel();
		attributeBar = new JPanel();
		attribute1 = new JTextField(10);
		attribute2 = new JTextField(10);
		attribute3 = new JTextField(10);
		attribute4 = new JTextField(10);
		attribute5 = new JTextField(10);
		attribute6 = new JTextField(10);
		
		
		separator.setPreferredSize(new Dimension(SEPARATOR_WIDTH,GAMEPANEL_HEIGHT));
		tools.setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH - GAMEPANEL_WIDTH - SEPARATOR_WIDTH, DEFAULT_WINDOW_HEIGHT));
		attributeBar.setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH, ATTRIBUTEBAR_HEIGHT));
		
		separator.setBackground(Color.YELLOW);
		tools.setBackground(Color.BLUE);
		attributeBar.setBackground(Color.RED);

		setupTools();

		level.addMouseListener(this);

		JPanel editorPanel = new JPanel();
		editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.X_AXIS));
		editorPanel.add(level);
		editorPanel.add(separator);
		editorPanel.add(tools);
		add(editorPanel,BorderLayout.CENTER);
		add(attributeBar,BorderLayout.SOUTH);
		attributeBar.add(attribute1);
		attributeBar.add(attribute2);
		attributeBar.add(attribute3);
		attributeBar.add(attribute4);
		attributeBar.add(attribute5);
		attributeBar.add(attribute6);
		
		
		

	}

	private void setupTools() {
		JButton wall = new JButton(new ImageIcon(ImageLoader.loadImage("images/wall.png")));
		wall.setActionCommand(OBJECT_WALL);
		wall.addActionListener(this);
		tools.add(wall);
		JButton enemyGhost = new JButton(new ImageIcon(ImageLoader.loadImage("images/ghost1.png")));
		enemyGhost.setActionCommand(ENEMY_GHOST);
		enemyGhost.addActionListener(this);
		tools.add(enemyGhost);
		JButton trapSpears = new JButton(new ImageIcon(ImageLoader.loadImage("images/groundTrap1.png")));
		trapSpears.setActionCommand(TRAP_SPEARS);
		trapSpears.addActionListener(this);
		tools.add(trapSpears);
		JButton trapFireball = new JButton(new ImageIcon(ImageLoader.loadImage("images/FireballRedEditor.png")));
		trapFireball.setActionCommand(TRAP_FIREBALL);
		trapFireball.addActionListener(this);
		tools.add(trapFireball);
		JButton portal = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		portal.setActionCommand(OBJECT_PORTAL);
		portal.addActionListener(this);
		tools.add(portal);
		JButton levelSwitch = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance2.png")));
		levelSwitch.setActionCommand(OBJECT_LEVELSWITCH);
		levelSwitch.addActionListener(this);
		tools.add(levelSwitch);
		JButton goal = new JButton(new ImageIcon(ImageLoader.loadImage("images/exit.png")));
		goal.setActionCommand(OBJECT_GOAL);
		goal.addActionListener(this);
		tools.add(goal);
		JButton spawn = new JButton(new ImageIcon(ImageLoader.loadImage("images/held.png")));
		spawn.setActionCommand(OBJECT_SPAWN);
		spawn.addActionListener(this);
		tools.add(spawn);
		JButton save = new JButton("Save");
		save.setPreferredSize(new Dimension(66,45));
		save.setActionCommand(SAVE);
		save.addActionListener(this);
		tools.add(save);
		JButton sword = new JButton(new ImageIcon(ImageLoader.loadImage("images/sword.png")));
		sword.setPreferredSize(new Dimension(66,45));
		sword.setActionCommand(OBJECT_SWORD);
		sword.addActionListener(this);
		tools.add(sword);
		JButton jacket = new JButton(new ImageIcon(ImageLoader.loadImage("images/hero.png")));
		jacket.setActionCommand(OBJECT_JACKET);
		jacket.addActionListener(this);
		tools.add(jacket);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image;
		Cursor c;
		if (actionCommand == OBJECT_WALL) {
			image = ImageLoader.loadImage("images/wall.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "wall");
			level.setCursor(c);
			currentSprite = actionCommand;
			
		}
		else if (actionCommand == ENEMY_GHOST) {
			image = ImageLoader.loadImage("images/ghost1.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "enemyGhost");
			level.setCursor(c);
			currentSprite = actionCommand;
			
		}
		else if (actionCommand == TRAP_SPEARS) {
			image = ImageLoader.loadImage("images/groundTrap1.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "trapSpears");
			level.setCursor(c);
			currentSprite = actionCommand;
			
		}
		else if (actionCommand == TRAP_FIREBALL) {
			image = ImageLoader.loadImage("images/FireballRedEditor2.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0),"trapFireball");
			level.setCursor(c);
			currentSprite = actionCommand;
			
		}
		else if (actionCommand == OBJECT_PORTAL) {
			image = ImageLoader.loadImage("images/entrance.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "portal");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		else if (actionCommand == OBJECT_LEVELSWITCH) {
			image = ImageLoader.loadImage("images/entrance2.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0),"levelSwitch");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		else if (actionCommand == OBJECT_GOAL) {
			image = ImageLoader.loadImage("images/exit.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "goal");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		else if (actionCommand == OBJECT_SPAWN) {
			image = ImageLoader.loadImage("images/held.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "spawn");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		else if (actionCommand == OBJECT_SWORD) {
			image = ImageLoader.loadImage("images/sword.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "sword");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		else if (actionCommand == OBJECT_JACKET) {
			image = ImageLoader.loadImage("images/hero.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "jacket");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		else if (actionCommand == SAVE) {
			new SaveDialog(this);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (currentSprite == OBJECT_WALL) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
			sprites.add(new Wall(e.getX(),e.getY()));
			}
		}
		else if (currentSprite == ENEMY_GHOST) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
			sprites.add(new EnemyGhost(e.getX(),e.getY(),null));
			}
		} 
		else if (currentSprite == TRAP_SPEARS) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
			sprites.add(new GroundTrap(e.getX(),e.getY(),null));
			}
		}
		else if (currentSprite == TRAP_FIREBALL) {
			if(checkCollision(e.getX(),e.getY(),16,16)==false){
			sprites.add(new FireballTrap(e.getX(),e.getY(),null,1,0,100,100));
			}
		}
		else if (currentSprite == OBJECT_PORTAL) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
			sprites.add(new PortalEntrance(e.getX(),e.getY(),300,300));
			}
		} 
		else if (currentSprite == OBJECT_LEVELSWITCH) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
			sprites.add(new LevelSwitch(e.getX(),e.getY(),"levels/TestLevel.xml"));
			}
		} 
		else if (currentSprite == OBJECT_GOAL) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
			sprites.add(new Goal(e.getX(),e.getY()));
			}
		} 
		else if (currentSprite == OBJECT_SPAWN) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
			sprites.add(new HeroEditor(e.getX(),e.getY()));
			}
		}
		else if (currentSprite == OBJECT_SWORD) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
			sprites.add(new Sword(e.getX(),e.getY()));
			}
		}
		else if (currentSprite == OBJECT_JACKET) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
			sprites.add(new Jacket(e.getX(),e.getY()));
			}
		}
		((GamePanel)level).render(1,sprites);
		level.repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	private boolean checkCollision(int x, int y, int width, int heigth){
		for(int i=0;i<sprites.size();i++){
			Sprite sprite = sprites.get(i);
			int size = 32;
			
			if(sprite instanceof FireballTrap){
				size = 16;
				
			}
			if(sprite.getX()<=x && sprite.getX()+size>x){
				if(sprite.getY()<=y && sprite.getY()+size>y){
					
					return true;
				}
				if(sprite.getY()>=y && sprite.getY()<y+heigth){
					
					return true;
				}
			}
			if(sprite.getX()>=x && sprite.getX()<x+width){
				if(sprite.getY()>=y && sprite.getY()<y+heigth){
					
					return true;
				}
				if(sprite.getY()<=y && sprite.getY()+size>y){
					
					return true;
				}
			}
			if(sprite.getX()==x && sprite.getX()==x+width){
				if(sprite.getY()==y && sprite.getY()==y+heigth){
					
					return true;
				}
			}
		}
		return false;
	}
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	public void saveLevel(){
		new LevelSaver(fileName, sprites).saveLevel();
	}
}
