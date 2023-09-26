package example.com.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TransferSecondaryPageActivity extends AppCompatActivity {
    private ViewPager viewPager;
    ColorStateList def;
    TextView item1;
    TextView item2;
    TextView select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_secondary_page);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        select = findViewById(R.id.select);
        viewPager = findViewById(R.id.viewpager);
        def = item2.getTextColors();

//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        viewPagerAdapter.addFragment(new TransferSecondaryFragment());
//        viewPagerAdapter.addFragment(new RekeningFragment());
//        viewPagerAdapter.addFragment(new FavoritFragment());
//        viewPager.setAdapter(viewPagerAdapter);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new TransferSecondaryFragment());
        viewPagerAdapter.addFragment(new FavoritFragment());
        viewPager.setAdapter(viewPagerAdapter);

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.animate().x(0).setDuration(100);
                item1.setTextColor(Color.BLACK);
                item2.setTextColor(def);
                viewPager.setCurrentItem(0);
//
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item1.setTextColor(def);
                item2.setTextColor(Color.BLACK);
                int size = item2.getWidth();
                select.animate().x(size).setDuration(100);
                viewPager.setCurrentItem(1);
            }
        });

    }
}