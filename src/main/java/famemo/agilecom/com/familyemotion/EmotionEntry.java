package famemo.agilecom.com.familyemotion;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by miyabi on 11/15/15.
 */
public class EmotionEntry {
    public String content;
    public int emotion;


    public EmotionEntry(String content, int emotion) {
        this.content = content;
        this.emotion = emotion;
    }

    public static int getResourceId(int id) {
        int drw;


        switch (id) {
            case 0:
                drw = android.R.drawable.ic_menu_more;
                break;
            case 1:
                drw = android.R.drawable.ic_menu_add;
                break;
            case 2:
                drw = android.R.drawable.ic_menu_agenda;
                break;
            default:
                drw = android.R.drawable.ic_delete;
                break;
        }
        return drw;
    }

    public Drawable getImageData() {
        Resources res = Resources.getSystem();

        int t = ((int) Math.round(Math.random() * 3));

        return res.getDrawable(this.getResourceId(t));
    }

    public String getTextData() {
        return content;
    }
}
