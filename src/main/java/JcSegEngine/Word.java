package JcSegEngine;

public class Word {

    String Word;
    int Type;
    int Position;
    String Pinyin;
    char[] pos;
    String Entity;
    String Root;
    String Parameter;


    public Word(String word, int type, int position, String pinyin, char[] pos, String entity, String root, String parameter) {
        Word = word;
        Type = type;
        Position = position;
        Pinyin = pinyin;
        this.pos = pos;
        Entity = entity;
        Root = root;
        Parameter = parameter;
    }

    public Word(String word, char[] pos) {
        Word = word;
        this.pos = pos;
    }

}
