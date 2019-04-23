package JcSegEngine;

import org.apache.jena.base.Sys;
import org.apache.lucene.analysis.Analyzer;
import org.lionsoul.jcseg.analyzer.JcsegAnalyzer;
import org.lionsoul.jcseg.tokenizer.core.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class JcSegTest {

   /* 名词n、时间词t、处所词s、方位词f、数词m、量词q、区别词b、代词r、动词v、
   形容词a、状态词z、副词d、介词p、连词c、助词u、语气词y、叹词e、拟声词o、
   成语i、习惯用语l、简称j、前接成分h、后接成分k、语素g、非语素字x、标点符号w）外，
   从语料库应用的角度，增加了专有名词（人名nr、地名ns、机构名称nt、其他专有名词nz）。
    */

    static public void init() throws JcsegException, IOException {
        //创建JcsegTaskConfig使用默认配置，不做任何配置文件查找
        //JcsegTaskConfig config = new JcsegTaskConfig();

        //该方法会自动按照上述“jcseg.properties查找步骤”来寻找jcseg.properties并且初始化：
        JcsegTaskConfig config = new JcsegTaskConfig(true);

        //依据给定的jcseg.properties文件创建并且初始化JcsegTaskConfig
        // JcsegTaskConfig config = new JcsegTaskConfig("absolute or relative jcseg.properties path");

        //调用JcsegTaskConfig#load(String proFile)方法来从指定配置文件中初始化配置选项
        config.load("JcSegConfig/jcseg.properties");

        File file = new File("JcSegConfig/lexicon");

        if (file.exists()) {

            String[] strFIles = new String[1];
            strFIles[0] = file.getAbsolutePath();
            config.setLexiconPath(strFIles);
        }

        //Jcseg提供org.lionsoul.jcseg.tokenzier.core.DictionaryFactory来方便词库的创建与往后的兼容
        //通常可以通过
        //  DictionaryFactory#createDefaultDictionary(JcsegTaskConfig)
        //  DictionaryFactory.createSingletonDictionary(JcsegTaskConfig)
        //两方法来创建词库对象并且加载词库文件，建议使用createSingletonDictionary来创建单例词库

        //config为上面创建的JcsegTaskConfig对象.
        //如果给定的JcsegTaskConfig里面的词库路径信息正确
        //ADictionary会依据config里面的词库信息加载全部有效的词库;
        //并且该方法会依据config.isAutoload()来决定词库的同步性还是非同步性,
        //config.isAutoload()为true就创建同步词库, 反之就创建非同步词库,
        //config.isAutoload()对应jcseg.properties中的lexicon.autoload;
        //如果config.getLexiconPath() = null，DictionaryFactory会自动加载classpath下的词库
        //如果不想让其自动加载lexicon下的词库
        //可以调用：DictionaryFactory.createSingletonDictionary(config, false)创建ADictionary即可；
        //ADictionary dic = DictionaryFactory.createSingletonDictionary(config);


        //创建一个非同步的按照config.lexPath配置加载词库的ADictioanry.
        //ADictionary dic = DictionaryFactory.createDefaultDictionary(config, false);
        //创建一个同步的按照config.lexPath加载词库的ADictioanry.
        //ADictionary dic = DictionaryFactory.createDefaultDictionary(config, true);
        //依据 config.isAutoload()来决定同步性，默认按照config.lexPath来加载词库的ADictionary
        ADictionary dic = DictionaryFactory.createDefaultDictionary(config, config.isAutoload());


        //指定ADictionary加载给定目录下的所有词库文件的词条.
        //config.getLexiconPath为词库文件存放有效目录数组.
        for (String path : config.getLexiconPath()) {

            dic.loadDirectory(path);

        }

        //指定ADictionary加载给定词库文件的词条.
        //dic.load("/java/lex-main.lex");
        //dic.load(new File("/java/lex-main.lex"));

        //指定ADictionary加载给定输入流的词条
        //dic.load(new FileInputStream("/java/lex-main.lex"));

        //阅读下面的“如果自定义使用词库”来获取更多信息

        //依据给定的ADictionary和JcsegTaskConfig来创建ISegment
        //通常使用SegmentFactory#createJcseg来创建ISegment对象
        //将config和dic组成一个Object数组给SegmentFactory.createJcseg方法
        //JcsegTaskConfig.COMPLEX_MODE表示创建ComplexSeg复杂ISegment分词对象
        //JcsegTaskConfig.SIMPLE_MODE表示创建SimpleSeg简易Isegmengt分词对象.
        //JcsegTaskConfig.DETECT_MODE表示创建DetectSeg Isegmengt分词对象.
        //JcsegTaskConfig.SEARCH_MODE表示创建SearchSeg Isegmengt分词对象.
        //JcsegTaskConfig.DELIMITER_MODE表示创建DelimiterSeg Isegmengt分词对象.
        //JcsegTaskConfig.NLP_MODE表示创建NLPSeg Isegmengt分词对象.
        IMain_seg = SegmentFactory.createJcseg(
                JcsegTaskConfig.NLP_MODE,
                config, dic);

        StruLag.Init();
    }

    static ISegment IMain_seg;

    public static HashMap<WordType, Word> test(String str) throws Exception {

        HashMap<WordType, Word> returnMap = new HashMap<>();
        //设置要分词的内容
        //String str = "小明昨天中午在上海合作组织啊啊吃米饭花费了多少元钱？";
        IMain_seg.reset(new StringReader(str));

        //获取分词结果
        IWord word = null;
        while ((word = IMain_seg.next()) != null) {
            String key = word.getPartSpeech()[0];

            WordType wordType = StruLag.findType(key.toUpperCase());
            if (wordType==null)wordType=WordType.UNKOWN;

            returnMap.put(wordType, new Word(word));
            System.out.println(wordType.toString()+"___"+word.getValue());
            System.out.println( word.toString());
        }
        return returnMap;
    }
}
