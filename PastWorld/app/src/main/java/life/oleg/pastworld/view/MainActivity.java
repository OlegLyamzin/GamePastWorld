package life.oleg.pastworld.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import life.oleg.pastworld.R;
import life.oleg.pastworld.controller.Controller;
import life.oleg.pastworld.model.MainGame;
import life.oleg.pastworld.model.Player;
import life.oleg.pastworld.model.Status;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private enum HandlerType {STATUS, IMAGECHANGES, TASK, YEAR, NAME, OPINION, IMAGE, COUNSELORS}
    private final String NAME = "name";
    private final String YEAR = "year";
    private final String LIVES = "lives";
    private final String COUNSELORS = "counselors";
    private final String ADS = "ads";
    private final String ERA = "era";
    private final String EXPERIENCE = "experience";
    private TextView taskText;
    private TextView yearText;
    private TextView nameText;
    private TextView counterCounselors;
    private ProgressBar[] statusProgressBars = new ProgressBar[4];
    private TextView[] opinionText = new TextView[2];
    private ImageView imagePerson;
    private RelativeLayout relativeLayoutPerson;
    private RelativeLayout relativeLayoutGame;
    private RelativeLayout relativeLayoutStart;
    private ImageView[] imagesChangeLeft = new ImageView[4];
    private ImageView[] imagesChangeRight = new ImageView[4];
    private Button buttonStart;
    private volatile boolean counselor = false;
    private Handler mUIHandler = new Handler();
    private HandlerWorker handlerWorker;
    private InterstitialAd mInterstitialAd;
    private AdRequest request;

    //private HashMap<HandlerType, Handler> handlers = new HashMap<>();

    private boolean flag =false; //todo

    public void setCounselor(boolean counselor) {
        this.counselor = counselor;
    }

    public void setImagePerson(final int image){
        Runnable runnable = new Runnable() {
            public void run() {
//                Message msg = handlers.get(HandlerType.IMAGE).obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putInt("Key", imagePerson);
//                msg.setData(bundle);
//                handlers.get(HandlerType.IMAGE).sendMessage(msg);
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        imagePerson.setImageResource(image);
                    }
                });
            }
        };
        handlerWorker.postTask(runnable);
    }

    public void setOpinionText(final int leftOpinion, final int rightOpinion) {
        Runnable runnable = new Runnable() {
            public void run() {
//                Message msg = handlers.get(HandlerType.OPINION).obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putString("Left", getString(leftOpinion));
//                bundle.putString("Right", getString(rightOpinion));
//                msg.setData(bundle);
//                handlers.get(HandlerType.OPINION).sendMessage(msg);
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        opinionText[0].setText(getString(leftOpinion));
                        opinionText[1].setText(getString(rightOpinion));
                    }
                });
            }
        };
        handlerWorker.postTask(runnable);
    }

    public void setNameText(final int name) {
        Runnable runnable = new Runnable() {
            public void run() {
//                Message msg = handlers.get(HandlerType.NAME).obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putString("Key", getString(name));
//                msg.setData(bundle);
//                handlers.get(HandlerType.NAME).sendMessage(msg);
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        nameText.setText(getString(name));
                    }
                });
            }
        };
        handlerWorker.postTask(runnable);
    }

    public void setYearText(final String s) {
        Runnable runnable = new Runnable() {
            public void run() {
//                Message msg = handlers.get(HandlerType.YEAR).obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putString("Key", s);
//                msg.setData(bundle);
//                handlers.get(HandlerType.YEAR).sendMessage(msg);
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        yearText.setText(s);
                    }
                });
            }
        };
        handlerWorker.postTask(runnable);
    }

    public void setTaskText(final int question) {
//        Runnable runnable = new Runnable() {
//            public void run() {
//                Message msg = handlers.get(HandlerType.TASK).obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putString("Key", getString(question));
//                msg.setData(bundle);
//                handlers.get(HandlerType.TASK).sendMessage(msg);
//            }
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        taskText.setText(getString(question));
                    }
                });
            }
        };
        handlerWorker.postTask(runnable);

    }



    public void setChangedImages(final Status.Change[] left, final Status.Change[] right) {//todo Динамическое изменение кружочков на стрелочки
        Runnable runnable = new Runnable() {
            public void run() {
                final int[] resLeft = new int[4];
                final int[] resRight = new int[4];
                if(!counselor){
                    for (int i = 0; i < 4; i++) {
                        if (left[i] == Status.Change.UP){
                            resLeft[i] = R.drawable.circle_up;
                        } else if(left[i] == Status.Change.DOWN){
                            resLeft[i] = R.drawable.circle_down;
                        } else {
                            resLeft[i] = R.drawable.empty_image;
                        }
                        if (right[i] == Status.Change.UP){
                            resRight[i] = R.drawable.circle_up;
                        } else if(right[i] == Status.Change.DOWN){
                            resRight[i] = R.drawable.circle_down;
                        } else {
                            resRight[i] = R.drawable.empty_image;
                        }
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        if (left[i] == Status.Change.UP){
                            resLeft[i] = R.drawable.rectangle_up;
                        } else if(left[i] == Status.Change.DOWN) {
                            resLeft[i] = R.drawable.rectangle_down;
                        } else {
                            resLeft[i] = R.drawable.empty_image;
                        }
                        if (right[i] == Status.Change.UP){
                            resRight[i] = R.drawable.rectangle_up;
                        } else if(right[i] == Status.Change.DOWN) {
                            resRight[i] = R.drawable.rectangle_down;
                        } else {
                            resRight[i] = R.drawable.empty_image;
                        }
                    }
                }

                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 4; i++) {
                            imagesChangeLeft[i].setImageResource(resLeft[i]);
                            imagesChangeLeft[i].setTag(resLeft[i]);
                            imagesChangeRight[i].setImageResource(resRight[i]);
                            imagesChangeRight[i].setTag(resRight[i]);
                        }
                    }
                });
