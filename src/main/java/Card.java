public class Card
{
    private int id;
    private int value;

    public Card(int id, int value)
    {
        this.id = id;
        this.value = value;
    }
    public int returnid()
    {
        return this.id;
    }
    public int returnvalue()
    {
        return this.value;
    }
}