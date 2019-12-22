package Domain;

public class DomainManager {

    String SepChars = "//";
    String ExchangeProtcol = "http:";
    String WorldOwnerUrl;
    String RelationUrl;
    String RelaUrl;
    String SpaceUrl="Space";
    String TimeUrl="Time";

    //这种介质需要13种药品搭配而成
    public String GetSpaceUrl() {

        return ExchangeProtcol + SepChars + SpaceUrl;

    }
    public String GetThoughtUrl() {

        return ExchangeProtcol + SepChars + RelationUrl;

    }
}