//                Message msg = handlers.get(HandlerType.IMAGECHANGES).obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putIntArray("Left", resLeft);
//                bundle.putIntArray("Right", resRight);
//                msg.setData(bundle);
//                handlers.get(HandlerType.IMAGECHANGES).sendMessage(msg);
            }
        };
        handlerWorker.postTask(runnable);
    }

    public void counselorChangeImages(){
        Runnable runnable = new Runnable() {
            public void run() {
                final int[] resLeft = new int[4];
                final int[] resRight = new int[4];
                if(!counselor){
                    for (int i = 0; i < 4; i++) {
                        if((int)imagesChangeLeft[i].getTag() == R.drawable.rectangle_down){
                            resLeft[i] = R.drawable.circle_down;
                        } else if((int)imagesChangeLeft[i].getTag() == R.drawable.rectangle_up){
                            resLeft[i] = R.drawable.circle_up;
                        } else {
                            resLeft[i] = R.drawable.empty_image;
                        }if((int)imagesChangeRight[i].getTag() == R.drawable.rectangle_down){
                            resRight[i] = R.drawable.circle_down;
                        } else if((int)imagesChangeRight[i].getTag() == R.drawable.rectangle_up){
                            resRight[i] = R.drawable.circle_up;
                        } else {
                            resRight[i] = R.drawable.empty_image;
                        }
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        if((int)imagesChangeLeft[i].getTag() == R.drawable.circle_down){
                            resLeft[i] = R.drawable.rectangle_down;
                        } else if((int)imagesChangeLeft[i].getTag() == R.drawable.circle_up){
                            resLeft[i] = R.drawable.rectangle_up;
                        } else {
                            resLeft[i] = R.drawable.empty_image;
                        }if((int)imagesChangeRight[i].getTag() == R.drawable.circle_down){
                            resRight[i] = R.drawable.rectangle_down;
                        } else if((int)imagesChangeRight[i].getTag() == R.drawable.circle_up){
                            resRight[i] = R.drawable.rectangle_up;
                        } else {
                            resRight[i] = R.drawable.empty_image;
                        }
                    }
                }

                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 4; i++) {
                            imagesChangeLeft[i].setImageResource(resLeft[i]);
                            imagesChangeLeft[i].setTag(resLeft[i]);
                            imagesChangeRight[i].setImageResource(resRight[i]);
                            imagesChangeRight[i].setTag(resRight[i]);
                        }
                    }
                });
