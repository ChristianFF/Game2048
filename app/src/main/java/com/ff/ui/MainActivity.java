package com.ff.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ff.R;
import com.ff.ui.base.BaseActivity;
import com.ff.util.SharedPreferencesUtils;
import com.ff.view.Game2048Layout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txt_restart)
    TextView txtRestart;
    @Bind(R.id.txt_best_score)
    TextView txtBestScore;
    @Bind(R.id.txt_current_score)
    TextView txtCurrentScore;
    @Bind(R.id.layout_2048)
    Game2048Layout layout2048;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void setViews() {
        setSupportActionBar(toolbar);

        txtBestScore.setText("BestScore: " + SharedPreferencesUtils.getInt("BestScore", 0));

        txtRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRestartDialog();
            }
        });

        layout2048.setOnGame2048Listener(new Game2048Layout.OnGame2048Listener() {
            @Override
            public void onScoreChange(int score) {
                updateAndSaveScore(score);
            }

            @Override
            public void onGameOver() {
                showGameOverDialog();
            }
        });
    }

    private void showRestartDialog() {
        new AlertDialog.Builder(MainActivity.this).setTitle("2048")
                .setMessage("Sure to start a new game?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        layout2048.restart();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
    }

    private void updateAndSaveScore(int score) {
        if (SharedPreferencesUtils.getInt("BestScore", 0) < score) {
            txtBestScore.setText("BestScore: " + score);
            SharedPreferencesUtils.putInt("BestScore", score);
        }
        txtCurrentScore.setText("CurrentScore: " + score);
    }

    private void showGameOverDialog() {
        new AlertDialog.Builder(MainActivity.this).setTitle("GAME OVER!")
                .setMessage(txtCurrentScore.getText() + ",Try again?")
                .setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        layout2048.restart();
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:

                break;
            case R.id.item_about:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
