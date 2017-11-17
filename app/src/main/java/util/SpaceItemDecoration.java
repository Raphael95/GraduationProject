package util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by raphael on 2017/5/19.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    int space;

    public SpaceItemDecoration(int space){

        this.space = space;


    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.top = space;

        outRect.left = space;

        outRect.right = space;

        outRect.bottom = space;





    }
}