//                Message msg = handlers.get(HandlerType.IMAGECHANGES).obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putIntArray("Left", resLeft);
//                bundle.putIntArray("Right", resRight);
//                msg.setData(bundle);
//                handlers.get(HandlerType.IMAGECHANGES).sendMessage(msg);
            }
        };
        handlerWorker.postTask(runnable);
    }

    public void setStatus(final int ecology, final int people, final int military, final int money) {
        Runnable runnable = new Runnable() {
            public void run() {
//                Message msg = handlers.get(HandlerType.STATUS).obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putInt("ecology", ecology);
//                bundle.putInt("people", people);
//                bundle.putInt("military", military);
//                bundle.putInt("money", money);
//                msg.setData(bundle);
//                handlers.get(HandlerType.STATUS).sendMessage(msg);
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            statusProgressBars[0].setProgress(ecology, true);
                            statusProgressBars[1].setProgress(people, true);
                            statusProgressBars[2].setProgress(military, true);
                            statusProgressBars[3].setProgress(money, true);
                        } else {
                            statusProgressBars[0].setProgress(ecology);
                            statusProgressBars[1].setProgress(people);
                            statusProgressBars[2].setProgress(military);
                            statusProgressBars[3].setProgress(money);
                        }
                    }
                });
            }
        };
        handlerWorker.postTask(runnable);
    }

    public void setCounselorCounter(final int counselors) {
        Runnable runnable = new Runnable() {
            public void run() {
//                Message msg = handlers.get(HandlerType.COUNSELORS).obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putInt("Key", counselors);
//                msg.setData(bundle);
//                handlers.get(HandlerType.COUNSELORS).sendMessage(msg);
                final String s = (getString(R.string.remainedCounselors) + counselors);
                mUIHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        counterCounselors.setText(s);
                    }
                });
            }
        };
        handlerWorker.postTask(runnable);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        handlerWorker = new HandlerWorker("My Handler Worker");
        handlerWorker.start();
        handlerWorker.prepareHandler();

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5024086206882853~4351619573");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"/*"ca-app-pub-5024086206882853/2416555528"*/);
        request = new AdRequest.Builder().addTestDevice("3DB15A16D967D9FA7400242C023AFC7A").build();
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("3DB15A16D967D9FA7400242C023AFC7A").build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("3DB15A16D967D9FA7400242C023AFC7A").build());
            }

        });

        mVisible = true;
        mContentView = findViewById(R.id.frameLayout);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);
        findViewById(R.id.adsRemoveBtn).setOnClickListener(this);

        opinionText[0] = findViewById(R.id.textLeftOpinion);
        opinionText[1] = findViewById(R.id.textRightOpinion);
        statusProgressBars[0] = findViewById(R.id.ecologyProgressBar);
        statusProgressBars[1] = findViewById(R.id.peopleProgressBar);
        statusProgressBars[2] = findViewById(R.id.militaryProgressBar);
        statusProgressBars[3] = findViewById(R.id.moneyProgressBar);
        imagesChangeLeft[0] = findViewById(R.id.imageChangeEcologyLeft);
        imagesChangeLeft[1] = findViewById(R.id.imageChangePeopleLeft);
        imagesChangeLeft[2] = findViewById(R.id.imageChangeMilitaryLeft);
        imagesChangeLeft[3] = findViewById(R.id.imagechangeMoneyLeft);
        imagesChangeRight[0] = findViewById(R.id.imageChangeEcologyRight);
        imagesChangeRight[1] = findViewById(R.id.imageChangePeopleRight);
        imagesChangeRight[2] = findViewById(R.id.imageChangeMilitaryRight);
        imagesChangeRight[3] = findViewById(R.id.imagechangeMoneyRight);
        taskText = findViewById(R.id.textTask);
        yearText = findViewById(R.id.textYear);
        nameText = findViewById(R.id.textName);
        counterCounselors = findViewById(R.id.counselorsCounter);

        relativeLayoutGame = findViewById(R.id.relativeLayoutGame);
        relativeLayoutStart = findViewById(R.id.relativeLayoutStart);
        relativeLayoutGame.setVisibility(View.INVISIBLE);
        relativeLayoutGame.setAlpha(0.0f);
