import java.util.Random;
import javax.swing.Icon;
import javax.swing.JButton;

public class Logic {
    public Logic() {
    }

    public int Run_game1(Card f, Card x, Player p1, Machine m1, boolean order) {
        return !order?(f.returnvalue() == x.returnvalue()?p1.updatescore(1):((f.returnvalue() != 0 || x.returnvalue() == 0) && (f.returnvalue() == 0 || x.returnvalue() != 0)?p1.updatescore(0):p1.updatescore(1))):(f.returnvalue() == x.returnvalue()?m1.updatescore(1):((f.returnvalue() != 0 || x.returnvalue() == 0) && (f.returnvalue() == 0 || x.returnvalue() != 0)?m1.updatescore(0):m1.updatescore(1)));
    }

    public int Run_game2(Card f, Card x, Player p1, Player p2, boolean order) {
        return !order?(f.returnvalue() == x.returnvalue()?p1.updatescore(1):((f.returnvalue() != 0 || x.returnvalue() == 0) && (f.returnvalue() == 0 || x.returnvalue() != 0)?p1.updatescore(0):p1.updatescore(1))):(f.returnvalue() == x.returnvalue()?p2.updatescore(1):((f.returnvalue() != 0 || x.returnvalue() == 0) && (f.returnvalue() == 0 || x.returnvalue() != 0)?p2.updatescore(0):p2.updatescore(1)));
    }

    public void machinecard(JButton[] buttonagame, Cards_array x, int leng) {
        Random r2 = new Random();
        int k = 0;

        int r;
        for(r = 0; r < leng; ++r) {
            if(x.searchmem(r) == null) {
                ++k;
            }
        }

        if(k != leng) {
            buttonagame[x.searchmem(0).returnid()].doClick();
        } else {
            do {
                r = r2.nextInt(19);
            } while(buttonagame[r].getIcon() != null);

            buttonagame[r].doClick();
        }
    }

    public boolean endgame(JButton[] game, Cards_array cv) {
        for(int counter = 0; counter < 20; ++counter) {
            if(game[counter].getIcon() == null) {
                for(int count = 0; count < 20; ++count) {
                    if(count != counter && game[count].getIcon() == null && (cv.searchcard(count).returnvalue() == cv.searchcard(counter).returnvalue() || cv.searchcard(count).returnvalue() == 0 || cv.searchcard(counter).returnvalue() == 0)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void flip(JButton[] game1, Cards_array cv1, int f1, int x1) {
        if(cv1.searchcard(f1).returnvalue() != cv1.searchcard(x1).returnvalue() && cv1.searchcard(f1).returnvalue() != 0 && cv1.searchcard(x1).returnvalue() != 0) {
            game1[f1].setIcon((Icon)null);
            game1[x1].setIcon((Icon)null);
        }

    }

    public boolean flipcardmachine(JButton[] game2, Cards_array cv2, int f2, int x2) {
        if(cv2.searchcard(f2).returnvalue() != cv2.searchcard(x2).returnvalue() && cv2.searchcard(f2).returnvalue() != 0 && cv2.searchcard(x2).returnvalue() != 0) {
            game2[f2].setIcon((Icon)null);
            game2[x2].setIcon((Icon)null);
            return true;
        } else {
            return false;
        }
    }
}