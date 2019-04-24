package JcSegEngine;

import org.lionsoul.jcseg.tokenizer.core.IWord;

public class Word {

    String Word;
    int Type;
    int Position;
    String Pinyin;
    String pos;
    String Entity;
    String Root;
    String Parameter;

    WordType WordType;

    public Word(String word, int type, int position, String pinyin, String pos, String entity, String root, String parameter) {
        Word = word;
        Type = type;
        Position = position;
        Pinyin = pinyin;
        this.pos = pos;
        Entity = entity;
        Root = root;
        Parameter = parameter;
        WordType = StruLag.findType( this.pos.toUpperCase());
        if (WordType==null)WordType=WordType.UNKOWN;
    }



    public  Word(IWord iWord)
    {

        Word = iWord.getValue();
        Type = iWord.getType();
        Position = iWord.getPosition();
        Pinyin = iWord.getPinyin();

        pos=iWord.getPartSpeech()==null?"":iWord.getPartSpeech()[0];
        Entity =iWord.getEntity()==null?"": iWord.getEntity(0);
        Root = null;
        Parameter = iWord.getParameter();
        WordType = StruLag.findType( this.pos.toUpperCase());
        if (WordType==null)WordType=WordType.UNKOWN;


    }

}