//        relativeLayoutGame.setAlpha(0.0f);
//        relativeLayoutGame.setLayoutParams(new RelativeLayout.LayoutParams(0,0));

        imagePerson = findViewById(R.id.imagePerson);
        relativeLayoutPerson = findViewById(R.id.relativeLayoutPerson);
        relativeLayoutPerson.setOnTouchListener(new CardTouchListener(opinionText, relativeLayoutPerson, imagesChangeLeft, imagesChangeRight));

        opinionText[0].setAlpha(0.0f);
        opinionText[1].setAlpha(0.0f);
        for (int i = 0; i < 4; i++) {
            imagesChangeLeft[i].setAlpha(0.0f);
            imagesChangeRight[i].setAlpha(0.0f);
        }

        findViewById(R.id.buttonCounselor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!counselor)
                Controller.counselorActivate();
            }
        });

        findViewById(R.id.buttonBuyCounselors).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.addCounselors();
            }
        });

        //initHandlers();

        MainGame mainGame = new MainGame();
        Controller.setMainGame(mainGame);
        Controller.setMainActivity(this);
        new Thread(mainGame).start();
        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean("hasState")) {
                load();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v == buttonStart){
//            relativeLayoutGame.setLayoutParams(new RelativeLayout.LayoutParams(relativeLayoutStart.getWidth(),relativeLayoutStart.getHeight()));
//            relativeLayoutGame.setVisibility(View.VISIBLE);
//            imagePerson.setAlpha(1.0f);
            animationRelativeLayout(1.0f, View.VISIBLE);
//            Controller.restart();
//            relativeLayoutGame.setVisibility(View.VISIBLE);
            Controller.continueGame();
        }
        if(v == findViewById(R.id.adsRemoveBtn)){
            Controller.adsChange();
        }
    }

    public void exitToStartMenu() {
//        imagePerson.setAlpha(0.0f);
        animationRelativeLayout(0.0f, View.INVISIBLE);

    }

    private synchronized void animationRelativeLayout(float alpha, final int visibility){
        relativeLayoutGame.animate()
                .alpha(alpha)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        relativeLayoutGame.setVisibility(visibility);
                        if(visibility == View.INVISIBLE){
                            Controller.showAdFulscreen();
                        }
                    }
                });
