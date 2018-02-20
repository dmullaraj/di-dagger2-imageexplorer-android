package al.dmullaraj.di.daggerimageexplorer.ui.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by denis.mullaraj on 19/01/2018.
 */

public class SquareWidthLayout extends RelativeLayout{

    public SquareWidthLayout(Context context) {
        super(context);
    }

    public SquareWidthLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareWidthLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SquareWidthLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
