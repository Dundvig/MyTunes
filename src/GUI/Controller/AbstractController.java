package GUI.Controller;

import GUI.Model.MTModel;

public abstract class AbstractController {
    private MTModel mtModel;

    public void setModel(MTModel mtModel)
    {
        this.mtModel = mtModel;
    }

    public MTModel getModel(){
        return mtModel;
    }

    public abstract void setup();
}

