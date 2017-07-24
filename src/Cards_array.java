import java.util.ArrayList;

public class Cards_array {
    private ArrayList<Card> myarray1;
    private Card[] myarray2;
    private int len;

    public Cards_array(int len) {
        this.myarray2 = new Card[len];
        this.myarray1 = new ArrayList();
        this.len = len;

        for(int counter = 0; counter < len; ++counter) {
            this.myarray2[counter] = null;
        }

    }

    public void add_card(Card p) {
        this.myarray1.add(p);
    }

    public Card searchcard(int e) {
        return (Card)this.myarray1.get(e);
    }

    public void addmemcard(Card b) {
        int counter;
        for(counter = 0; counter < this.len; ++counter) {
            if(this.myarray2[counter] == null) {
                this.myarray2[counter] = b;
                return;
            }
        }

        for(counter = 0; counter < this.len; ++counter) {
            this.myarray2[counter] = this.myarray2[counter + 1];
            if(counter == this.len - 1) {
                this.myarray2[this.len - 1] = b;
            }
        }

    }

    public Card searchmem(int g) {
        for(int counter = 0; counter < this.len; ++counter) {
            if(this.myarray2[counter].returnid() == g) {
                return this.myarray2[counter];
            }
        }

        return null;
    }

    public void deletemem(int a) {
        int t = 0;

        int counter;
        for(counter = 0; counter < this.len; ++counter) {
            if(this.myarray2[counter].returnid() == a) {
                this.myarray2[counter] = null;
                t = counter;
            }
        }

        for(counter = t; counter < this.len; ++counter) {
            this.myarray2[counter] = this.myarray2[counter + 1];
            if(counter == this.len - 1) {
                this.myarray2[this.len - 1] = null;
            }
        }

    }
}