//        relativeLayoutGame.setVisibility(visibility);
    }

    public  void showAd(){
        if(mInterstitialAd.isLoaded() && request.isTestDevice(getApplicationContext())){
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }

    @SuppressLint("HandlerLeak")
//    private void initHandlers() {
//
//        handlers.put(HandlerType.IMAGECHANGES,new android.os.Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Bundle bundle = msg.getData();
//                int[] left = bundle.getIntArray("Left");
//                int[] right = bundle.getIntArray("Right");
//                for (int i = 0; i < 4; i++) {
//                    imagesChangeLeft[i].setImageResource(left[i]);
//                    imagesChangeLeft[i].setTag(left[i]);
//                    imagesChangeRight[i].setImageResource(right[i]);
//                    imagesChangeRight[i].setTag(right[i]);
//                }
//            }
//        });
//
//        handlers.put(HandlerType.TASK ,new android.os.Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Bundle bundle = msg.getData();
//                String s = bundle.getString("Key");
//                taskText.setText(s);
//            }
//        });
//
//        handlers.put(HandlerType.COUNSELORS ,new android.os.Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Bundle bundle = msg.getData();
//                int counselors = bundle.getInt("Key");
//                String s = (getString(R.string.remainedCounselors) + counselors);
//                counterCounselors.setText(s);
//            }
//        });
//
//        handlers.put(HandlerType.YEAR,new android.os.Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Bundle bundle = msg.getData();
//                String s = bundle.getString("Key");
//                yearText.setText(s);
//            }
//        });
//
//        handlers.put(HandlerType.NAME,new android.os.Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Bundle bundle = msg.getData();
//                String s = bundle.getString("Key");
//                nameText.setText(s);
//            }
//        });
//
//        handlers.put(HandlerType.OPINION ,new android.os.Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Bundle bundle = msg.getData();
//                String left = bundle.getString("Left");
//                String right = bundle.getString("Right");
//                opinionText[0].setText(left);
//                opinionText[1].setText(right);
//            }
//        });
//
//        handlers.put(HandlerType.STATUS ,new android.os.Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Bundle bundle = msg.getData();
//                int ecology = bundle.getInt("ecology");
//                int people = bundle.getInt("people");
//                int military = bundle.getInt("military");
//                int money = bundle.getInt("money");
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    statusProgressBars[0].setProgress(ecology, true);
//                    statusProgressBars[1].setProgress(people, true);
//                    statusProgressBars[2].setProgress(military, true);
//                    statusProgressBars[3].setProgress(money, true);
//                } else {
//                    statusProgressBars[0].setProgress(ecology);
//                    statusProgressBars[1].setProgress(people);
//                    statusProgressBars[2].setProgress(military);
//                    statusProgressBars[3].setProgress(money);
//                }
//
//            }
//        });
//
//        handlers.put(HandlerType.IMAGE ,new android.os.Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                Bundle bundle = msg.getData();
//                int res = bundle.getInt("Key");
//                imagePerson.setImageResource(res);
//            }
//        });
//    }

    @Override
    protected void onDestroy() {
        handlerWorker.quit();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("hasState", true);
        save();
        super.onSaveInstanceState(outState);
    }

    public void save(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        Player player = Controller.getPlayer();
        editor.putString(NAME, player.getName());
        editor.putInt(YEAR, player.getYear());
        editor.putInt(LIVES, player.getLives());
        editor.putInt(COUNSELORS, player.getCounselors());
        editor.putBoolean(ADS, player.isAds());
        editor.putInt(ERA, player.getEra());
        editor.putInt(EXPERIENCE, player.getExperience());
        editor.apply();
    }

    public void load(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Player player = Controller.getPlayer();
        player.setName(settings.getString(NAME, player.getName()));
        player.setYear(settings.getInt(YEAR, player.getYear()));
        player.setLives(settings.getInt(LIVES, player.getLives()));
        player.setCounselors(settings.getInt(COUNSELORS, player.getCounselors()));
        player.setAds(settings.getBoolean(ADS, player.isAds()));
        player.setEra(settings.getInt(ERA, player.getEra()));
        player.setExperience(settings.getInt(EXPERIENCE, player.getExperience()));
        Controller.setCounselorsCounter();
    }

    /**
     * Внимание!! все что дальше скопировано и сделано для полноэкранного режима
     */
//    private static final boolean AUTO_HIDE = true;
//
//    /**
//     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
//     * user interaction before hiding the system UI.
//     */
//    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
//
//    /**
//     * Some older devices needs a small delay between UI widget updates
//     * and a change of the status and navigation bar.
//     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
//    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
//            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
//    /**
//     * Touch listener to use for in-layout UI controls to delay hiding the
//     * system UI. This is to prevent the jarring behavior of controls going away
//     * while interacting with activity UI.
//     */
//    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            if (AUTO_HIDE) {
//                delayedHide(AUTO_HIDE_DELAY_MILLIS);
//            }
//            return false;
//        }
//    };

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_start);
//
//        mVisible = true;
//        mControlsView = findViewById(R.id.fullscreen_content_controls);
//        mContentView = findViewById(R.id.fullscreen_content);
//
//
////        // Set up the user interaction to manually show or hide the system UI.
////        mContentView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                toggle();
////            }
////        });
////
////        // Upon interacting with UI controls, delay any scheduled hide()
////        // operations to prevent the jarring behavior of controls going away
////        // while interacting with the UI.
////        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
//    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(0);

        load();
    }

//    private void toggle() {
//        if (mVisible) {
//            hide();
//        } else {
//            show();
//        }
//    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
//        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
