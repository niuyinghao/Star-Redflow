package my.util;

import my.model.persist.project.HeartSymbol;


/**
 * Created by niuyinghao on 2016/6/2 for project.
 */
public class HeartSymbolResolver {
    public static String resolveStyle(HeartSymbol symbol) throws Exception {
        String style = null;
        int age = symbol.getAge();
        if (symbol.getKind() == HeartSymbol.Kind.wave) {
            style = "color:darkred;";
            if (age <= 3) { // mature age;
                float opacity = (age+1) / 3f;
                style += " filter:alpha(Opacity=80);-moz-opacity:" +
                        opacity +
                        ";opacity: " +
                        opacity +
                        "; ";
            }
            else {    // inflation age ;
                int base = 18;  // 基量
                int increment = age * 10; // 增量
                style += " font-size:" +
                        (base + increment)
                        + "px;";
            }
        }
        else if (symbol.getKind() == HeartSymbol.Kind.flower) {
            style = " color:pink;";
            if (age <= 3) { // mature age;
                float opacity = (age+1) / 3f;
                style += " filter:alpha(Opacity=80);-moz-opacity:" +
                        opacity +
                        ";opacity: " +
                        opacity +
                        "; ";
            }
            else {    // inflation age ;
                int base = 0;  // 基量
                int threadHold = 5; // 域值
                int increment = age > threadHold ? threadHold : age; // 增量
                int i = base + increment;
                int blue = i / 10;
                style = "    -webkit-filter: blur(10px); /* Chrome, Opera */\n" +
                        "       -moz-filter: blur(10px);\n" +
                        "        -ms-filter: blur(10px);    \n" +
                        "            filter: blur(10px);\n" +
                        "    \n" +
                        "    filter: progid:DXImageTransform.Microsoft.Blur(PixelRadius=10, MakeShadow=false); /* IE6~IE9 */";

            }
        }
        else if (symbol.getKind() == HeartSymbol.Kind.stone) {
            style = "";
        }
        else {
            style = "";
        }
        return style;
    }
}
