package JcSegEngine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public enum PieceType {

    NULL(0),
    Time(1),
    Location(2),
    People(4),
    Activity(8),
    RelatedObjects(16),
    Cost(32),
    Product(64),
    Infuence(128),
    ASK(256);


    private int FLAG;
    private int Value;
    private boolean IsAsk = false;

    private PieceType(int n) {
        FLAG = n;

    }


    public int Flag() {
        return FLAG;
    }

    public int Value() {
        return Value;
    }

    public void SetValue(int n) {
        Value = n;

    }

    static PieceType sortFromWordType(Word word) {

        switch (word.WordType) {

            case R:
                return People;
            case NS:
                return Location;
            case Q: {

                if (word.Entity.contains(("datetime"))) {
                    return Time;
                }
            }
            case N:
                if (word.Entity.contains("currency")) {
                    return Cost;
                }
            case W:
                if (word.Word.contains("?")) return ASK;
            default:
                return NULL;
        }

    }
    static List<PieceType> findFromKnownInt(int value, int modelSchema)
    {
        List<PieceType> pieceTypeList=new ArrayList<>();
        pieceTypeList.add(NULL);
        if (value==0)return  pieceTypeList;
        int unkown=value^modelSchema;
        for (PieceType pieceType:PieceType.values()) {
            if ((pieceType.Flag()&unkown)==pieceType.Flag())
            {
                pieceTypeList.add(pieceType);

            }
        }
        return  pieceTypeList;

    }
}