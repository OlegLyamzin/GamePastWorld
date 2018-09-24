package life.oleg.pastworld.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import life.oleg.pastworld.controller.Controller;
import life.oleg.pastworld.model.MainGame;

public class CardTouchListener implements View.OnTouchListener {
    private TextView[] opinionText = new TextView[2];
    private RelativeLayout relativeLayout;
    private ImageView[] imagesChangeLeft;
    private ImageView[] imagesChangeRight;

    private float dX;
    private float dY;
    private float beginX = 0;
    private float beginY = 0;

    CardTouchListener(TextView[] opinionText, RelativeLayout relativeLayout, ImageView[] imagesChangeLeft, ImageView[] imagesChangeRight) {
        this.opinionText = opinionText;
        this.relativeLayout = relativeLayout;
        this.imagesChangeLeft = imagesChangeLeft;
        this.imagesChangeRight = imagesChangeRight;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(final View view, MotionEvent event) {
        if(beginX == 0 && beginY == 0){
            beginX = relativeLayout.getX();
            beginY = relativeLayout.getY();
        }
        float distanceFromCenter = relativeLayout.getX() - beginX;
        switch (event.getAction()) {

                /* Нажатие на relativeLayout */
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                break;

                /* Перемещение relativeLayout */
            case MotionEvent.ACTION_MOVE:

                    /* Анимация перемещения */
                view.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();

                    /* Выбор, если перемещена карта влево или вправо на 250 */
                if(distanceFromCenter < 0 && distanceFromCenter > -250){
                    opinionText[1].animate()
                            .alpha(0.0f)
                            .setDuration(0)
                            .start();

                    for (int i = 0; i < 4; i++) {
                        imagesChangeRight[i].animate()
                                .alpha(0.0f)
                                .setDuration(0)
                                .start();
                        imagesChangeLeft[i].animate()
                                .alpha((-((int)distanceFromCenter / 10) * 0.1f))
                                .setDuration(0)
                                .start();
                    }

                    opinionText[0].animate()
                            .alpha((-((int)distanceFromCenter / 10) * 0.1f))
                            .setDuration(0)
                            .start();

                    view.animate()
                            .rotation((((int)distanceFromCenter / 2) * 0.1f))
                            .start();
                }

                if(distanceFromCenter > 0 && distanceFromCenter < 250){
                    opinionText[0].animate()
                            .alpha(0.0f)
                            .setDuration(0)
                            .start();

                    for (int i = 0; i < 4; i++) {
                        imagesChangeLeft[i].animate()
                                .alpha(0.0f)
                                .setDuration(0)
                                .start();
                        imagesChangeRight[i].animate()
                                .alpha((((int)distanceFromCenter / 10) * 0.1f))
                                .setDuration(0)
                                .start();
                    }

                    opinionText[1].animate()
                            .alpha((((int)distanceFromCenter / 10) * 0.1f))
                            .setDuration(0)
                            .start();

                    view.animate()
                            .rotation((((int)distanceFromCenter / 2) * 0.1f))
                            .start();
                }
                break;

                /* При отпускании relativeLayout */
            case MotionEvent.ACTION_UP:
                if(distanceFromCenter < -200){
                    Controller.doChoose(MainGame.Choose.LEFT);
                    view.animate()
                            .x(beginX)
                            .y(beginY)
                            .rotation(0.0f)
                            .setDuration(0)
                            .alpha(0.0f)
                            .start();
                    view.animate()
                            .alpha(1.0f)
                            .setDuration(350)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                }
                            });
                } else if(distanceFromCenter > 200){
                    Controller.doChoose(MainGame.Choose.RIGHT);
                    view.animate()
                            .x(beginX)
                            .y(beginY)
                            .rotation(0.0f)
                            .setDuration(0)
                            .alpha(0.0f)
                            .start();
                    view.animate()
                            .alpha(1.0f)
                            .setDuration(350)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                }
                            });
                } else {
                    view.animate()
                            .x(beginX)
                            .y(beginY)
                            .rotation(0.0f)
                            .setDuration(200)
                            .start();
                }
                opinionText[0].animate()
                        .alpha(0.0f)
                        .setDuration(0)
                        .start();
                opinionText[1].animate()
                        .alpha(0.0f)
                        .setDuration(0)
                        .start();

                for (int i = 0; i < 4; i++) {
                    imagesChangeLeft[i].animate()
                            .alpha(0.0f)
                            .setDuration(0)
                            .start();
                    imagesChangeRight[i].animate()
                            .alpha(0.0f)
                            .setDuration(0)
                            .start();

                }
                break;

                /* При отмене */
            case MotionEvent.ACTION_CANCEL:
                view.animate()
                        .x(beginX)
                        .y(beginY)
                        .rotation(0.0f)
                        .alpha(1.0f)
                        .setDuration(0)
                        .start();
                opinionText[0].animate()
                        .alpha(0.0f)
                        .setDuration(0)
                        .start();
                opinionText[1].animate()
                        .alpha(0.0f)
                        .setDuration(0)
                        .start();
                for (int i = 0; i < 4; i++) {
                    imagesChangeLeft[i].animate()
                            .alpha(0.0f)
                            .setDuration(0)
                            .start();
                    imagesChangeRight[i].animate()
                            .alpha(0.0f)
                            .setDuration(0)
                            .start();

                }
                break;
            default:
                return false;
        }
        return true;
    }
}
