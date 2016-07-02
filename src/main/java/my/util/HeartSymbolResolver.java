package my.util;

import com.google.gson.JsonObject;
import my.model.persist.project.HeartSymbol;
import my.model.persist.spirit.Flower;
import my.model.persist.spirit.Stone;
import my.model.persist.spirit.Wave;
import org.primefaces.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by niuyinghao on 2016/6/2 for project.
 */
public class HeartSymbolResolver {

    public static final int INCREATEMENT_STEP = 40;
    public static final int FLOWER_INCREATEMENT_STEP = 10;

    public static String resolveStyleJson(HeartSymbol symbol, Object belong) throws Exception {
        Map styleMap = resolveStyleMap(symbol, belong);
        return JSONObject.valueToString(styleMap);
    }

    public static Map resolveStyleMap(HeartSymbol symbol, Object belong) {
        Map styleMap = new HashMap();
        int age = symbol.getAge();
        if (belong.getClass() == Wave.class) {
            resolveWaveAge(styleMap, age);

        }
        else if (belong.getClass() == Flower.class) {
            resolveFlowerAge(styleMap, age);

        }
        else if (belong.getClass() == Stone.class) {
        }
        else {
        }

        return styleMap;
    }

    private static void resolveWaveAge(Map styleMap, int age) {
        styleMap.put("color", "#8B0000");
        if (age <= 3) { // mature age;
            float opacity = (age + 1) / 3f;
            styleMap.put("filter", "alpha(Opacity=80)");
            styleMap.put("-moz-opacity", opacity);
            styleMap.put("opacity", opacity);
        }
        else if (3 < age && age <= 7) {    // inflation age ;
            int base = 18;  // 基量
            int increment = (age - 3) * INCREATEMENT_STEP; // 增量
            styleMap.put("font-size", (base + increment)
                    + "px");
        }
        else if (7 < age && age <= 11) {  // aging and dying age
            int base = 18;
            int increment = (7 - 3) * INCREATEMENT_STEP;
            styleMap.put("font-size", (base + increment) + "px");


            waveAgingPhase(styleMap, age);
        }
        else {
            int base = 18;
            int increment = (7 - 3) * INCREATEMENT_STEP;
            styleMap.put("font-size", (base + increment) + "px");
            waveAgingPhase(styleMap, 11);

        }

        styleMap.put("_age", age);
    }

    private static void resolveFlowerAge(Map styleMap, int age) {
        age = age + 1; // correct base 0;

        int ageMod = age % 12;

        styleMap.put("color", "#800080"); // purple
        if (ageMod <= 3) { // mature age;
            float opacity = (ageMod + 1) / 3f;
            styleMap.put("filter", "alpha(Opacity=80)");
            styleMap.put("-moz-opacity", opacity);
            styleMap.put("opacity", opacity);

            styleMap.put("font-size", FLOWER_INCREATEMENT_STEP
                    + "px");

        }
        else if (ageMod > 3 && ageMod <= 7) {    // inflation age ;
            int base = FLOWER_INCREATEMENT_STEP;  // 基量
            int increment = ageMod * FLOWER_INCREATEMENT_STEP; // 增量
            int i = base + increment;

            styleMap.put("font-size", (base + increment)
                    + "px");
            int blur = (ageMod + (10-7)) / 10;
            styleMap.put("-webkit-filter", "blur(" +
                    blur + "px)");
            styleMap.put("-moz-filter", "blur(" +
                    blur + "px)");
            styleMap.put("-ms-filter", "blur(" +
                    blur + "px)");
            styleMap.put("filter", "blur(" +
                    blur + "px)");
            styleMap.put("filter", "progid:DXImageTransform.Microsoft.Blur(PixelRadius=" +
                    blur +
                    ", MakeShadow=false); /* IE6~IE9 */");

        }
        else if (ageMod > 7 && ageMod <= 11) {  // shrink age
            int base=FLOWER_INCREATEMENT_STEP + 7 * FLOWER_INCREATEMENT_STEP;
            int increment = - (ageMod - 7) * FLOWER_INCREATEMENT_STEP;
            styleMap.put("font-size", (base + increment)
                    + "px");

            int blur=10;

            styleMap.put("-webkit-filter", "blur(" +
                    blur + "px)");
            styleMap.put("-moz-filter", "blur(" +
                    blur + "px)");
            styleMap.put("-ms-filter", "blur(" +
                    blur + "px)");
            styleMap.put("filter", "blur(" +
                    blur + "px)");
            styleMap.put("filter", "progid:DXImageTransform.Microsoft.Blur(PixelRadius=" +
                    blur +
                    ", MakeShadow=false); /* IE6~IE9 */");

        }
        styleMap.put("_age", ageMod);
    }

    private static void waveAgingPhase(Map styleMap, int age) {
        int mod = age % 4;
        mod = mod + 1;
        styleMap.put("transform", "skew(" +
                (mod * 10) +
                "deg,0deg) rotate(" +
                (mod * 22.5) +
                "deg) scale(" +
                (0.8 - 0.1 * mod) +
                "," +
                (1.1 + 0.1 * mod) +
                ")");
        if (mod == 1) {
            styleMap.put("color", "#CD5C5C"); // indianred
        }
        else if (mod == 2) {
            styleMap.put("color", "#808080"); // grey

        }
        else if (mod == 3) {
            styleMap.put("color", "#696969"); //dimgrey

        }
        else if (mod == 4) {
            styleMap.put("color", "#000000"); // black

        }
        styleMap.put("transform-origin", "0");

    }

    public static String resolveStyleStyle(HeartSymbol symbol, Object belong) {
        Map<String, Object> styleMap = resolveStyleMap(symbol, belong);
        return convertMapToString(styleMap);

    }

    public static String convertMapToString(Map<String, Object> styleMap) {
        if (styleMap == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : styleMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            sb.append(key + ":" + value + ";");
        }
        return sb.toString();
    }
}
