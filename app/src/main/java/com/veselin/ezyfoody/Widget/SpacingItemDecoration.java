package com.veselin.ezyfoody.Widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class SpacingItemDecoration extends RecyclerView.ItemDecoration {
    private boolean includeEdge;
    private int spacingPx;
    private int spanCount;

    public SpacingItemDecoration(int i, int i2, boolean z) {
        this.spanCount = i;
        this.spacingPx = i2;
        this.includeEdge = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = this.spanCount;
        int i2 = childAdapterPosition % i;
        if (this.includeEdge) {
            int i3 = this.spacingPx;
            rect.left = i3 - ((i2 * i3) / i);
            rect.right = ((i2 + 1) * this.spacingPx) / this.spanCount;
            if (childAdapterPosition < this.spanCount) {
                rect.top = this.spacingPx;
            }
            rect.bottom = this.spacingPx;
            return;
        }
        rect.left = (this.spacingPx * i2) / i;
        int i4 = this.spacingPx;
        rect.right = i4 - (((i2 + 1) * i4) / this.spanCount);
        if (childAdapterPosition >= this.spanCount) {
            rect.top = this.spacingPx;
        }
    }
}
