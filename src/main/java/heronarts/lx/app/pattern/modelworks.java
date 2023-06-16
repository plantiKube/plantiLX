package heronarts.lx.app.pattern;

import heronarts.lx.LX;
import heronarts.lx.LXCategory;
import heronarts.lx.LXClassLoader;
import heronarts.lx.color.LXColor;
import heronarts.lx.model.LXModel;
import heronarts.lx.model.LXPoint;
import heronarts.lx.parameter.BooleanParameter;
import heronarts.lx.parameter.DiscreteParameter;
import heronarts.lx.pattern.LXPattern;

import static java.lang.Math.abs;
import static java.lang.Math.max;

@LXCategory("Test")
public class modelworks extends LXPattern {

    public final int NUM_BUTTONS = model.children[0].children.length;
    public final BooleanParameter[] buttons = new BooleanParameter[NUM_BUTTONS];

    public final DiscreteParameter x = new DiscreteParameter("select component", 0, model.children[0].children.length)
            .setDescription("select segment");

    public modelworks(LX lx) {
        super(lx);

        addParameter("x", this.x);

        for (int i = 0; i < NUM_BUTTONS; i++) {
            String thisButtonLabel = String.valueOf(i);
            buttons[i] = new BooleanParameter(thisButtonLabel, false);
            addParameter(thisButtonLabel, buttons[i]);

        }
    }

    public void run(double deltaMs) {
        int x = this.x.getValuei();

        LXModel model = this.lx.getModel();

        // make sure the growPlate model is the first in the Fixtures list
        LXModel[] ringChildren = model.children[0].children;

        int i = 0;
        for (LXModel ring : ringChildren) {
            if (buttons[i].getValueb()) {
                for (LXPoint p : ring.points) {
                    colors[p.index] = LXColor.rgb(
                            (int) 0xff,
                            (int) 0xff,
                            (int) 0xff
                    );
                }
            } else {
                for (LXPoint p : ring.points) {
                    colors[p.index] = LXColor.rgb(
                            (int) 0x00,
                            (int) 0x00,
                            (int) 0x00
                    );
                }
            }

            i++;
        }

//            for (LXPoint p : model.points) {
//
//                if (z_en) {
//                    colors[p.index] = LXColor.hsb(0, 100, 100);
//                } else {
//                    colors[p.index] = LXColor.hsb(120, 100, 100);
//                }
//            }
    }
}
