package JcSegEngine;

import java.util.HashSet;

public enum ModelPiece {

    Time(1),
    Location(2),
    People(4),
    Activity(8),
    RelatedObjects(16),
    Cost(32),
    Product(64),
    Infuence(128);
    private int FLAG;
    private  int Value;
    private ModelPiece(int n)
    {
        FLAG = n;

    }
    public int Flag()
    {
        return FLAG;
    }
    public int Value()
    {
        return Value;
    }
    public void SetValue(int n)
    {
        Value=n;

    }

    ModelPiece sortFromWordType(Word word)
    {

        switch (word.WordType)
        {

            case R:
                return People;

        }
        HashSet<WordType> TimeContainer=new HashSet<>();
        TimeContainer.add(WordType.T);
        return People;
    }
}
