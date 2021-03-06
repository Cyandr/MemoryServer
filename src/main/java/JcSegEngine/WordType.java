package JcSegEngine;

public enum WordType {

     /* 名词n、时间词t、处所词s、方位词f、数词m、量词q、区别词b、代词r、动词v、
   形容词a、状态词z、副词d、介词p、连词c、助词u、语气词y、叹词e、拟声词o、
   成语i、习惯用语l、简称j、前接成分h、后接成分k、语素g、非语素字x、标点符号w）外，
   从语料库应用的角度，增加了专有名词（人名nr、地名ns、机构名称nt、其他专有名词nz）。
    */
    N,//名词 noun
    T,//time 时间
    S,//处所词
    F,//方位词
    M,//数词
    Q,//量词
    B,//区别词
    R,//代词
    V,//动词
    A,//形容词
    Z,//状态词
    D,//副词
    P,//介词
    C,//连词
    U,//助词
    Y,//语气词
    E,//叹词
    O,//拟声词
    I,//成语
    L,//习惯用语
    J,//简称
    H,//前接成分
    K,//后接成分
    G,//语素
    X,//非语素字
    W,//标点符号
    NR,//人名
    NS,//地名
    NT,//机构名称
    NZ,//其他专有名词;
    ND,//在
    UNKOWN;
}
