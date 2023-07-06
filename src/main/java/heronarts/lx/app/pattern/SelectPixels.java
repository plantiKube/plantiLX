package heronarts.lx.app.pattern;

import heronarts.lx.LX;
import heronarts.lx.LXCategory;
import heronarts.lx.color.LXColor;
import heronarts.lx.model.LXPoint;
import heronarts.lx.parameter.BooleanParameter;
import heronarts.lx.pattern.LXPattern;

import java.util.ArrayList;

@LXCategory("plantiKube")
public class SelectPixels extends LXPattern {
    static final int NUM_BUTTONS = 32;

    static final ArrayList<String> nameMap = new ArrayList<String>();

    public final BooleanParameter[] buttons = new BooleanParameter[NUM_BUTTONS];
    public SelectPixels(LX lx) {
        super(lx);

        nameMap.add("DELIVERY.ph"); // 0
        nameMap.add("BORKED.ph");   // 1
        nameMap.add("LEFT.1.ph");   // 2
        nameMap.add("LEFT.2.ph");   // 3
        nameMap.add("FAUCET.ph");   // 4
        nameMap.add("RIGHT.1.ph");        // 5
        nameMap.add("LEFT.1.ph");        // 6
        nameMap.add("H20.ph");        // 7
        nameMap.add("ENTER.mod");        // 8
        nameMap.add("EXIT.mod");        // 9
        nameMap.add("PASS.mod");        // 10
        nameMap.add("RETURN.mod");        // 11
        nameMap.add("PUMP.mod");        // 12
        nameMap.add("PUMP.ph");        // 13
        nameMap.add("GROW.0");        // 14
        nameMap.add("GROW.1");        // 15
        nameMap.add("GROW.2");        // 16
        nameMap.add("GROW.3");        // 17
        nameMap.add("GROW.4");        // 18
        nameMap.add("GROW.5");        // 19
        nameMap.add("GROW.6");        // 20
        nameMap.add("GROW.7");        // 21
        nameMap.add("GROW.8");        // 22
        nameMap.add("GROW.9");        // 23
        nameMap.add("GROW.10");        // 24
        nameMap.add("GROW.11");        // 25
        nameMap.add("GROW.12");        // 26
        nameMap.add("GROW.13");        // 27
        nameMap.add("GROW.14");        // 28
        nameMap.add("GROW.15");        // 29
        nameMap.add("NA.0");        // 30
        nameMap.add("NA.1");        // 31




        for (int i = 0; i < NUM_BUTTONS; i++) {
//            buttons[i] = new BooleanParameter(Integer.toString(i), false);
            String paramString = i + "_" + nameMap.get(i);
            buttons[i] = new BooleanParameter(paramString, false);
            addParameter(paramString, buttons[i]);
        }
    }

    @Override
    public void run(double deltaMs) {
        int index = 0;
        for (LXPoint p : model.points) {
            if (index < NUM_BUTTONS){
                if (buttons[index].getValueb()) {
                    colors[p.index] = LXColor.hsb(0, 0, 100);
                } else {
                    colors[p.index] = LXColor.hsb(0, 0, 0);
                }
            }
            index++;
        }
    }
}
