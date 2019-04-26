package JcSegEngine;

public class ModelSchema {


     long Description;

  static   int ConsumeLexModel= PieceType.People.Flag() &
                        PieceType.Time.Flag() &
                        PieceType.Location.Flag() &
                        PieceType.Activity.Flag() &
                        PieceType.Cost.Flag() &
                        PieceType.RelatedObjects.Flag();

    ModelSchema(int n)
    {

        Description=n;
    }

}
