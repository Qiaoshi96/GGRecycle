package com.example.administrator.ggrecycle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liqy on 2017/8/9.
 */

public class MyLine extends RecyclerView.ItemDecoration {
    Drawable drawable;
    public MyLine(Context context, int resId) {
        drawable=context.getResources().getDrawable(resId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(8,8,8,8);
    }
//重写方法来实现自定义布局
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

//   绘制分割线

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //以下计算主要用来确定绘制的位置
            final int top = child.getBottom() + params.bottomMargin;
            //注意这边drawable.getIntrinsicHeight()必须有高度
            final int bottom = top + drawable.getIntrinsicHeight();

            drawable.setBounds(left,top,right,bottom);
            drawable.draw(c);

            //        绘制虚线
//            Paint paint = new Paint();
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setColor(Color.RED);
//            Path path = new Path();
//            path.moveTo(left, top);
//            path.lineTo(right,top);
//            PathEffect effects = new DashPathEffect(new float[]{15,15,15,15},5);//此处单位是像素不是dp  注意 请自行转化为dp
//            paint.setPathEffect(effects);
//            c.drawPath(path, paint);
//            drawable.setBounds(left,top,right,bottom);
//            drawable.draw(c);
        }


    }
}
