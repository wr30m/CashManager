package com.example.kirazavrik.cashmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kirazavrik.cashmanager.models.Category;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AddBalanceDialogFragment.EditAddBalanceFragment {

    private ImageButton changeBalanceButton;
    private TextView balanceText;
    private PieChart diagram;
    private BalanceManager balanceManager;
    private CategoryManager categoryManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        balanceManager = BalanceManager.getInstance();

        changeBalanceButton = findViewById(R.id.changeBalanceBtn);
        changeBalanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddBalanceFragment();
            }
        });

        balanceText = findViewById(R.id.balance_text);

        diagram = findViewById(R.id.diagram);
        diagram.setUsePercentValues(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        categoryManager = CategoryManager.getInstance();

        mockDiagram();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFinishEditAddBalanceFragment(String inputText) {
        updateBalance(inputText);
    }

    private void startAddBalanceFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddBalanceDialogFragment addBalanceDialogFragment = AddBalanceDialogFragment.newInstance();
        addBalanceDialogFragment.show(fragmentManager, "change_balance_dialog");
    }

    private void updateBalance(String inputBalance) {
        Double newBalance = Double.parseDouble(inputBalance);
        balanceManager.setBalance(newBalance);
        balanceText.setText(newBalance.toString());
    }

    private void mockDiagram() {
        List<PieEntry> entries = new ArrayList<>();
        Random r = new Random();
        float min = 0.1f;
        float max = 100.1f;
        for (Category category : categoryManager.getCategories()) {
            //float f = (float) category.getTotalWaste();
            float random = min + r.nextFloat() * (max - min);
            entries.add(new PieEntry(random, category.getLabel()));
        }

        PieDataSet set = new PieDataSet(entries, "Results");
        PieData data = new PieData(set);
        data.setValueFormatter(new PercentFormatter());
        diagram.setData(data);
        diagram.invalidate();
    }
}
