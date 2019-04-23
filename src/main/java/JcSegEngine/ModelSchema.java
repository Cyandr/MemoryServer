package JcSegEngine;

public class ModelSchema {


     long Description;

    int ConsumeLexModel=ModelPiece.People.Flag() &
                        ModelPiece.Time.Flag() &
                        ModelPiece.Location.Flag() &
                        ModelPiece.Activity.Flag() &
                        ModelPiece.Cost.Flag() &
                        ModelPiece.RelatedObjects.Flag();

    ModelSchema(int n)
    {

        Description=n;
    }

}
