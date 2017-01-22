package com.github.farafonoff.deskpetsir.control;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Artem_Farafonov on 12/22/2015.
 */
public class ControlAdapter extends RecyclerView.Adapter<ControlAdapter.MyViewHolder> {
    public interface ActionListener {
        public void onAction(Action act);
    }

    static class MyButton extends Button {

        public MyButton(Context context) {
            super(context);
        }

        OnGenericMotionListener listener;

        @Override
        public boolean onGenericMotionEvent(MotionEvent event) {
            if (listener!=null) {
                return listener.onGenericMotion(this, event);
            }
            else {
                return super.onGenericMotionEvent(event);
            }
        }

        @Override
        public void setOnGenericMotionListener(OnGenericMotionListener l) {
            super.setOnGenericMotionListener(l);
            this.listener = l;
        }
    }

    ActionListener myListener;

    public ControlAdapter(ActionListener listener) {
        myListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button view = new MyButton(parent.getContext());
        view.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View v, MotionEvent event) {
                Log.d("BUTTON", "" + event.getActionMasked());
                if (v.getTag() != null && v.getTag() instanceof Action) {
                    myListener.onAction((Action) v.getTag());
                }
                return true;
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getTag() != null && v.getTag() instanceof Action) {
                    switch(event.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            myListener.onAction((Action) v.getTag());
                            break;
                        case MotionEvent.ACTION_UP:
                            myListener.onAction(Action.STOP);
                            break;
                    }
                }
                return false;
            }
        });
        MyViewHolder vh = new MyViewHolder(view);
        parent.addView(vh.button);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position<Action.actionList.size()) {
            Action action = Action.actionList.get(position);
            holder.button.setTag(action);
            holder.button.setText(action.label);
            holder.button.setTextSize(60);
        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public MyViewHolder(Button itemView) {
            super(itemView);
            this.button = itemView;
        }
    }
}
