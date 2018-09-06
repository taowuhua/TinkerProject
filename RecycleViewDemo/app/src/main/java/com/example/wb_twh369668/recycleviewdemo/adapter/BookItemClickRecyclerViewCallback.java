package com.example.wb_twh369668.recycleviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.wb_twh369668.recycleviewdemo.interfaces.ItemTouchHelperAdapterListener;

/**
 * creat by TWH on 2018/9/6
 */
public class BookItemClickRecyclerViewCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapterListener itemTouchHelperAdapterListener;

    public BookItemClickRecyclerViewCallback(ItemTouchHelperAdapterListener itemTouchHelperAdapterListener) {
        this.itemTouchHelperAdapterListener = itemTouchHelperAdapterListener;
    }

    /**
     * ItemTouchHelper可以让你轻易得到一个事件的方向。
     * 你需要重写getMovementFlags()方法来指定可以支持
     * 的拖放和滑动的方向。
     * 使用helperItemTouchHelper.makeMovementFlags(int, int)
     * 来构造返回的flag。这里我们启用了上下左右两种方向。
     * 注：上下为拖动（drag），左右为滑动（swipe）。
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipFlags);
    }

    //TODO 进行数据的交换,修改onMove()方法提供回调
    /**
     * Called when ItemTouchHelper wants to move the dragged item from its old position to
     * the new position.
     * 当移动一个条目时被调用
     * <p>
     * If this method returns true, ItemTouchHelper assumes {@code viewHolder} has been moved
     * to the adapter position of {@code target} ViewHolder
     * ({@link RecyclerView.ViewHolder#getAdapterPosition()
     * ViewHolder#getAdapterPosition()}).
     * 如果该方法返回true，认定条目已经移动到了新的位置
     * <p>
     * If you don't support drag & drop, this method will never be called.
     * 如果你不支持拖拽，此方法不会被调用
     *
     * @param recyclerView The RecyclerView to which ItemTouchHelper is attached to.
     *                     ItemTouchHelper所附着的RecyclerView
     * @param source   The ViewHolder which is being dragged by the user.
     *                 正在被用户拖拽的位置
     * @param target       The ViewHolder over which the currently active item is being
     *                     dragged.
     *                     当前正在被拖拽的条目所经过的位置
     * @return True if the {@code viewHolder} has been moved to the adapter position of
     * {@code target}.
     * @see #onMoved(RecyclerView, RecyclerView.ViewHolder, int, RecyclerView.ViewHolder, int, int, int)
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        itemTouchHelperAdapterListener.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());

        return false;
    }
    /**
     * Called when a ViewHolder is swiped by the user.
     * 当被左右滑动时，调用该方法
     * 注意：这里的ViewHolder统一翻译成条目，通过ViewHolder可以得到关于条目的所有属性（如：位置等等）
     * <p>
     * If you are returning relative directions ( {START} , {END}) from the
     * {@link #getMovementFlags(RecyclerView, RecyclerView.ViewHolder)} method, this method
     * will also use relative directions. Otherwise, it will use absolute directions.
     * 当你返回一个相对方向时（由getMovementFlags()所返回的方向），该方法使用的是相对方向，否则使用绝对方向
     * <p>
     * If you don't support swiping, this method will never be called.
     * 如果你不支持左右滑动，该方法不会被调用
     * <p>
     * ItemTouchHelper will keep a reference to the View until it is detached from
     * RecyclerView.
     * ItemTouchHelper将会持有View的引用，直到ItemTouchHelper不再附着在RecyclerView上时
     * As soon as it is detached, ItemTouchHelper will call
     * {@link #clearView(RecyclerView, RecyclerView.ViewHolder)}.
     * 当不再附着时，ItemTouchHelper会调用clearView方法
     *
     * @param viewHolder The ViewHolder which has been swiped by the user.
     *                   用户拖拽的条目
     * @param direction  The direction to which the ViewHolder is swiped. It is one of
     *                   {UP}, {DOWN},
     *                   {LEFT} or {RIGHT}. If your
     *                   {@link #getMovementFlags(RecyclerView, RecyclerView.ViewHolder)}
     *                   method
     *                   returned relative flags instead of {LEFT} / {RIGHT};
     *                   `direction` will be relative as well. ({START} or {
     *                   END}).
     *                   条目被左右滑动的方向，他是上、下、左、右中的一个，如果getMovementFlags返回了
     *                   相对标志flags代替了左、右方向，将使用这个返回的方向
     *
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemTouchHelperAdapterListener.onItemSwip(direction);
    }
    /**
     * 要支持长按RecyclerView item进入拖动操作，
     * 你必须在isLongPressDragEnabled()方法中返回true。
     * 或者，也可以调用ItemTouchHelper.startDrag(RecyclerView.ViewHolder)
     * 方法来开始一个拖动。
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;//返回true表示支持长按开始拖拽
    }
    /**
     * 而要在view任意位置触摸事件发生时启用滑动操作，
     * 则直接在sItemViewSwipeEnabled()中返回true就可以了。
     * 或者，你也主动调用ItemTouchHelper.startSwipe(RecyclerView.ViewHolder)
     * 来开始滑动操作。
     */
    @Override
    public boolean isItemViewSwipeEnabled() {

        return true;//返回true表示支持左右滑动
    }


}
