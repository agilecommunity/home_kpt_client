package famemo.agilecom.com.familyemotion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by miyabi on 11/15/15.
 */
public class EmotionAdapter extends ArrayAdapter<EmotionEntry> {
    private static LayoutInflater layoutInflater_;
    private List<EmotionEntry> mEmotionList;

    public EmotionAdapter(Context context, int resource, int textViewResourceId, List<EmotionEntry> objects) {
        super(context, resource, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mEmotionList = objects;
    }


    @Override
    public int getCount() {
        return mEmotionList.size();
    }

    @Override
    public EmotionEntry getItem(int position) {

        return mEmotionList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        final EmotionEntry item = (EmotionEntry) getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.emotion_list, null);
        }

        // CustomDataのデータをViewの各Widgetにセットする
        ImageView imageView;
        imageView = (ImageView) convertView.findViewById(R.id.image);
        if (imageView != null) {
            imageView.setImageDrawable(item.getImageData());
        }
        TextView textView = (TextView) convertView.findViewById(R.id.text);
        if (textView != null) {
            textView.setText(item.getTextData());
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ctx = getContext();
                Intent intent = new Intent();
                intent.setClass(ctx, KTPRegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Intent.ACTION_VIEW);

                intent.putExtra("emotion", item.emotion);
                intent.putExtra("content", item.getTextData());


                ctx.startActivity(intent);
            }
        });
        return convertView;
    }
}
